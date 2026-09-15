package ni.uam.edu.proyectonavegacion.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ni.uam.edu.proyectonavegacion.RegistroApplication;

import java.io.IOException;

public class LoginController {

    @FXML private Label welcomeText;
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtClave;
    @FXML private Label lblMensaje;
    @FXML private Button confirmarBoton;

    @FXML
    public void btnConfirmar(ActionEvent event) {
        String user = txtUsuario.getText();
        String pass = txtClave.getText();
        
        if ("admin".equals(user) && "admin".equals(pass)) {
            abrirPrincipal();
        } else {
            lblMensaje.setText("Credenciales incorrectas");
        }
    }
    
    private void abrirPrincipal() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(RegistroApplication.class.getResource("principal-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) confirmarBoton.getScene().getWindow();
            stage.setTitle("Ventana Principal");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
