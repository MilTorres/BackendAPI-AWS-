package com.matr.mysqlaws.repository;


import com.matr.mysqlaws.models.Lavadoras;
import com.matr.mysqlaws.models.Pel;
import com.matr.mysqlaws.models.Usuario;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImplementacion implements UsuarioDao {


    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario ";
        return entityManager.createQuery(query).getResultList();
    }


    @Override
    @Transactional
    public void deleteUsuario(String rfid) {
        // Busca el usuario por la columna 'rfid'
        Usuario usuario = entityManager.createQuery("FROM Usuario WHERE rfid = :rfid", Usuario.class)
                .setParameter("rfid", rfid)
                .getSingleResult();

        // Si el usuario existe, lo elimina
        if (usuario != null) {
            entityManager.remove(usuario);
        }
    }


    @Transactional
    public Usuario obtenerPorrfId(String rfid) {
        String query = "FROM Usuario WHERE rfid = :rfid";
        TypedQuery<Usuario> typedQuery = entityManager.createQuery(query, Usuario.class);
        typedQuery.setParameter("rfid", rfid);
        return typedQuery.getSingleResult();
    }


    @Transactional
    public void actualizar(String rfid, Usuario usuario) {
        // Encuentra el usuario existente
        Usuario usuarioExistente = entityManager.createQuery("FROM Usuario WHERE rfid = :rfid", Usuario.class)
                .setParameter("rfid", rfid)
                .getSingleResult();

        if (usuarioExistente != null) {
            // Actualiza los campos necesarios
            usuarioExistente.setRfid(usuario.getRfid());
            usuarioExistente.setNombre(usuario.getNombre());
            usuarioExistente.setCorreo(usuario.getCorreo());
            usuarioExistente.setContrasena(usuario.getContrasena());
            usuarioExistente.setTelefono(usuario.getTelefono());
            usuarioExistente.setSaldo(usuario.getSaldo());
            usuarioExistente.setFecha(usuario.getFecha());

            // La entidad se actualiza automáticamente gracias a la persistencia de JPA
        } else {
            throw new RuntimeException("Usuario no encontrado con el rfid: " + rfid);
        }
    }


    @Override
    public void registrar(Usuario usuario) {

        entityManager.merge(usuario); //inserta objeto usuario en la bd mssql
    }


    @Transactional
    @Override
    public Usuario validarLogin(String correo, String pass) {
        try {
            // Primero, intenta obtener el usuario por correo y contraseña
            String query = "FROM Usuario WHERE correo = :correo AND contrasena = :pass";
            TypedQuery<Usuario> typedQuery = entityManager.createQuery(query, Usuario.class);
            typedQuery.setParameter("correo", correo);
            typedQuery.setParameter("pass", pass);

            // Obtén el usuario
            Usuario usuario = typedQuery.getSingleResult();

            // Verifica el tipo de usuario
            if (!"administrador".equals(usuario.getTipo_usuario())) {
                // Si no es administrador, lanzar una excepción o devolver un valor especial
                throw new SecurityException("Acceso denegado. Solo los administradores pueden iniciar sesión.");
            }

            return usuario;
        } catch (NoResultException e) {
            // Usuario no encontrado
            return null;
        } catch (SecurityException se) {
            // Maneja la excepción de seguridad si el tipo de usuario no es administrador
            System.err.println(se.getMessage());
            return null; // O puedes lanzar la excepción
        } catch (Exception e) {
            // Manejar otras excepciones si es necesario
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public Usuario obtenerPorRfId(String rfid) {
        try {
            // Obtener el usuario usando JPQL
            String query = "FROM Usuario WHERE rfid = :rfid";
            TypedQuery<Usuario> typedQuery = entityManager.createQuery(query, Usuario.class);
            typedQuery.setParameter("rfid", rfid);
            System.out.println("----------rfid: " + rfid);

            Usuario usuario = typedQuery.getSingleResult();
            System.out.println("----------usuario: " + usuario);

            // Si el usuario es encontrado, actualiza el estado en la tabla Lavadoras
            if (usuario.getRfid() != null) {
                // Actualizar el estado de la lavadora usando una consulta nativa
                String updateQuery = "UPDATE lavadoras SET estado = 1 WHERE id = 1";
                entityManager.createNativeQuery(updateQuery).executeUpdate();

                // Obtener el saldo actual del usuario
                int saldoActual = usuario.getSaldo();
                int descuento = 0;

                // Obtiene el día actual
                DayOfWeek diaSemana = LocalDate.now().getDayOfWeek();
                if (rfid.equals("1661C187")) {
                    if (diaSemana == DayOfWeek.MONDAY) {
                        descuento = 55;
                    } else if (diaSemana == DayOfWeek.TUESDAY) {
                        descuento = 55;
                    } else if (diaSemana == DayOfWeek.WEDNESDAY) {
                        descuento = 50;
                    } else if (diaSemana == DayOfWeek.THURSDAY) {
                        descuento = 50;
                    } else if (diaSemana == DayOfWeek.FRIDAY) {
                        descuento = 60;
                    } else if (diaSemana == DayOfWeek.SATURDAY) {
                        descuento = 60;
                    } else if (diaSemana == DayOfWeek.SUNDAY) {
                        descuento = 60;
                    }

                } else {

                    if (diaSemana == DayOfWeek.MONDAY) {
                        descuento = 35;
                    } else if (diaSemana == DayOfWeek.TUESDAY) {
                        descuento = 35;
                    } else if (diaSemana == DayOfWeek.WEDNESDAY) {
                        descuento = 35;
                    } else if (diaSemana == DayOfWeek.THURSDAY) {
                        descuento = 35;
                    } else if (diaSemana == DayOfWeek.FRIDAY) {
                        descuento = 40;
                    } else if (diaSemana == DayOfWeek.SATURDAY) {
                        descuento = 40;
                    } else if (diaSemana == DayOfWeek.SUNDAY) {
                        descuento = 40;
                    }

                }

                // Verifica si el saldo es suficiente para aplicar el descuento
                if (saldoActual >= descuento) {
                    // Construye la consulta SQL con el descuento calculado
                    String updateSaldo = "UPDATE Usuario SET saldo = saldo - " + descuento + " WHERE rfid = :rfid";
                    Query querySaldo = entityManager.createQuery(updateSaldo);
                    querySaldo.setParameter("rfid", rfid);
                    querySaldo.executeUpdate();

                    System.out.println("Se cambió a activo la lavadora 1 y se restó " + descuento + " a " + rfid);
                } else {
                    System.out.println("No se puede aplicar el descuento. Saldo insuficiente para " + rfid);
                    // Puedes lanzar una excepción o manejar esta situación como prefieras
                }
            }

            return usuario;
        } catch (NoResultException e) {
            System.out.println("No existe el usuario");
            // No se encontró ningún resultado, devolver null
            return null;
        } catch (Exception e) {
            System.out.println("No existe el usuario-catch 2");
            // Manejar otras excepciones si es necesario
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public Usuario obtenerSaldoPorRfId(String rfid) {
        try {
            // Obtener el usuario usando JPQL
            String query = "FROM Usuario WHERE rfid = :rfid";
            TypedQuery<Usuario> typedQuery = entityManager.createQuery(query, Usuario.class);
            typedQuery.setParameter("rfid", rfid);

            Usuario usuario = typedQuery.getSingleResult();
            System.out.println("Usuario encontrado: " + usuario);

            return usuario;
        } catch (NoResultException e) {
            System.out.println("Usuario no encontrado.");
            return null; // Si no se encuentra, devuelve null
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Si ocurre un error, también devuelve null
        }
    }

    @Override
    public Lavadoras estadoLav(String id) {
        try {
            // Obtener el usuario usando JPQL

            String query = "FROM Lavadoras WHERE id = :id";
            TypedQuery<Lavadoras> typedQuery = entityManager.createQuery(query, Lavadoras.class);
            typedQuery.setParameter("id", id);
            System.out.println("---------id: " + id);

            Lavadoras lavadoras = typedQuery.getSingleResult();
            System.out.println("----------lavadora: " + lavadoras);

            // Si el usuario es encontrado, actualiza el estado en la tabla Lavadoras
            if (lavadoras.getEstado().equals("0")) {
                // Actualizar el estado de la lavadora usando una consulta nativa


                System.out.println("El estado es ----->" + lavadoras.getEstado());
                lavadoras = null;

            } else {
                return lavadoras;
            }

            System.out.println("El estado es ----->" + lavadoras);
            return lavadoras;

        } catch (NoResultException e) {
            System.out.println("No existe el usuario");
            // No se encontró ningún resultado, devolver null
            return null;
        } catch (Exception e) {
            System.out.println("No existe el usuario-catch 2");
            // Manejar otras excepciones si es necesario
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public void actualizarEstadoLavadoraPorId(String id) {
        String updateQuery = "UPDATE lavadoras SET estado = 0 WHERE id = 1";
        entityManager.createNativeQuery(updateQuery).executeUpdate();
    }



    // PL

    @Override
    @Transactional
    public List<Pel> getPel() {
        String query = "FROM Pel ";
        return entityManager.createQuery(query).getResultList();
    }


}
