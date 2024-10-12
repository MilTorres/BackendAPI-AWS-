package com.matr.mysqlaws.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios_local")
public class Usuario {



@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
    private long id;
    @Column(name = "rfid")
    private String rfid;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "correo")
    private String correo;
    @Column(name = "contrasena")
    private String contrasena;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "saldo")
    private Integer saldo;
    @Column(name = "fecha")
    private String fecha;
    @Column(name = "tipo_usuario")
    private String tipo_usuario;



}
