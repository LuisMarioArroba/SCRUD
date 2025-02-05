package com.crud.hellow.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionService {
    public Connection conexionBD(){
        try{
            // Cargar el driver JDBC
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // URL de conexión corregida
            String connectionUrl = "jdbc:sqlserver://localhost:1433;"
                    + "databaseName=Learn;"
                    + "user=sa;"
                    + "password=1234567;"
                    + "encrypt=true;"
                    + "trustServerCertificate=true;";
            Connection con = DriverManager.getConnection(connectionUrl);
            return con;
        }catch(SQLException  e){
            System.out.println("Error al conectarse a la base de datos");
            int x = 1;
        }
        catch(ClassNotFoundException cnfex){
            int x= 1;
        }
                return null;
    }
}
