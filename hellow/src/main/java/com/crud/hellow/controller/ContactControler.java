package com.crud.hellow.controller;

import com.crud.hellow.entity.Contact;
import com.crud.hellow.service.ContactService;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/api/contacts")
public class ContactControler {
    private final ContactService contactService;

    @GetMapping
    public Iterable<Contact> list() {
        return contactService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> get(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(contactService.findById(id));
        }catch(ResponseStatusException  e){
            System.out.println("Excepción capturada: " + e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(null);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Contact> create(@RequestBody Contact contact) {
        Contact savedContact = contactService.save(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContact);
    }

    @PutMapping("{id}")
     public ResponseEntity<Contact> update(@PathVariable Integer id, @RequestBody Contact contact) {
        try{
            Contact updatedContact = contactService.update(id, contact);
            return ResponseEntity.ok(updatedContact);
        }catch(ResponseStatusException  e){
            System.out.println("Excepción capturada: " + e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(null);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try{
            contactService.delete(id);
            return ResponseEntity.noContent().build();
        }catch(ResponseStatusException  e){
            System.out.println("Excepción capturada: " + e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(null);
        }
    }
}
