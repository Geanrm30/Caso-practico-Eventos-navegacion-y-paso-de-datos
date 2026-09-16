package ni.uam.edu.proyectonavegacion.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ni.uam.edu.proyectonavegacion.DAO.ClienteDAO;
import ni.uam.edu.proyectonavegacion.RegistroApplication;
import ni.uam.edu.proyectonavegacion.modelos.Cliente;

import java.io.IOException;

public class ConsultaController {

    @FXML private TableView<Cliente> tablaClientes;
    @FXML private TableColumn<Cliente, String> colCodigo;
    @FXML private TableColumn<Cliente, String> colNombres;
    @FXML private TableColumn<Cliente, String> colApellidos;
    @FXML private TableColumn<Cliente, String> colTipoCliente;
    @FXML private TableColumn<Cliente, String> colCiudad;
    @FXML private TableColumn<Cliente, String> colFechaNacimiento;
    @FXML private TableColumn<Cliente, String> colTipoSolicitud;
    @FXML private Label lblEstado;

    private final ClienteDAO clienteDAO = ClienteDAO.getInstancia();

    @FXML
    public void initialize() {
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        colApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        colTipoCliente.setCellValueFactory(new PropertyValueFactory<>("tipoCliente"));
        colCiudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));

        colFechaNacimiento.setCellValueFactory(datos -> {
            var fecha = datos.getValue().getFechaNacimiento();
            String texto = (fecha != null) ? fecha.toString() : "";
            return new javafx.beans.property.SimpleStringProperty(texto);
        });

        colTipoSolicitud.setCellValueFactory(datos -> {
            RadioButton rb = datos.getValue().getTipoSolicitud();
            String texto = (rb != null) ? rb.getText() : "";
            return new javafx.beans.property.SimpleStringProperty(texto);
        });

        ObservableList<Cliente> clientes = FXCollections.observableArrayList(clienteDAO.obtenerRegistro());
        tablaClientes.setItems(clientes);
    }

    @FXML
    private void onTablaClick(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();
            if (seleccionado == null) {
                lblEstado.setText("Seleccione un cliente antes de hacer doble clic.");
                return;
            }
            abrirDetalle(seleccionado);
        }
    }

    private void abrirDetalle(Cliente cliente) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    RegistroApplication.class.getResource("detalle-view.fxml"));
            Parent root = loader.load();

            DetalleController controller = loader.getController();
            controller.cargarCliente(cliente);

            Stage stage = new Stage();
            stage.setTitle("Detalle del Cliente");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("No se pudo abrir la ventana de detalle: " + e.getMessage());
        }
    }

    @FXML
    private void volverAlMenu(ActionEvent event) {
        Stage stage = (Stage) tablaClientes.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR, mensaje);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }
}