package edu.accesodatos.actividad14.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 */
public class MySQLConnector {
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
    public MySQLConnector(String host, int puerto, String usuario, String password, String database) {
        this.host = host;
        this.puerto = puerto;
        this.usuario = usuario;
        this.password = password;
        this.database = database;

        try {
            this.conexion = DriverManager.getConnection("jdbc:mysql://"+this.host+":"+this.puerto+"/"+this.database, this.usuario, this.password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modificar(String query){
        try {
            Statement estado = this.conexion.createStatement();
            estado.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
