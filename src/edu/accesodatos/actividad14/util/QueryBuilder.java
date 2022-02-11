package edu.accesodatos.actividad14.util;

import java.util.ArrayList;

/**
 * Clase QueryBuilder, esta clase de lo que se encarga es de asistir al usuario en la creacion
 * de sentencias SQL en Java ofrenciendo una programación parecida a la de bloques creando
 * la sentencia a base de concatenar los métodos que requiere.
 * El orden es importante y construirla mal generará una excecpción
 * 
 * @author Daniel Caparros Duran
 * @since 1.0
 * @version 1.0
 */
public class QueryBuilder {
    private ArrayList<Fragment> fragmentos;

    public QueryBuilder(){
        this.fragmentos = new ArrayList<>();
    }

    /**
     * Añade una sentencia SELECT a la query
     * @param tabla La tabla que se va a consultar
     * @param campos Las columnas que solo queremos obtener, dejar a NULL para obtener todos los campos
     * @return Una referencia al mismo objeto
     */
    public QueryBuilder select(String tabla, String ... campos){
        String query = "select ";
        
        if(campos.length == 0){
            query += "* ";
        }else{
            for(int i = 0; i < campos.length; i++){
                if(i+1 >= campos.length){
                    query += campos[i]+" ";
                }else{
                    query += campos[i]+", ";
                }
            }
        }

        query += "from "+tabla+" ";
        Fragment fragment = new Fragment(query);
        this.fragmentos.add(fragment);
        return this;
    }

    /**
     * Añade una sentencia UPDATE a la query
     * @param tabla La tabla que se va a editar
     * @param datos Los valores que se van a establecer en el formato "<columna> = <valor>"
     * @return
     */
    public QueryBuilder update(String tabla, ArrayList<String> datos){
        String resultado = "update "+tabla+" set ";
        for(int i = 0; i < datos.size(); i++){
            if(i+1 >= datos.size()){
                resultado += datos.get(i)+" ";
            }else{
                resultado += datos.get(i)+", ";
            }
        }

        this.fragmentos.add(new Fragment(resultado));
        return this;
    }

    /**
     * Añade un DELETE a la consula
     * @param tabla La tabla desde la que se va a borrar
     * @return
     */
    public QueryBuilder delete(String tabla){
        String resultado = "DELETE FROM "+tabla;
        this.fragmentos.add(new Fragment(resultado));
        return this;
    }

    /**
     * Añade una sentencia leftJoin para poder consultar tablas relacionadas
     * @param tablas Las tablas que se van a añadir al leftjoin
     * @param keys Las key en las que estan relacionadas
     * @return
     */
    public QueryBuilder leftJoin(ArrayList<String> tablas, ArrayList<String> keys){
        String query = "";
        for(int i = 0; i < tablas.size(); i++){
            String tabla = tablas.get(i);
            String key = keys.get(i);

            query += "left join "+tabla+" on "+key+" ";
        }

        Fragment fragment = new Fragment(query);
        this.fragmentos.add(fragment);
        return this;
    }

    /**
     * Añade una clausula WHERE a la query
     * @param condicionales Una lista de condicionales con formato <columna> <operador> <valor> Ej id = 1
     * @param operadoresLogicos Una lista con los operadores logicos en caso de que queramos concatenar multiples condicionales, el array debe de ser X-1 teniendo en cuenta que X es el tamaño del array de condicionales
     * @return
     */
    public QueryBuilder where(ArrayList<String> condicionales, ArrayList<String> operadoresLogicos){
        String query = "where ";
        for(int i = 0; i < condicionales.size(); i++){
            String condicional = condicionales.get(i);
            String operador = (operadoresLogicos==null)?"":operadoresLogicos.get(i);

            if(i >= condicionales.size()){
                query += condicional+" "+operador;
            }else{
                query += condicional+" ";
            }
        }

        Fragment fragment = new Fragment(query);
        this.fragmentos.add(fragment);
        return this;
    }

    /**
     * Añade un LIMIT a la query
     * @param cantidad La cantidad que vamos a coger
     * @return
     */
    public QueryBuilder limit(int cantidad){
        return this.limit(cantidad, -1);
    }

    /**
     * Añade un LIMIT a la query
     * @param cantidad La cantidad que vamos a coger
     * @param desde Desde donde vamos a comenzar a coger
     * @return
     */
    public QueryBuilder limit(int cantidad, int desde){
        String query = "limit "+cantidad+((desde==-1)?" ":", "+desde+" ");
        

        Fragment fragment = new Fragment(query);
        this.fragmentos.add(fragment);
        return this;
    }

    /**
     * Metodo insert, este metodo lo que realiza es la creacion de una sentencia insert
     * en el queryBuilder, como insert no necesita ninguna sentencia WHERE ni nada por el estilo
     * deberia de ser usado solo y construirse despues de usarse
     * @param tabla La tabla en la que se van a insertar los datos
     * @param values Los valores que va a tomar esa fila
     * @return Una instancia de si mismo para seguir concatenando
     */
    public QueryBuilder insert(String tabla, ArrayList<Object> values){
        String query = "insert into `"+tabla+"` values(";
        for(int i = 0; i < values.size(); i++){
            Object valor = values.get(i);
            if(valor == null){
                query += "null";
            }else if(valor instanceof Integer){
                query += valor;
            }else{
                query += "'"+valor+"'";
            }

            if(i+1 >= values.size()){
                query += ")";
            }else{
                query += ", ";
            }
        }
        Fragment fragment = new Fragment(query);
        this.fragmentos.add(fragment);
        return this;
    }

    /**
     * Construye finalmente la query para poder ser utilizada
     * @return
     */
    public String build(){
        String resultado = "";

        for (Fragment fragment : fragmentos) {
            resultado += fragment.toString();
        }

        return resultado;
    }
}
