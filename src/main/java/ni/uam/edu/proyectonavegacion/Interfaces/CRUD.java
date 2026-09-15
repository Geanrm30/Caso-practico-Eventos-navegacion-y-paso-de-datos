package ni.uam.edu.proyectonavegacion.Interfaces;

import java.util.List;

public interface CRUD <T>{
    public void agregar(T entidad);
    public List<T> obtenerRegistro();
    public void eliminar(T entidad);

}
