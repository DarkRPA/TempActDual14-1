package edu.accesodatos.actividad14.util;

/**
 * Clase fragment, esta clase lo unico que hara será servir como bloque de construcción
 * para QueryBuilder
 * 
 * @author Daniel Caparros Duran
 * @since 1.0
 * @version 1.0
 */
public class Fragment {
    private String fragmento;

    public Fragment(String fragment){
        this.fragmento = fragment;
    }

    public String toString(){
        return this.fragmento;
    }
}
