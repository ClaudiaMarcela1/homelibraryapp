/*
AUTHOR: Claudia Marcela
DATE: May 5/2022
 */

package persistence;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ConnectionDB {
    
    /*
    ATRIBUTOS
    */
    /*
    private String installedConnector = "jdbc:mysql:"; //Se agregó al pom.xml
    private String host = "localhost:3306"; //Servidor local:puerto
    private String db = "homelibrary_db"; //phpAdmin
    private String username = "root"; //phpAdmin
    private String password = ""; //phpAdmin
    */
    
    //Datos para el despliegue en Heroku
    private final boolean isLocal = false;
    
    private final String installedConnector =      isLocal ? "jdbc:mysql:"      : "jdbc:mysql:";
    private final String host =                   isLocal ? "localhost:3306"    : "bvunxwnjevvmmq12bfro-mysql.services.clever-cloud.com";
    private final String db =                     isLocal ? "homelibrary_db"    : "bvunxwnjevvmmq12bfro";
    private final String username =               isLocal ? "root"              : "u5yfc0k1jqwv7ywz";
    private final String password =               isLocal ? ""                  : "Y78Ca62ZNUQeJvNEmSWl";
    
    private Connection connection; //Objeto para conectar a la BD usando los valores de arriba
    private Statement executor; //Se hace cargo de las queries
    
    
    // -----------------------------------------------------------------------------------------------------------
    
    /**
     * Método CONSTRUCTOR
     * Ejecuta el método CONECTAR
     */
    public ConnectionDB(){
        connect();
    }
    
    /**
     * Método para OBTENER CONEXIÓN
     * @return atributo conexion
     */
    public Connection getConnection(){
        return this.connection; //Si la conexión NO está OK, va devolver nulo
    }
    
    /**
     * Metodo para VERIFICAR CONEXIÓN
     * Se necesita para la prueba unitaria
     * @return Si la conexión está OK va devolver TRUE. En caso contrario, va devolver FALSE
     */
    public boolean isConnected(){
        return (this.connection != null);
    }
    
    
    // -----------------------------------------------------------------------------------------------------------
    
    /**
     * Método para CONECTAR A LA BD
     */
    public void connect(){
        try{
            String cadenaConexion = this.installedConnector + "//" + this.host + "/" + this.db; //Crear la cadenaConexion
            Class.forName("com.mysql.cj.jdbc.Driver"); //Indicar donde está el Driver para mysql
            this.connection = DriverManager.getConnection(cadenaConexion, this.username, this.password); //DriverManager hace conexión usando cadenaConexion y info de la BD, y la guarda en la variable
            this.executor = this.connection.createStatement(); //Crear ejecutor para las queries usando la conexión
            this.executor.setQueryTimeout(30); //Ejecutor espere 30 seg la respuesta de la BD
            System.out.println("Conexión creada. " + this.connection);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Método para DESCONECTAR DE LA BD
     */
    public void disconnect(){
        try{
            this.connection.close(); //Cerrar conexión
            this.connection = null; //Desocupar espacio en memoria
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    // -----------------------------------------------------------------------------------------------------------
    
    /**
     * Método para EJECUTAR SELECT
     * @param sql
     * @return todos los registros en un ResultSet
     */
    public ResultSet executeQuery(String sql){
        ResultSet rs = null; //ResultSet permite acumular conjunto de registros
        try{
            rs = this.executor.executeQuery(sql); //Ejecutar consulta SQL y recibo registros de tipo ResultSet
        }catch(Exception e){
            e.printStackTrace();
        }
        return rs; //Devolver ResultSet
    }
    
    /**
     * Método para EJECUTAR INSERT y DELETE
     * @param sql
     * @return id generados en un ResultSet
     */
    public ResultSet executeInsert(String sql){
        ResultSet rs = null;
        try{
            int cant = this.executor.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS); //Ejecuta consulta SQL y recibo # filas afectadas
            if (cant > 0){ //Si hubo filas afectadas
                rs = this.executor.getGeneratedKeys(); //Guardar los id generados por ser auto-increment en INSERT
                //Si se ejecuta UPDATE o DELETE, donde no se generan nuevas llaves, va retornar nulo
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return rs;
    }
    
    /**
     * Método para EJECUTAR UPDATE y DELETE
     * @param sql
     * @return entero con el número de filas afectadas
     */
    public int executeUpdate(String sql){
        int cant = 0;
        try{
            cant = this.executor.executeUpdate(sql); //Ejecuta consulta SQL y recibo # filas afectadas
        }catch(Exception e){
            e.printStackTrace();
        }
        return cant;
    }
    
}
