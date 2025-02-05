package com.crud.hellow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@Entity
public class Product {
    public Product(Integer codigo,String name, float precio, Integer cantidad) throws IllegalArgumentException {
        this.codigo=codigo;
        this.name=name;
        this.precio=precio;
        this.cantidad=cantidad;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer codigo;
    private String name;
    private float precio;
    private Integer cantidad;
}
