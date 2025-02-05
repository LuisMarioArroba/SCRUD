package com.crud.hellow;
import com.crud.hellow.service.conexionService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.JdbcTemplate;

import com.crud.hellow.entity.Contact;
import com.crud.hellow.repository.ContactRepository;

import com.crud.hellow.entity.Product;
import com.crud.hellow.repository.ProductRepository;

@SpringBootApplication
public class HellowApplication {

	/*@Autowired
	private JdbcTemplate jdbcTemplate;*/


	public static void main(String[] args) {
		SpringApplication.run(HellowApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(ContactRepository contactRepository, ProductRepository productRepository){
		Connection _con;
		_con = new conexionService().conexionBD();

		return (String ... args) -> {
				List<Contact> contactList = Arrays.asList(
				new Contact("Carlos", "carlos@gmail.com", LocalDateTime.now()),
				new Contact("Pepe","pepe@gmail.com",LocalDateTime.now()),
				new Contact("Juan", "juan@gmail.com", LocalDateTime.now()),
				new Contact("Camila","camila@gmail.com",LocalDateTime.now())
				);
				contactRepository.saveAll(contactList);
				try {
					Statement stnt = _con.createStatement();
					String query = "select * from dbo.Product";
					Product[] productos = new Product[5000];
    				int size = 0;  // Contador para el número de productos en el array
					ResultSet result = stnt.executeQuery(query);
					while (result.next()){
						Product producto =new Product(result.getInt("codigo"),result.getString("name"),result.getFloat("precio"),result.getInt("cantidad"));
						if (size == productos.length) {
							// Si el array está lleno, creamos un array más grande
							Product[] newArray = new Product[productos.length + 1];
							System.arraycopy(productos, 0, newArray, 0, productos.length);
							productos = newArray;  // Redirigimos productos al nuevo array
						}
						
						productos[size] = producto;  // Agregar el producto en la siguiente posición
						size++;  // Aumentamos el contador de productos
					}
					result.close();
					stnt.close();
					productRepository.saveAll(Arrays.asList(Arrays.copyOf(productos, size)));
				}catch(Exception exception){
					System.out.println("Error al ejecutar el query");
					int x =1;
				}
				/* 
				List<Product> productList = Arrays.asList(
				new Product(6595124,"Cortadora de pasto",1200,30),
				new Product(9618510,"Fertilizante 10 Kg",100,240),
				new Product(2062614,"Rosalias 100 unidades",240,100),
				new Product(5051176,"Fertilizante 5 Kg",60,120),
				new Product(7442012 ,"Luces exterior bateria solar 4 unidades",45,80)
				);
				productRepository.saveAll(productList);
				*/
		};
	}
}


