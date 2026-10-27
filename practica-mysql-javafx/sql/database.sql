
CREATE DATABASE IF NOT EXISTS practica_bd_fx;
USE practica_bd_fx;

CREATE TABLE IF NOT EXISTS categorias (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS productos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    categoria_id INT,
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);

INSERT INTO categorias (nombre) VALUES 
('Electrónica'), 
('Ropa'),
('Alimentos');

INSERT INTO productos (nombre, precio, categoria_id) VALUES
('Laptop', 1200.50, 1),
('Camiseta', 15.99, 2),
('Manzana', 0.50, 3),
('Audífonos', 80.00, 1);
