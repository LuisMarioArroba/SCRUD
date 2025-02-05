package com.crud.hellow.repository;

import com.crud.hellow.entity.Contact;
import org.springframework.data.repository.*;
public interface ContactRepository extends CrudRepository<Contact, Integer>{
    
}
