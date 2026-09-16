module ni.uam.edu.proyectonavegacion {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens ni.uam.edu.proyectonavegacion to javafx.fxml;
    opens ni.uam.edu.proyectonavegacion.modelos to javafx.base;
    exports ni.uam.edu.proyectonavegacion;
    exports ni.uam.edu.proyectonavegacion.controllers;
    opens ni.uam.edu.proyectonavegacion.controllers to javafx.fxml;
}