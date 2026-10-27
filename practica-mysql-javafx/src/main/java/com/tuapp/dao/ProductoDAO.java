package com.tuapp.dao;

import com.tuapp.model.Categoria;
import com.tuapp.model.Producto;
import java.util.List;

public interface ProductoDAO {

    List<Categoria> listarCategorias();

    List<Producto> listarProductosPorCategoria(int categoriaId);
}
