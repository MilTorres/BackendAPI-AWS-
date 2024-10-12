package com.matr.mysqlaws.repository;

import com.matr.mysqlaws.models.Lavadoras;
import com.matr.mysqlaws.models.Usuario;

import java.util.List;

public interface UsuarioDao  {

    List<Usuario> getUsuarios();


    void deleteUsuario(String rfid);




    Usuario obtenerPorrfId(String rfid);








    void actualizar(String rfid, Usuario usuario);





    void registrar(Usuario usuario);



    Usuario validarLogin(String correo, String pass );

    Usuario obtenerPorRfId(String rfid);

    Lavadoras estadoLav(String id);

    void actualizarEstadoLavadoraPorId(String id);

    Usuario obtenerSaldoPorRfId(String hexs);

}
