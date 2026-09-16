package ni.uam.edu.proyectonavegacion.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import ni.uam.edu.proyectonavegacion.modelos.Cliente;

public class DetalleController {

    @FXML private ImageView imgFoto;
    @FXML private Label lblCodigo;
    @FXML private Label lblNombres;
    @FXML private Label lblApellidos;
    @FXML private Label lblTipoCliente;
    @FXML private Label lblCiudad;
    @FXML private Label lblFechaNacimiento;
    @FXML private Label lblTipoSolicitud;
    @FXML private Label lblServicios;

    public void cargarCliente(Cliente cliente) {
        lblCodigo.setText("Código: " + cliente.getCodigo());
        lblNombres.setText("Nombres: " + cliente.getNombres());
        lblApellidos.setText("Apellidos: " + cliente.getApellidos());
        lblTipoCliente.setText("Tipo de cliente: " + cliente.getTipoCliente());
        lblCiudad.setText("Ciudad: " + cliente.getCiudad());

        String fecha = (cliente.getFechaNacimiento() != null)
                ? cliente.getFechaNacimiento().toString() : "No especificada";
        lblFechaNacimiento.setText("Fecha de nacimiento: " + fecha);

        String solicitud = (cliente.getTipoSolicitud() != null)
                ? cliente.getTipoSolicitud().getText() : "No especificado";
        lblTipoSolicitud.setText("Tipo de solicitud: " + solicitud);

        lblServicios.setText("Servicios de interés: " + (cliente.isServiciosInteres() ? "Sí" : "No"));

        if (cliente.getFoto() != null) {
            imgFoto.setImage(cliente.getFoto());
        }
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) lblCodigo.getScene().getWindow();
        stage.close();
    }
}