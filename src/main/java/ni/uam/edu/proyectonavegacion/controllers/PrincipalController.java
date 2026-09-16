package ni.uam.edu.proyectonavegacion.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Stage;
import javafx.stage.DirectoryChooser;
import java.io.File;

import ni.uam.edu.proyectonavegacion.DAO.ClienteDAO;
import ni.uam.edu.proyectonavegacion.RegistroApplication;
import ni.uam.edu.proyectonavegacion.util.ClienteExportador;

import java.io.IOException;

public class PrincipalController {

    @FXML
    public void abrirRegistro(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(RegistroApplication.class.getResource("registro-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Registro de Cliente");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista de registro: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void abrirConsulta(ActionEvent event) {
        try {
            java.net.URL fxmlUrl = RegistroApplication.class.getResource("consulta-view.fxml");
            if (fxmlUrl != null) {
                FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Consulta de Clientes");
                stage.setScene(new Scene(root));
                stage.show();
            } else {
                mostrarAlerta("Módulo de Consulta", 
                    "La ventana de consulta de clientes está actualmente en desarrollo por los miembros del equipo.", 
                    Alert.AlertType.INFORMATION);
            }
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo cargar la vista de consulta.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void mostrarInfoDetalle(ActionEvent event) {
        mostrarAlerta("Información Detallada de Pantalla", 
            "Detalles del Estado del Sistema:\n\n" +
            "• Estado actual: Activo y listo para operaciones.\n" +
            "• Ventanas disponibles: Registro y Consulta.\n" +
            "• Conexión a datos: Lista.", 
            Alert.AlertType.INFORMATION);
    }
    @FXML
    public void exportarRespaldo(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Seleccionar carpeta de respaldo");

        File carpetaSeleccionada = directoryChooser.showDialog(null);

        if (carpetaSeleccionada == null) {
            mostrarAlerta("Respaldo", "No se seleccionó ninguna carpeta.", Alert.AlertType.WARNING);
            return;
        }

        try {
            File archivo = ClienteExportador.exportarACsv(
                    ClienteDAO.getInstancia().obtenerRegistro(), carpetaSeleccionada);

            mostrarAlerta("Respaldo Exitoso",
                    "Se exportaron " + ClienteDAO.getInstancia().obtenerRegistro().size() +
                            " cliente(s) a:\n" + archivo.getAbsolutePath(),
                    Alert.AlertType.INFORMATION);

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudo generar el respaldo: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }




    @FXML
    public void salir(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    public void mostrarAcercaDe(ActionEvent event) {
        mostrarAlerta("Acerca del Sistema", "Sistema de Navegación UAM v1.0\nDesarrollado para el proyecto del curso.", Alert.AlertType.INFORMATION);
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
