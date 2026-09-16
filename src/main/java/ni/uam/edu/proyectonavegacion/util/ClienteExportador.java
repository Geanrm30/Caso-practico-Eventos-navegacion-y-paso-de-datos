package ni.uam.edu.proyectonavegacion.util;

import ni.uam.edu.proyectonavegacion.modelos.Cliente;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ClienteExportador {

    public static File exportarACsv(List<Cliente> clientes, File carpetaDestino) throws IOException {
        File archivoRespaldo = new File(carpetaDestino, "respaldo_clientes.csv");

        try (FileWriter writer = new FileWriter(archivoRespaldo)) {
            writer.write("Codigo,Nombres,Apellidos,TipoCliente,Ciudad,FechaNacimiento\n");

            for (Cliente cliente : clientes) {
                writer.write(String.format("%s,%s,%s,%s,%s,%s%n",
                        cliente.getCodigo(),
                        cliente.getNombres(),
                        cliente.getApellidos(),
                        cliente.getTipoCliente(),
                        cliente.getCiudad(),
                        cliente.getFechaNacimiento() != null ? cliente.getFechaNacimiento().toString() : ""));
            }
        }

        return archivoRespaldo;
    }
}