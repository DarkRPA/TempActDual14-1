package edu.accesodatos.actividad14;

import java.util.ArrayList;
import java.util.Random;

import com.matisse.MtDatabase;
import com.matisse.MtObjectFactory;
import com.matisse.MtPackageObjectFactory;

import edu.accesodatos.actividad14.matisse.MatisseController;
import edu.accesodatos.actividad14.mysql.MySQLConnector;
import edu.accesodatos.actividad14.util.QueryBuilder;
import modelos.Usuario;

/**
 * Clase Inicio, esta es la clase de prueba y la clase donde se van a realizar las consultas
 * especificadas en la actividad. Se haran 2 consultas con PostgreSQL y 2 modificaciones con
 * MySQL
 * 
 * @author Daniel Caparros Duran
 * @since 1.0
 * @version 1.0
 */
public class Inicio {
    public static void main(String[] args) {
        //Primero insertamos 5 valores aleatorios en la base de datos MySQL
        //Para ello usamos un bucle y un metodo que genere los datos
        MySQLConnector conectorMySQL = new MySQLConnector("localhost", 3306, "root", "usuario", "actividadacceso14-1");
        for(int i = 0; i < 5; i++){
            ArrayList<Object> valores = new ArrayList<>();
            valores.add(null);
            valores.add(Inicio.generateRandomNames(10));
            valores.add(Inicio.generateRandomNames(10));
            QueryBuilder query = new QueryBuilder().insert("usuarios", valores);
            conectorMySQL.insertar(query.build());            
        }

        //Luego haciendo uso del metodo eliminarPrimeros eliminamos unicamente 5 registros de la base
        //matisse
        MatisseController controladorMatisse = new MatisseController("example", "modelos");
        controladorMatisse.eliminarPrimeros(5);
    }

    /**
     * Metodo generateRandomNames. Este metodo de lo que se encarga es de generar un string de caracteres aleatorios que
     * podamos utilizar como nombre de prueba
     * @param cantLetras
     * @return
     */
    public static String generateRandomNames(int cantLetras){
        String nombre = "";
        Random random = new Random();
        for(int i = 0; i < cantLetras; i++){
            nombre += ((char) (random.nextInt(65)+57))+"";
        }

        return nombre;
    }
}
