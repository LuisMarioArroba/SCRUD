# Proyecto CRUD con .NET y Spring Boot

Este proyecto consiste en la creación de una aplicación CRUD (Crear, Leer, Actualizar, Eliminar) utilizando dos tecnologías de backend: .NET y Spring Boot, junto con Angular en el frontend. 

## Estructura del Proyecto

El proyecto está organizado de la siguiente manera:

- **/clients**: Implementación del servicio CRUD usando **.NET**. Aquí se gestiona la parte del backend relacionada con los clientes.
- **/products**: Implementación del servicio CRUD usando **Spring Boot**. Esta parte del backend maneja las operaciones relacionadas con los productos.
- **/frontend**: Desarrollado en **Angular** para interactuar con los servicios RESTful proporcionados por los backends de .NET y Spring Boot.
- **/base_de_datos**: Contiene el script SQL necesario para crear la base de datos y las tablas.

## Descripción

Se crearon dos SCRUBs (servicios CRUD) independientes:

1. **Scrub en .NET (`/clients`)**:  
   - Implementación de los servicios RESTful utilizando **.NET**.
   - La API permite realizar operaciones CRUD sobre los clientes.

2. **Scrub en Spring Boot (`/products`)**:  
   - Implementación de los servicios RESTful utilizando **Spring Boot**.
   - La API permite realizar operaciones CRUD sobre los productos.

## Base de Datos

La base de datos utilizada es **SQL Server**, donde se han creado las tablas correspondientes para los clientes y los productos. Ambas tablas fueron generadas mediante un script SQL que se encuentra en la carpeta **`/base_de_datos`**.

### Script para crear la base de datos y las tablas

Puedes encontrar el script SQL necesario en el archivo `script.sql` dentro de la carpeta **`/base_de_datos`**.

## Instrucciones de Uso

### Backend .NET

1. Abre el proyecto `/clients` en tu entorno de desarrollo preferido (Visual Studio o Visual Studio Code).
2. Asegúrate de que la cadena de conexión a la base de datos esté configurada correctamente.
3. Ejecuta el proyecto. Esto pondrá en marcha el servicio RESTful para gestionar los clientes.

### Backend Spring Boot

1. Abre el proyecto `/products` en tu entorno de desarrollo preferido (IntelliJ IDEA, Eclipse, o VSCode).
2. Asegúrate de que la configuración de la base de datos esté correctamente especificada en `application.properties`.
3. Ejecuta el proyecto. Esto pondrá en marcha el servicio RESTful para gestionar los productos.

### Frontend Angular

1. Abre el proyecto frontend en tu editor preferido (Visual Studio Code, IntelliJ IDEA, etc.).
2. Asegúrate de que los endpoints en el frontend estén configurados para interactuar con las APIs de backend de .NET y Spring Boot.
3. Ejecuta el frontend utilizando el comando:

   ```bash
   ng serve
