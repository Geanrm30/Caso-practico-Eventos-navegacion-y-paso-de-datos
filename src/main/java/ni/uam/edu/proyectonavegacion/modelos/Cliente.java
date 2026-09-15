package ni.uam.edu.proyectonavegacion.modelos;

import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@NoArgsConstructor@AllArgsConstructor
@Setter@Getter
public class Cliente {
    private String codigo;
    private String nombres;
    private String apellidos;
    private String tipoCliente;
    private String ciudad;
    private LocalDate fechaNacimiento;
    private RadioButton tipoSolicitud;
    private boolean serviciosInteres;
    private Image foto;

}
