/*
AUTHOR: Claudia Marcela
DATE: May 5/2022
*/

package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;
import logic.Book;

public class BookDAO {
    
    /**
     * Método para CONSULTAR TODOS LOS LIBROS
     * @return todos los registros en un ArrayList
     */
    public ArrayList<Book> getBooks(){
        ArrayList<Book> list = new ArrayList<Book>(); //Crear objeto de tipo ArrayList que guarda elementos tipo Book
        ConnectionDB con = new ConnectionDB(); //Crear objeto tipo ConnectionDB. Ejecutar método constructor, que incluye el método conectar
        
        //Creo sentencia SQL, la ejecuto por medio del método de la clase ConnectionDB y guardo respuesta en una variable tipo ResultSet
        ResultSet rs = con.executeQuery("SELECT * FROM book");
        
        try{
            while(rs.next()){ //Recorrer cada registro del objeto tipo ResultSet con un "Ciclo indefinido while"
                Book b = new Book(); //Crear objeto tipo Book
                b.setId(rs.getInt("id")); //Asignarle el valor del atributo id del registro
                b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author"));
                b.setPublicationYear(rs.getString("publicationYear"));
                b.setFk_genre(rs.getInt("fk_genre"));
                b.setFk_language(rs.getInt("fk_language"));
                b.setFk_status(rs.getInt("fk_status"));
                b.setAvailability(rs.getString("availability"));
                list.add(b); //Adicionar objeto a la lista
            }
        }catch(SQLException ex){
            con.disconnect();//Ejecutar método desconectar
            return null;
        }
        con.disconnect();//Ejecutar método desconectar
        return list; //Retorno objeto tipo ArrayList
    }
    
    /**
     * Método para CONSULTAR UN LIBRO EN ESPECÍFICO
     * @param idConsultar
     * @return un objeto tipo Book. En caso contrario NULL
     */
    public Book getBook(int idConsultar){
        Book b = new Book(); //Crear objeto tipo Book
        ConnectionDB con = new ConnectionDB(); //Crear objeto tipo ConnectionDB. Ejecutar método constructor, que incluye el método conectar
        
        //Creo sentencia SQL, la ejecuto por medio del método de la clase ConnectionDB y guardo respuesta en una vaiable tipo ResultSet
        String sql = "SELECT * " +
                     "FROM book " +
                     "WHERE id = " + idConsultar;
        ResultSet rs = con.executeQuery(sql);
        
        try{
            if(rs.next()){ //Revisar el único registro del objeto tipo ResultSet
                b.setId(rs.getInt("id")); //Asignarle el valor del atributo id del registro
                b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author"));
                b.setPublicationYear(rs.getString("publicationYear"));
                b.setFk_genre(rs.getInt("fk_genre"));
                b.setFk_language(rs.getInt("fk_language"));
                b.setFk_status(rs.getInt("fk_status"));
                b.setAvailability(rs.getString("availability"));
            }
        }catch(SQLException ex){
            con.disconnect();//Ejecutar método desconectar
            return null; //Retorno nulo
        }
        con.disconnect();//Ejecutar método desconectar
        return b; //Retorno objeto tipo Book
    }
    
    /**
     * Método para CONSULTAR LIBROS SEGÚN UN FILTRO
     * @param filter: título, autor, genero, estado, disponibilidad
     * @return todos los registros en un ArrayList
     */
    public ArrayList<Book> getBooksFilter(String filter){
        ArrayList<Book> list = new ArrayList<Book>(); //Crear objeto de tipo ArrayList que guarda elementos tipo Book
        ConnectionDB con = new ConnectionDB(); //Crear objeto tipo ConnectionDB. Ejecutar método constructor, que incluye el método conectar
        
        //Creo sentencia SQL, la ejecuto por medio del método de la clase ConnectionDB y guardo respuesta en una variable tipo ResultSet
        String sql = "SELECT b.id, b.title, b.author, b.publicationYear, b.fk_genre, b.fk_language, b.fk_status, b.availability " +
                     "FROM book b JOIN book_genre g ON (b.fk_genre = g.id) JOIN book_status s ON (b.fk_status = s.id) JOIN book_language l ON (b.fk_language = l.id) " +
                     "WHERE b.title LIKE '%" +filter+ "%' OR b.author LIKE '%" +filter+ "%' OR g.genre LIKE '%" +filter+ "%' OR s.status LIKE '%"+filter+"%' OR b.availability LIKE '%"+filter+"%'";
        ResultSet rs = con.executeQuery(sql);
        try{
            while(rs.next()){ //Recorrer cada registro del objeto tipo ResultSet con un "Ciclo indefinido while"
                Book b = new Book(); //Crear objeto tipo Book
                b.setId(rs.getInt("id")); //Asignarle el valor del atributo id del registro
                b.setTitle(rs.getString("title"));
                b.setAuthor(rs.getString("author"));
                b.setPublicationYear(rs.getString("publicationYear"));
                b.setFk_genre(rs.getInt("fk_genre"));
                b.setFk_language(rs.getInt("fk_language"));
                b.setFk_status(rs.getInt("fk_status"));
                b.setAvailability(rs.getString("availability"));
                list.add(b); //Adicionar objeto a la lista
            }
        }catch(SQLException ex){
            con.disconnect();//Ejecutar método desconectar
            return null;
        }
        con.disconnect();//Ejecutar método desconectar
        return list; //Retorno objeto tipo ArrayList
    }
    
    /**
     * Método para INSERTAR NUEVO LIBRO
     * @param b: objeto tipo Book
     * @return id del nuevo registro
     */
    public int insertBook(Book b){
        ConnectionDB con = new ConnectionDB(); //Crear objeto tipo ConnectionDB. Ejecutar método constructor, que incluye el método conectar
        
        //Creo sentencia SQL, la ejecuto por medio del método de la clase ConnectionDB y guardo respuesta (ID generado) en una variable tipo ResultSet
        String sql = "INSERT INTO book (title, author, publicationYear, fk_genre, fk_language, fk_status, availability) " +
                     "VALUES ('" +b.getTitle()+ "', '" +b.getAuthor()+ "', '" +b.getPublicationYear()+ "', " +b.getFk_genre()+ ", " +b.getFk_language()+ ", " +b.getFk_status()+ ",'" +b.getAvailability()+ "')";
        ResultSet rs = con.executeInsert(sql);
        
        int id = 0;
        try{
            if(rs.next()){ //Revisar registro del objeto tipo ResultSet
                id = rs.getInt(1);
            }
        }catch(Exception ex){
            con.disconnect();//Ejecutar método desconectar
            return id; //Retornar id=0 porque no insertó nada
        }
        con.disconnect();//Ejecutar método desconectar
        return id; //Retorno ID>0 porque insertó correctamente
    }
    
    /**
     * Método para ACTUALIZAR UN LIBRO
     * @param b: objeto tipo Book
     * @return # filas actualizadas
     */
    public int updateBook(Book b){
        ConnectionDB con = new ConnectionDB(); //Crear objeto tipo ConnectionDB. Ejecutar método constructor, que incluye el método conectar
        
        //Creo sentencia SQL, la ejecuto por medio del método de la clase ConnectionDB y guardo respuesta (# filas afectadas) en una variable tipo int
        String sql = "UPDATE book " +
                     "SET title = '" + b.getTitle() + "', author = '" + b.getAuthor() + "', publicationYear =  '" + b.getPublicationYear() + "', fk_genre = " + b.getFk_genre() + ", fk_language = " + b.getFk_language() + ", fk_status = " + b.getFk_status() + ", availability = '" + b.getAvailability() + "' " +
                     "WHERE id = " + b.getId();
        int queries = con.executeUpdate(sql);
        
        con.disconnect();//Ejecutar método desconectar
        return queries; //Retorno # filas actualizadas
    }
    
    /**
     * Método para OBTENER TODOS LOS GÉNEROS DE LIBROS
     * @return registros en una estructura llamada TreeMap (Id, género)
     */
    public TreeMap<Integer, String> getGenres(){ //Tipo clave, tipo valor
        TreeMap<Integer, String> listGenres = new TreeMap<Integer, String>(); //Crear estructura para almacenar el listado de géneros de libros
        ConnectionDB con = new ConnectionDB(); //Crear objeto tipo ConnectionDB. Ejecutar método constructor, que incluye el método conectar
        
        //Creo sentencia SQL, la ejecuto por medio del método de la clase ConnectionDB y guardo respuesta en una variable tipo ResultSet
        ResultSet rs = con.executeQuery("SELECT * FROM book_genre");
        
        try{
            while(rs.next()){ //Recorrer cada registro del objeto tipo ResultSet con un "Ciclo indefinido while"
                int id = rs.getInt("id"); //Asignarle el valor del atributo "id" del registro
                String genre = rs.getString("genre"); //Asignarle el valor del atributo "género" del registro
                listGenres.put(id, genre); //Adicionar objeto al TreeMap (clave, valor)
            }
        }catch(SQLException ex){
            con.disconnect();//Ejecutar método desconectar
            return null;
        }
        con.disconnect();//Ejecutar método desconectar
        return listGenres; //Retorno el TreeMap con todos los géneros de libros
    }
    
    /**
     * Método para OBTENER TODOS LOS IDIOMAS DE LIBROS
     * @return registros en una estructura llamada TreeMap (Id, lenguaje)
     */
    public TreeMap<Integer, String> getLanguages(){ //Tipo clave, tipo valor
        TreeMap<Integer, String> listLanguages = new TreeMap<Integer, String>(); //Crear estructura para almacenar el listado de idiomas de libros
        ConnectionDB con = new ConnectionDB(); //Crear objeto tipo ConnectionDB. Ejecutar método constructor, que incluye el método conectar
        
        //Creo sentencia SQL, la ejecuto por medio del método de la clase ConnectionDB y guardo respuesta en una variable tipo ResultSet
        ResultSet rs = con.executeQuery("SELECT * FROM book_language");
        
        try{
            while(rs.next()){ //Recorrer cada registro del objeto tipo ResultSet con un "Ciclo indefinido while"
                int id = rs.getInt("id"); //Asignarle el valor del atributo "id" del registro
                String language = rs.getString("language"); //Asignarle el valor del atributo "idioma" del registro
                listLanguages.put(id, language); //Adicionar objeto al TreeMap (clave, valor)
            }
        }catch(SQLException ex){
            con.disconnect();//Ejecutar método desconectar
            return null;
        }
        con.disconnect();//Ejecutar método desconectar
        return listLanguages; //Retorno el TreeMap con todos los idiomas de libros
    }
    
    /**
     * Método para OBTENER TODOS LOS ESTADOS DE LIBROS
     * @return registros en una estructura llamada TreeMap (Id, estado)
     */
    public TreeMap<Integer, String> getStatus(){ //Tipo clave, tipo valor
        TreeMap<Integer, String> listStatus = new TreeMap<Integer, String>(); //Crear estructura para almacenar el listado de estados de libros
        ConnectionDB con = new ConnectionDB(); //Crear objeto tipo ConnectionDB. Ejecutar método constructor, que incluye el método conectar
        
        //Creo sentencia SQL, la ejecuto por medio del método de la clase ConnectionDB y guardo respuesta en una variable tipo ResultSet
        ResultSet rs = con.executeQuery("SELECT * FROM book_status");
        
        try{
            while(rs.next()){ //Recorrer cada registro del objeto tipo ResultSet con un "Ciclo indefinido while"
                int id = rs.getInt("id"); //Asignarle el valor del atributo "id" del registro
                String status = rs.getString("status"); //Asignarle el valor del atributo "estado" del registro
                listStatus.put(id, status); //Adicionar objeto al TreeMap (clave, valor)
            }
        }catch(SQLException ex){
            con.disconnect();//Ejecutar método desconectar
            return null;
        }
        con.disconnect();//Ejecutar método desconectar
        return listStatus; //Retorno el TreeMap con todos los estados de libros
    }
    
    /**
     * Método para BORRAR UN LIBRO
     * @param id: objeto tipo Book
     * @return id del nuevo registro
     * @param b
     * @return 
     */
    public int deleteBook(int id){
        ConnectionDB con = new ConnectionDB(); //Crear objeto tipo ConnectionDB. Ejecutar método constructor, que incluye el método conectar
        
        //Creo sentencia SQL, la ejecuto por medio del método de la clase ConnectionDB y guardo respuesta en una variable tipo int
        String sql = "DELETE FROM book WHERE id = " + id;
        
        int queries = con.executeUpdate(sql);
        
        con.disconnect();//Ejecutar método desconectar
        return queries; //Retorno # filas eliminadas
    }
    
}
