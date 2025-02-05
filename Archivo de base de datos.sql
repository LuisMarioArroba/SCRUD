-- Crear la base de datos Learn
CREATE DATABASE Learn;
GO

-- Usar la base de datos Learn
USE Learn;
GO

-- Crear la tabla Contact
CREATE TABLE dbo.Contact (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(100),
    register_at DATETIME,
    username VARCHAR(50),
    password VARCHAR(100)
);
GO

-- Crear la tabla Product
CREATE TABLE dbo.Product (
    id INT IDENTITY(1,1) PRIMARY KEY,
    codigo VARCHAR(50),
    name VARCHAR(100),
    precio DECIMAL(10, 2),
    cantidad INT
);
GO

-- Procedimiento almacenado sp_ListaClientes
CREATE PROCEDURE sp_ListaClientes
AS
BEGIN
    SELECT * FROM dbo.Contact;
END;
GO

-- Procedimiento almacenado sp_GetClient
CREATE PROCEDURE sp_GetClient (@Id INT)
AS
BEGIN
    SELECT * FROM dbo.Contact WHERE id = @Id;
END;
GO

-- Procedimiento almacenado sp_ProtClient
CREATE PROCEDURE sp_ProtClient
    @name VARCHAR(50),
    @email VARCHAR(100),
    @register_at DATETIME,
    @username VARCHAR(50),
    @password VARCHAR(100)
AS
BEGIN
    INSERT INTO dbo.Contact (name, email, register_at, username, password)
    VALUES (@name, @email, @register_at, @username, @password);
END;
GO

-- Procedimiento almacenado sp_PutClient
CREATE PROCEDURE sp_PutClient
    @id INT,
    @name VARCHAR(50),
    @email VARCHAR(100),
    @username VARCHAR(50),
    @password VARCHAR(100)
AS
BEGIN
    UPDATE dbo.Contact
    SET name = @name,
        email = @email,
        username = @username,
        password = @password
    WHERE id = @id;
END;
GO

-- Procedimiento almacenado sp_DeleteClient
CREATE PROCEDURE sp_DeleteClient
    @id INT
AS
BEGIN
    SET NOCOUNT ON;

    -- Verificar si el ID existe antes de eliminar
    IF EXISTS (SELECT 1 FROM dbo.Contact WHERE id = @id)
    BEGIN
        DELETE FROM dbo.Contact WHERE id = @id;
        PRINT 'Registro eliminado correctamente.';
    END
    ELSE
    BEGIN
        PRINT 'No se encontró un registro con ese ID.';
    END
END;