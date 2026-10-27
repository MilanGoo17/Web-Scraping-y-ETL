package com.tuapp.dao;

import com.tuapp.model.Categoria;
import com.tuapp.model.Producto;
import java.util.List;

public class TestDatos {

    public static void main(String[] args) {
        System.out.println("=== 1. PROBANDO LISTAR CATEGORÍAS ===");
        ProductoDAO dao = new ProductoDAOImpl();
        List<Categoria> categorias = dao.listarCategorias();
        System.out.println("Categorías encontradas: " + categorias.size());
        for (Categoria c : categorias) {
            System.out.println(" - ID: " + c.getId() + " | Nombre: " + c.getNombre());
        }

        System.out.println("\n=== 2. PROBANDO LISTAR PRODUCTOS (Categoría 1) ===");
        List<Producto> productos = dao.listarProductosPorCategoria(1);
        System.out.println("Productos encontrados para categoría 1: " + productos.size());
        for (Producto p : productos) {
            System.out.println(" - ID: " + p.getId() + " | Nombre: " + p.getNombre() + " | Precio: " + p.getPrecio() + " | Categoría: " + p.getCategoria());
        }
    }
}
