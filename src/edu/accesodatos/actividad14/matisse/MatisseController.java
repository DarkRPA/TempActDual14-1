package edu.accesodatos.actividad14.matisse;

import com.matisse.MtDatabase;
import com.matisse.MtObjectIterator;
import com.matisse.MtPackageObjectFactory;

import modelos.Usuario;

/**
 * Controlador Matisse, esta clase es la encargada de la conexion con la base de datos matisse
 * y es la que posee una serie de metodos encargado del manejo de la informacion.
 * Como por ejemplo la eliminacion de datos.
 * 
 * @author Daniel Caparros Duran
 * @since 1.0
 * @version 1.0
 */
public class MatisseController {

    private MtDatabase db;
    
    /**
     * Constructor especifico para poder hacer un objeto de la clase
     * matiise controller.
     * Necesitamos de la base de datos a la que se va a conectar
     * y el paquete que va a utilizar
     * @param db La base de datos a la que se va a conectar
     * @param paquete El paquete que se va a utilizar
     */
    public MatisseController(String db, String paquete){
        this.db = new MtDatabase("localhost", db, new MtPackageObjectFactory("", paquete));
        this.db.open();
    }

    /**
     * metodo encargado de eliminar todos los objetos que hay en la tabla Usuario de la base de datos.
     */
    public void eliminarTodo(){
        this.db.startTransaction();
        MtObjectIterator<Usuario> usuarios = Usuario.instanceIterator(this.db);
        while(usuarios.hasNext()){
            Usuario usuario = usuarios.next();
            usuario.remove();
        }
        this.db.commit();
    }

    /**
     * Metodo encargado de eliminar unicamente los X primeros elementos de la tabla de la base de datos
     * @param cantidadEliminar La cantidad a eliminar, puede ser cualquier numero
     */
    public void eliminarPrimeros(int cantidadEliminar){
        this.db.startTransaction();
        MtObjectIterator<Usuario> usuarios = Usuario.instanceIterator(this.db);
        int eliminados = 0;
        while(usuarios.hasNext() && eliminados < cantidadEliminar){
            Usuario usuario = usuarios.next();
            usuario.remove();
            eliminados++;
        }
        this.db.commit();
    }
}
