package com.tuapp.dao;

import com.tuapp.model.Categoria;
import com.tuapp.model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAOImpl implements ProductoDAO {

    @Override
    public List<Categoria> listarCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT id, nombre FROM categorias";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                categorias.add(new Categoria(id, nombre));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar categorias: " + e.getMessage());
        }

        return categorias;
    }

    @Override
    public List<Producto> listarProductosPorCategoria(int categoriaId) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT p.id, p.nombre, p.precio, c.nombre AS cat "
                   + "FROM productos p "
                   + "JOIN categorias c ON p.categoria_id = c.id "
                   + "WHERE p.categoria_id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Uso de PreparedStatement para evitar inyección SQL
            pstmt.setInt(1, categoriaId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("nombre");
                    double precio = rs.getDouble("precio");
                    String nombreCategoria = rs.getString("cat");

                    productos.add(new Producto(id, nombre, precio, nombreCategoria));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al listar productos por categoria: " + e.getMessage());
        }

        return productos;
    }
}
