package com.crud.hellow.entity;

import java.time.LocalDateTime;
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
public class Contact {
    public Contact(String name, String email, LocalDateTime registerAt) throws IllegalArgumentException {
        this.name=name;
        this.email=email;
        try {
            this.registerAt=registerAt;
        }catch(Exception e){
            System.err.println("Error: " + e.getMessage()); // Mensaje de error en consola
            throw e; // Relanzar la excepción para que el llamador pueda manejarla
        }
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private LocalDateTime registerAt;
}
