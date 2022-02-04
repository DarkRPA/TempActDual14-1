package edu.accesodatos.actividad14;

import java.util.ArrayList;
import java.util.HashMap;

import edu.accesodatos.actividad14.model.Usuario;
import edu.accesodatos.actividad14.mysql.MySQLConnector;
import edu.accesodatos.actividad14.postgresql.PostgresqlConnector;
import edu.accesodatos.actividad14.util.QueryBuilder;

public class Inicio {
    public static void main(String[] args) {
        PostgresqlConnector conector = new PostgresqlConnector("localhost", 5432, "postgres", "usuario", "AccesoDatos14-1");
        MySQLConnector connectorMySQL = new MySQLConnector("localhost", 3306, "root", "usuario", "actividadacceso14-1");

        //Sentencia 1 Postgres
        QueryBuilder queryBuilder = new QueryBuilder();
        String query = queryBuilder.select("usuarios").build();
        ArrayList<ArrayList<HashMap<String, Object>>> todo = conector.select(query);
        ArrayList<Usuario> usuarios = Usuario.parseUsuarios(todo);
        
        //Sentencia 2 Postgres
        QueryBuilder queryBuilder2 = new QueryBuilder();
        ArrayList<String> condicionales = new ArrayList<>();
        condicionales.add("id = 1");
        String query2 = queryBuilder2.select("usuarios").where(condicionales, null).build();

        ArrayList<ArrayList<HashMap<String, Object>>> todo2 = conector.select(query2);
        ArrayList<Usuario> usuarios2 = Usuario.parseUsuarios(todo2);

        //Sentencia 1 MySQL
        QueryBuilder queryBuilderMySQL1 = new QueryBuilder();
        ArrayList<String> valores1 = new ArrayList<>();
        valores1.add("nombre = 'Pepito'");
        String queryMySQL1 = queryBuilderMySQL1.update("usuarios", valores1).build();

        connectorMySQL.modificar(queryMySQL1);

        //Sentencia 2 MySQL
        QueryBuilder queryBuilderMySQL2 = new QueryBuilder();
        ArrayList<String> valores2 = new ArrayList<>();
        ArrayList<String> condicionalesMySQL = new ArrayList<>();
        condicionalesMySQL.add("id = '2'");
        valores2.add("nombre = 'AAAAA'");

        String queryMySQL2 = queryBuilderMySQL2.update("usuarios", valores2).where(condicionalesMySQL, null).build();

        connectorMySQL.modificar(queryMySQL2);
        System.out.println("");
    }
}
