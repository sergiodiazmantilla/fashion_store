-- CREACION DE BASE DE DATOS
-- =========================================

CREATE DATABASE IF NOT EXISTS fashion_store_db;
USE fashion_store_db;

-- =========================================
-- TABLA USUARIOS
-- =========================================

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- Usuario administrador por defecto
INSERT INTO usuarios(usuario, password)
VALUES ('admin', '1234');

-- =========================================
-- TABLA CLIENTES
-- =========================================

CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(8) NOT NULL UNIQUE,
    nombre VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    correo VARCHAR(100)
);

-- =========================================
-- TABLA PRODUCTOS
-- =========================================

CREATE TABLE productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,

    -- Polo, Camisa, Pantalon, Calzado
    tipo VARCHAR(50) NOT NULL,
    
    precio DOUBLE NOT NULL,
    stock INT NOT NULL,
    stock_minimo INT NOT NULL,
    talla VARCHAR(10),
    color VARCHAR(50),

    -- Característica específica según el tipo
    atributo VARCHAR(100)
);

-- =========================================
-- TABLA VENTAS
-- =========================================

CREATE TABLE ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    fecha DATETIME NOT NULL,
    total DOUBLE NOT NULL,

    CONSTRAINT fk_venta_cliente
    FOREIGN KEY (cliente_id)
    REFERENCES clientes(id)
);

-- =========================================
-- TABLA DETALLE_VENTA
-- =========================================

CREATE TABLE detalle_venta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    venta_id INT NOT NULL,
    producto_id INT NOT NULL,
    cantidad INT NOT NULL,
    subtotal DOUBLE NOT NULL,

    CONSTRAINT fk_detalle_venta
    FOREIGN KEY (venta_id)
    REFERENCES ventas(id),

    CONSTRAINT fk_detalle_producto
    FOREIGN KEY (producto_id)
    REFERENCES productos(id)
);

-- =========================================
-- ACTUALIZACION POST-EJECUCION
-- =========================================

ALTER TABLE detalle_venta
ADD COLUMN precio_unitario DECIMAL(10,2);

-- =========================================
-- DATOS DE PRUEBA (OPCIONAL)
-- =========================================

INSERT INTO clientes(dni, nombre, telefono, correo)
VALUES
('12345678', 'Juan Perez', '987654321', 'juan@gmail.com'),
('87654321', 'Maria Lopez', '999888777', 'maria@gmail.com');

INSERT INTO productos
(nombre, tipo, precio, stock, stock_minimo, talla, color, atributo)
VALUES
('Polo Nike', 'Polo', 59.90, 20, 5, 'M', 'Negro', 'Manga corta'),
('Camisa Zara', 'Camisa', 89.90, 15, 5, 'L', 'Blanco', 'Cuello italiano'),
('Pantalon Levis', 'Pantalon', 120.00, 10, 3, '32', 'Azul', 'Denim'),
('Adidas Run', 'Calzado', 199.90, 8, 2, '42', 'Blanco', 'Deportivo');

-- =========================================
-- CONSULTAS DE PRUEBA
-- =========================================

-- Ver productos
SELECT * FROM productos;

-- Ver clientes
SELECT * FROM clientes;

-- Ver ventas
SELECT * FROM ventas;

-- Ver detalle ventas
SELECT * FROM detalle_venta;
