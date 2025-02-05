package com.crud.hellow.repository;

import com.crud.hellow.entity.Product;
import org.springframework.data.repository.*;
public interface ProductRepository extends CrudRepository<Product, Integer>{

}
