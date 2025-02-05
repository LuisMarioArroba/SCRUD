package com.crud.hellow.service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.crud.hellow.entity.Contact;
import com.crud.hellow.repository.ContactRepository;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public Iterable<Contact> findAll() {
        return contactRepository.findAll();
    }

    public Contact findById(Integer id) {
        return contactRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró Contacto con este identificador"));
    }

    public Contact save(Contact contact) {
        if (contact.getRegisterAt() == null) {
            contact.setRegisterAt(LocalDateTime.now());
        }
        return contactRepository.save(contact);
    }

    public Contact update(Integer id, Contact contact) {
        if (contact.getName() == null || contact.getEmail() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "No se pueden actualizar registros con valores nulos.");
        }

        Contact contactFromDB = contactRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró Contacto con este identificador"));

        contactFromDB.setName(contact.getName());
        contactFromDB.setEmail(contact.getEmail());

        return contactRepository.save(contactFromDB);
    }

    public void delete(Integer id) {
        Contact contactFromDB = contactRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró Contacto con este identificador"));
        
        contactRepository.delete(contactFromDB);
    }
}
