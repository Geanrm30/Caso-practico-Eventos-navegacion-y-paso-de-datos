package ni.uam.edu.proyectonavegacion.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import ni.uam.edu.proyectonavegacion.DAO.ClienteDAO;
import ni.uam.edu.proyectonavegacion.modelos.Cliente;

import java.time.LocalDate;

public class RegistroController {
    private Image imagenTemporal;
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombres;
    @FXML private TextField txtApellidos;
    @FXML private ComboBox<String> cmbTipoCliente;
    @FXML private TextField txtCiudad;
    @FXML private DatePicker dpFechaNacimiento;
    @FXML private ToggleGroup tgTipoSolicitud;
    @FXML private CheckBox chkServiciosInteres;
    @FXML private ImageView imgFoto;

    private ClienteDAO clienteDAO = ClienteDAO.getInstancia();

    @FXML
    public void initialize() {
        if (cmbTipoCliente != null) {
            cmbTipoCliente.getItems().addAll("Regular", "VIP", "Corporativo");
        }
    }

    @FXML
    public void guardarCliente(ActionEvent event) {
        if (!validarCampos()) {
            return;
        }
        String codigo = txtCodigo.getText();
        String nombres = txtNombres.getText();
        String apellidos = txtApellidos.getText();
        String tipoCliente = cmbTipoCliente.getValue();
        String ciudad = txtCiudad.getText();
        LocalDate fechaNacimiento = dpFechaNacimiento.getValue();
        RadioButton tipoSolicitud = (RadioButton) tgTipoSolicitud.getSelectedToggle();
        
        boolean serviciosInteres = chkServiciosInteres.isSelected();
        Image foto = imagenTemporal;

        Cliente nuevoCliente = new Cliente(codigo, nombres, apellidos, tipoCliente, ciudad, fechaNacimiento, tipoSolicitud, serviciosInteres, foto);
        
        clienteDAO.agregar(nuevoCliente);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registro Exitoso");
        alert.setHeaderText(null);
        alert.setContentText("El cliente ha sido registrado correctamente.");
        alert.showAndWait();

        limpiarFormulario();
    }
    @FXML private void seleccionarImagen(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar fotografía del producto");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg")
        );

        java.io.File archivoElegido = fileChooser.showOpenDialog(txtNombres.getScene().getWindow());

        if (archivoElegido != null) {

            Image imagen = new Image(archivoElegido.toURI().toString());

            imagenTemporal = imagen;
            imgFoto.setImage(imagen);

        }
    }

    @FXML
    public void limpiarFormulario() {
        if (txtCodigo != null) txtCodigo.clear();
        if (txtNombres != null) txtNombres.clear();
        if (txtApellidos != null) txtApellidos.clear();
        if (cmbTipoCliente != null) cmbTipoCliente.getSelectionModel().clearSelection();
        if (txtCiudad != null) txtCiudad.clear();
        if (dpFechaNacimiento != null) dpFechaNacimiento.setValue(null);
        if (tgTipoSolicitud != null) tgTipoSolicitud.selectToggle(null);
        if (chkServiciosInteres != null) chkServiciosInteres.setSelected(false);
        if (imgFoto != null) imgFoto.setImage(null);
    }

    private boolean validarCampos() {
        if (txtCodigo == null || txtCodigo.getText() == null || txtCodigo.getText().trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Campo vacío", "Debe ingresar el código del cliente.");
            return false;
        }

        if (txtNombres == null || txtNombres.getText() == null || txtNombres.getText().trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Campo vacío", "Debe ingresar los nombres del cliente.");
            return false;
        }

        if (txtApellidos == null || txtApellidos.getText() == null || txtApellidos.getText().trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Campo vacío", "Debe ingresar los apellidos del cliente.");
            return false;
        }

        if (cmbTipoCliente == null || cmbTipoCliente.getValue() == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Campo Vacío", "Debe seleccionar un tipo de cliente.");
            return false;
        }

        if (txtCiudad == null || txtCiudad.getText() == null || txtCiudad.getText().trim().isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Campo vacío", "Debe ingresar la ciudad del cliente.");
            return false;
        }

        if (dpFechaNacimiento == null || dpFechaNacimiento.getValue() == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Campo Vacío", "Debe seleccionar la fecha de nacimiento.");
            return false;
        } else {
            java.time.Period edad = java.time.Period.between(dpFechaNacimiento.getValue(), LocalDate.now());
            if (edad.getYears() < 18) {
                mostrarAlerta(Alert.AlertType.ERROR, "Edad Inválida", "El cliente debe ser mayor de 18 años.");
                return false;
            }
        }

        if (tgTipoSolicitud == null || tgTipoSolicitud.getSelectedToggle() == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Campo vacío", "Debe seleccionar un tipo de solicitud.");
            return false;
        }

        if(imagenTemporal == null) {
            mostrarAlerta(Alert.AlertType.ERROR, "Campo vacío", "Debe de seleccionar una foto del cliente");
            return false;
        }

        return true;
    }
    public void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje){
        Alert alerta = new Alert(tipo);
        alerta.setHeaderText(null);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    private void volverAlMenu(ActionEvent event) {
        javafx.stage.Stage stage = (javafx.stage.Stage) txtCodigo.getScene().getWindow();
        stage.close();
    }
}
