package com.matr.mysqlaws.controller;


import com.matr.mysqlaws.models.Lavadoras;
import com.matr.mysqlaws.models.LoginRequest;
import com.matr.mysqlaws.models.Usuario;
import com.matr.mysqlaws.repository.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UsuarioController {


    @Autowired
    private UsuarioDao usuarioDao;


   // @CrossOrigin(origins = "http://localhost:5005") // Permite solicitudes desde este dominio
    @GetMapping("/")
    public String c() {
        return "Felicidades conecto al AWS de Mysql";
    }

    @GetMapping(value = "/token")
    public String GeneraToken() {
        return "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "<SOAP-ENV:Header/>\n" +
                "<SOAP-ENV:Body>\n" +
                "<ns3:loginResponse xmlns:ns3=\"http://proxy.standard.services.sesame.bappa.com\">\n" +
                "<loginReturn>9456d1c72b86667ac8150256d936dea7d4051a9988</loginReturn>\n" +
                "</ns3:loginResponse>\n" +
                "</SOAP-ENV:Body>\n" +
                "</SOAP-ENV:Envelope>";
    }
    @GetMapping(value ="/my")
    public String mysql() {
        return "Felicidades conecto al AWS de Mysql con properties y dependencias y conexion a mysql";
    }


    @GetMapping(value="/con")
    public String mysqls() {
        return "Felicidades conecto al AWS de Mysql con properties y dependencias y la clase usuarios y usuarioDao";
    }


    @GetMapping(value = "/usuarios")
    public List<Usuario> getUsuarios() {

        return usuarioDao.getUsuarios();
    }
    @DeleteMapping(value = "/elimusuario/{rfid}")
    public void deleteUsuario(@PathVariable("rfid") String rfid)
    {
        usuarioDao.deleteUsuario(rfid);
    }
    @GetMapping(value = "usuarioedit/{rfid}")
    public Usuario getUsuario(@PathVariable("rfid") String rfid) {
        return usuarioDao.obtenerPorrfId(rfid);
    }

    @PatchMapping(value = "/actualizar/{rfid}")
    public void actualizarUsuario(@PathVariable("rfid") String rfid, @RequestBody Usuario usuario) {
        usuarioDao.actualizar(rfid, usuario);
    }


    @PostMapping(value = "registrarusuario")
    public void registrarUsuarios(@RequestBody Usuario usuario) {
        usuarioDao.registrar(usuario);
    }



    @PostMapping("/login")
    public ResponseEntity<?> checkLogin(@RequestBody LoginRequest loginRequest) {
        String correo = loginRequest.getEmail();
        String pass = loginRequest.getPassword();

        // Llama al método validarLogin sin tipo de usuario
        Usuario usuario = usuarioDao.validarLogin(correo, pass);

        // Valida si el usuario no se encontró o si no tiene acceso
        if (usuario == null) {
            return ResponseEntity.status(404).body("Usuario no encontrado o acceso denegado");
        }

        // Si el usuario es válido y es un administrador, devuelve el usuario
        return ResponseEntity.ok(usuario);
    }



    @GetMapping("/check_uid/{rfid}")
    public String checkUid(@PathVariable String rfid) {
        Usuario usuario = usuarioDao.obtenerPorRfId(rfid);

        if (usuario != null && usuario.getSaldo() >= 10) {
            System.out.println("retorna estatus VERDE arduino ");
            return "registered";
        } else {
            System.out.println("retorna estatus ROJO arduino");
            return "No";
        }
    }

    @GetMapping("/estado/{id}")
    public String obtenerEstadoLavadora(@PathVariable("id") String id) {
        Lavadoras lavadoras = usuarioDao.estadoLav(id);

        if (lavadoras!= null) {
            System.out.println("El estado de la lavadora es 1 ");
            return "activo";
        } else {
            System.out.println("El estado de la lavadora es 0");
            return "desactivado";
        }
    }

    // Endpoint para actualizar el estado de la lavadora a 0
    @PutMapping("/actualizarlav/{id}")
    public ResponseEntity<String> actualizarEstadoLavadoraPorId(@PathVariable String id) {
        try {
            System.out.println("el ide para regresar a 0 es = "+id);
            usuarioDao.actualizarEstadoLavadoraPorId(id);
            return ResponseEntity.ok("Estado de la lavadora actualizado a 0");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el estado de la lavadora");
        }
    }

    @GetMapping("/check_saldo/{hexs}")
    public ResponseEntity<Map<String, Object>> checkSaldo(@PathVariable String hexs) {
        Usuario usuario = usuarioDao.obtenerSaldoPorRfId(hexs);

        Map<String, Object> response = new HashMap<>();

        if (usuario != null) {
            response.put("existe", true);
            response.put("saldo", usuario.getSaldo());
            response.put("nombre", usuario.getNombre()); // Devuelve el saldo del usuario
            return ResponseEntity.ok(response); // Devuelve una respuesta exitosa con el saldo
        } else {
            response.put("existe", false);
            response.put("mensaje", "Usuario no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response); // Devuelve 404 si el usuario no existe
        }
    }



}