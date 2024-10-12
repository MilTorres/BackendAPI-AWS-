package com.matr.mysqlaws.models;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "lavadoras")
public class Lavadoras {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "estado")
    private String estado;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "descripcion")
    private String descripcion;





}
