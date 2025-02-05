package com.crud.hellow.service;

import java.sql.Connection;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.crud.hellow.entity.Product;
import com.crud.hellow.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    Connection con= new conexionService().conexionBD();


    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Integer id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró Contacto con este identificador"));
    }

    public Product save(Product product) {
        try {
            Statement stnt = con.createStatement();
            String query = "insert into dbo.Product values("+product.getCodigo()+",'"+product.getName()+"', '"+product.getPrecio()+"', '"+product.getCantidad()+"')";
            
            // Ejecutamos la consulta de eliminación
            int rowsAffected = stnt.executeUpdate(query);
            
            // Verificamos si se eliminó alguna fila
            if (rowsAffected > 0) {
                System.out.println("Producto editado exitosamente.");
            } else {
                System.out.println("No se encontró el producto con el id especificado.");
            }
            stnt.close();  // Cerramos el statement después de usarlo
        } catch (Exception exception) {
            System.out.println("Error al ejecutar el query");
            exception.printStackTrace();  // Imprimir la excepción completa para depuración
        }
        return productRepository.save(product);
    }

    public Product update(Integer id, Product product) {
        if (product.getName() == null || product.getCodigo() == null || product.getPrecio() == 0.0) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "No se pueden actualizar registros con valores nulos.");
        }

        Product productFromDB = productRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró Contacto con este identificador"));

        productFromDB.setName(product.getName());
        productFromDB.setCodigo(product.getCodigo());
        productFromDB.setPrecio(product.getPrecio());
        productFromDB.setCantidad(product.getCantidad());

        try {
            Statement stnt = con.createStatement();
            String query = "UPDATE dbo.Product SET CODIGO = "+product.getCodigo()+", NAME = '"+product.getName()+"', PRECIO = '"+product.getPrecio()+"', CANTIDAD = '"+product.getCantidad()+"' WHERE id = " + id;
            
            // Ejecutamos la consulta de eliminación
            int rowsAffected = stnt.executeUpdate(query);
            
            // Verificamos si se eliminó alguna fila
            if (rowsAffected > 0) {
                System.out.println("Producto editado exitosamente.");
            } else {
                System.out.println("No se encontró el producto con el id especificado.");
            }
            stnt.close();  // Cerramos el statement después de usarlo
        } catch (Exception exception) {
            System.out.println("Error al ejecutar el query");
            exception.printStackTrace();  // Imprimir la excepción completa para depuración
        }

        return productRepository.save(productFromDB);
    }

    public void delete(Integer id) {
        Product contactFromDB = productRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró Contacto con este identificador"));
        try {
            Statement stnt = con.createStatement();
            String query = "DELETE FROM dbo.Product WHERE id = " + id;
            
            // Ejecutamos la consulta de eliminación
            int rowsAffected = stnt.executeUpdate(query);
            
            // Verificamos si se eliminó alguna fila
            if (rowsAffected > 0) {
                System.out.println("Producto eliminado exitosamente.");
            } else {
                System.out.println("No se encontró el producto con el id especificado.");
            }
        
            stnt.close();  // Cerramos el statement después de usarlo
        } catch (Exception exception) {
            System.out.println("Error al ejecutar el query");
            exception.printStackTrace();  // Imprimir la excepción completa para depuración
        }
        productRepository.delete(contactFromDB);
    }
}
