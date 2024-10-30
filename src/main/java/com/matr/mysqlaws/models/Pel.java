package com.matr.mysqlaws.models;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "peliculas")
public class Pel {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "fecha_estreno")
    private String fecha_estreno;



}
