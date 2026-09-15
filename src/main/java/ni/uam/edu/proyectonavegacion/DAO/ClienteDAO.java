package ni.uam.edu.proyectonavegacion.DAO;

import ni.uam.edu.proyectonavegacion.Interfaces.CRUD;
import ni.uam.edu.proyectonavegacion.modelos.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements CRUD<Cliente> {

    private static ClienteDAO instancia;
    private final List<Cliente> listaClientes;

    private ClienteDAO() {
        listaClientes = new ArrayList<>();
    }

    public static ClienteDAO getInstancia() {
        if (instancia == null) {
            instancia = new ClienteDAO();
        }
        return instancia;
    }

    @Override
    public void agregar(Cliente entidad) {
        listaClientes.add(entidad);
    }

    @Override
    public List<Cliente> obtenerRegistro() {
        return listaClientes;
    }

    @Override
    public void eliminar(Cliente entidad) {
        listaClientes.remove(entidad);
    }
}