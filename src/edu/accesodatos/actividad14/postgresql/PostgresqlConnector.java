package edu.accesodatos.actividad14.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import edu.accesodatos.actividad14.model.Usuario;

/**
 * Esta clase es la que se va a encargar de conectar y manipular toda las base de datos
 * que podamos tener en PostgreSQL.
 * Tendremos en cuenta que podremos tener mas de una tabla
 * por lo que lo que haremos será hacer metodos génericos para estos casos
 * 
 * @author Daniel Caparros Duran
 * @version 1.0
 * @since 1.0
 */
public class PostgresqlConnector {
    private String host;
    private int puerto;
    private String usuario;
    private String password;
    private String database;

    private Connection conexion;

    /**
     * @param host
     * @param puerto
     * @param usuario
     * @param password
     * @param database
     * @param conexion
     */
    public PostgresqlConnector(String host, int puerto, String usuario, String password, String database) {
        this.host = host;
        this.puerto = puerto;
        this.usuario = usuario;
        this.password = password;
        this.database = database;

        try {
            this.conexion = DriverManager.getConnection("jdbc:postgresql://"+this.host+":"+this.puerto+"/"+this.database, this.usuario, this.password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método select, este metodo lo que toma es una sentencia SQL generada por un asistente
     * como por ejemplo QueryBuilder y la ejecuta. Cuando recibe los datos los mapea en 
     * una estructura de ArrayList génerica
     *
     * ArrayListPrincipal
     *    |
     *    |
     *    |_____ ArrayList
     *    |         |
     *    |         |------- HashMap -| Propiedad
     *    |         |              
     *    |         |------- HashMap -| Propiedad
     *    |
     *    |_____ ArrayList
     *             |
     *             | ... 
     * @param query La sentencia SQL que se va a ejecutar
     * @return El modelo de datos superior con toda la información
     */
    public ArrayList<ArrayList<HashMap<String, Object>>> select(String query){
        ArrayList<ArrayList<HashMap<String, Object>>> resultado = new ArrayList<>();

        try {
            Statement estado = this.conexion.createStatement();
            ResultSet peticionRealizada = estado.executeQuery(query);
            while(peticionRealizada.next()){
                ArrayList<HashMap<String, Object>> listaPropiedades = new ArrayList<>();
                HashMap<String, Object> propiedad = new HashMap<>();

                int cantColumnas = peticionRealizada.getMetaData().getColumnCount();

                for(int i = 1; i <= cantColumnas; i++){
                    Object dataColumna = peticionRealizada.getObject(i);
                    String nombreColumna = peticionRealizada.getMetaData().getColumnName(i);

                    propiedad.put(nombreColumna, dataColumna);
                }

                listaPropiedades.add(propiedad);
                resultado.add(listaPropiedades);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultado;
    } 
}
