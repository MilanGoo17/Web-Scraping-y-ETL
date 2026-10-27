module practica.mysql.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.tuapp.controller to javafx.fxml;
    opens com.tuapp.model to javafx.base;
    opens com.tuapp.view to javafx.fxml, javafx.graphics;

    exports com.tuapp.view;
    exports com.tuapp.model;
    exports com.tuapp.controller;
    exports com.tuapp.dao;
}

