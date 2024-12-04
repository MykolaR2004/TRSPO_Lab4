module org.example.trspo_lab4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires jakarta.jakartaee.web.api;
    requires spring.data.jpa;
    requires java.sql;


    opens org.example.trspo_lab4 to javafx.fxml;
    exports org.example.trspo_lab4;
    exports org.example.trspo_lab4.model;
    opens org.example.trspo_lab4.model to javafx.fxml;
    exports org.example.trspo_lab4.controller;
    opens org.example.trspo_lab4.controller to javafx.fxml;
}