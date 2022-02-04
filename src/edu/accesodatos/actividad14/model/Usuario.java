package edu.accesodatos.actividad14.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase usuario, esta clase de lo que se va a encargar es de identificar que son los usuarios 
 * en nuestro programa.
 * 
 * @author Daniel Caparros Duran
 * @since 1.0
 * @version 1.0
 */
public class Usuario {
    private String nombre;
    private String apellido;
    private int id;

    public Usuario(String nombre, String apellido, int id){
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
    }

    public String toString(){
        return "Usuario: "+this.nombre+"\nApellido: "+this.apellido+"\nID: "+this.id;
    }

    /**
     * Metodo parseUsuarios, este metodo de lo que se va a encargar es de parsear los datos
     * recibidos por un conector como por ejemplo PostgreSQLConnector y volverlos usuarios
     * Finalmente devolvera el array con todos los usuarios 
     * @param objetos El array con toda la informacion base que ha sido devuelta por el conector al usar el método de consulta
     * @return Una lista con todos los usuarios bien formados
     */
    public static ArrayList<Usuario> parseUsuarios(ArrayList<ArrayList<HashMap<String, Object>>> objetos){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        for (ArrayList<HashMap<String,Object>> objetoConcreto : objetos) {
            Usuario usuario = new Usuario("", "", 1);
            for (int i = 0; i < objetoConcreto.size(); i++) {
                HashMap<String, Object> propiedad = objetoConcreto.get(i);
                for(String nombre : propiedad.keySet()){
                    switch(nombre){
                        case "nombre":
                            usuario.setNombre((String) propiedad.get(nombre));
                            break;
                        case "apellido":
                            usuario.setApellido((String) propiedad.get(nombre));
                            break;
                        case "id":
                            usuario.setId((int) propiedad.get(nombre));
                            break;
                    }
                }         
            }
            usuarios.add(usuario);
        }
        return usuarios;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    
}
