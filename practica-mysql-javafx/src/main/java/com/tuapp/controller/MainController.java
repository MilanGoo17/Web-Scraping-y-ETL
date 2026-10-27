package com.tuapp.controller;

import com.tuapp.dao.ProductoDAO;
import com.tuapp.dao.ProductoDAOImpl;
import com.tuapp.model.Categoria;
import com.tuapp.model.Producto;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;


public class MainController {

    @FXML
    private ComboBox<Categoria> comboCategorias;

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, Double> colPrecio;

    @FXML
    private TableColumn<Producto, String> colCategoria;

    private final ProductoDAO productoDAO = new ProductoDAOImpl();
    private final ObservableList<Producto> listaProductos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        configurarColumnasTabla();
        configurarComboBox();
        cargarCategorias();
    }

    private void configurarColumnasTabla() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        tablaProductos.setItems(listaProductos);
    }

    private void configurarComboBox() {
        // StringConverter para mostrar el nombre de la categoría y no la referencia del objeto
        comboCategorias.setConverter(new StringConverter<Categoria>() {
            @Override
            public String toString(Categoria categoria) {
                return (categoria != null) ? categoria.getNombre() : "";
            }

            @Override
            public Categoria fromString(String string) {
                return comboCategorias.getItems().stream()
                        .filter(cat -> cat.getNombre().equals(string))
                        .findFirst()
                        .orElse(null);
            }
        });

        // ChangeListener para filtrar productos al seleccionar una categoría
        comboCategorias.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, nuevaCategoria) -> {
                    if (nuevaCategoria != null) {
                        actualizarTablaProductos(nuevaCategoria.getId());
                    } else {
                        listaProductos.clear();
                    }
                }
        );
    }

    private void cargarCategorias() {
        List<Categoria> categorias = productoDAO.listarCategorias();
        comboCategorias.setItems(FXCollections.observableArrayList(categorias));

        if (!categorias.isEmpty()) {
            comboCategorias.getSelectionModel().selectFirst();
        }
    }

    private void actualizarTablaProductos(int categoriaId) {
        List<Producto> productosFiltrados = productoDAO.listarProductosPorCategoria(categoriaId);
        listaProductos.setAll(productosFiltrados);
    }
}
