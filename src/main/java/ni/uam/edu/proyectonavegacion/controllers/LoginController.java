package ni.uam.edu.proyectonavegacion.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ni.uam.edu.proyectonavegacion.RegistroApplication;

import java.io.IOException;

public class LoginController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtClave;
    @FXML private Label lblMensaje;
    @FXML private Button confirmarBoton;

    @FXML
    public void btnConfirmar(ActionEvent event) {
        String usuario = txtUsuario.getText().trim();
        String clave = txtClave.getText();

        if (usuario.isBlank() || clave.isBlank()) {
            lblMensaje.setText("");

            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Datos incompletos");
            alerta.setHeaderText(null);
            alerta.setContentText("Ingresá el usuario y la contraseña para continuar.");
            alerta.showAndWait();
            return;
        }

        if ("admin".equals(usuario) && "admin".equals(clave)) {
            lblMensaje.setText("");
            abrirPrincipal();
        } else {
            lblMensaje.setText("Credenciales incorrectas");
        }
    }

    @FXML
    public void btnSalir(ActionEvent event) {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Salir de la aplicación");
        confirmacion.setHeaderText("Confirmar salida");
        confirmacion.setContentText("¿Querés cerrar la aplicación?");

        if (confirmacion.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            Platform.exit();
        }
    }

    private void abrirPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    RegistroApplication.class.getResource("principal-view.fxml")
            );
            Parent root = loader.load();

            Stage stage = (Stage) confirmarBoton.getScene().getWindow();
            stage.setTitle("Ventana Principal");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}