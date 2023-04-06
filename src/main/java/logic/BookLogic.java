/*
AUTHOR: Claudia Marcela
DATE: May 6/2022
*/

package logic;

import java.util.ArrayList;
import java.util.TreeMap;
import persistence.BookDAO;

public class BookLogic {
    
    /*
    ATRIBUTOS
    */
    private ArrayList<Book> list; //Lista de todos los libros obtenidos de la consulta
    
    
    // -----------------------------------------------------------------------------------------------------------
    
    /**
     * Método GETTER para OBTENER LISTA DE LIBROS
     * @return 
     */
    public ArrayList<Book> getList(){
        return list;
    }
    
    
    // -----------------------------------------------------------------------------------------------------------
    
    /**
     * Método para CARGAR TODOS LOS LIBROS EN EL ATRIBUTO LIST
     * @return TRUE or FALSE
     */
    public boolean loadBooks(){
        BookDAO dao = new BookDAO(); //Crear objeto de la clase DAO
        list = dao.getBooks(); //Ejecutar método getBooks y guardar resultado en atributo lista
        
        //Verificar carga de libros
        if (list.size() > 0){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Método para CARGAR LOS LIBROS FILTRADOS EN EL ATRIBUTO LIST
     * @param filter
     * @return TRUE or FALSE
     */
    public boolean loadBooksFilter(String filter){
        BookDAO dao = new BookDAO(); //Crear objeto de la clase DAO
        list = dao.getBooksFilter(filter); //Ejecutar método getBooksFilter y guardar resultado en atributo lista
        
        //Verificar carga de libros
        if (list.size() > 0){
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Método para CONSULTAR UN LIBRO
     * @param idConsultar
     * @return objeto tipo Book
     */
    public Book loadBook(int idConsultar){
        BookDAO dao = new BookDAO(); //Crear objeto de la clase DAO
        Book b = dao.getBook(idConsultar); //Ejecutar método getBooksy guardar resultado en variable tipo Book
        return b; //Retornar objeto tipo Book
    }
    
    
    // -----------------------------------------------------------------------------------------------------------
     
    /**
     * Método para INSERTAR NUEVO LIBRO O ACTUALIZAR UNO EXISTENTE
     * @param b
     * @return TRUE or FALSE
     */
    public boolean registerBook(Book b){
        BookDAO dao = new BookDAO();
        
        if(b.getId() == 0){ //Se quiere INGRESAR NUEVO LIBRO
            int id = dao.insertBook(b);
            if(id > 0){ //Se devolvió el ID del registro
                return true;
            }else{
                return false;
            }
        }else{ //Existe un ID, se quiere ACTUALIZAR LIBRO EXISTENTE
            int query = dao.updateBook(b);
            if(query == 1){ //Hubo 1 fila afectada
                return true;
            }else{
                return false;
            }
        }
    }
    
    /**
     * Método para ELIMINAR UN LIBRO
     * @param id
     * @return TRUE or FALSE
     */
    public boolean deleteBook(int id){
        BookDAO dao = new BookDAO(); //Crear objeto de la clase DAO
        int query = dao.deleteBook(id); //Ejecutar método deleteBook y guardar resultado en variable tipo int
        if(query == 1){ //Se eliminó 1 registro
            return true;
        }else{
            return false;
        }
    }
    
    
    // -----------------------------------------------------------------------------------------------------------
    
    /**
     * Método para OBTENER TODOS LOS GÉNEROS DE LIBROS
     * @return registros en una estructura llamada TreeMap (Id, género)
     */
    public TreeMap<Integer, String> getGenres(){
        BookDAO dao = new BookDAO();
        TreeMap<Integer, String> listGenres = dao.getGenres();
        return listGenres;
    }
    
    /**
     * Método para OBTENER TODOS LOS IDIOMAS DE LIBROS
     * @return registros en una estructura llamada TreeMap (Id, idioma)
     */
    public TreeMap<Integer, String> getLanguages(){
        BookDAO dao = new BookDAO();
        TreeMap<Integer, String> listLanguages = dao.getLanguages();
        return listLanguages;
    }
    
    /**
     * Método para OBTENER TODOS LOS ESTADOS DE LIBROS
     * @return registros en una estructura llamada TreeMap (Id, estado) 
     */
    public TreeMap<Integer, String> getStatus(){
        BookDAO dao = new BookDAO();
        TreeMap<Integer, String> listStatus = dao.getStatus();
        return listStatus;
    }
    
    /**
     * Mëtodo para OBTENER UN GÉNERO DE LIBRO
     * @param key
     * @return género
     */
    public String getGenre(int key){
        BookDAO dao = new BookDAO();
        TreeMap<Integer, String> genres = dao.getGenres();
        String genre = genres.get(key);
        return genre;
    }
    
    /**
     * Mëtodo para OBTENER UN IDIOMA DE LIBRO
     * @param key
     * @return idioma
     */
    public String getLanguage(int key){
        BookDAO dao = new BookDAO();
        TreeMap<Integer, String> languages = dao.getLanguages();
        String language = languages.get(key);
        return language;
    }
    
    /**
     * Mëtodo para OBTENER UN ESTADO DE LIBRO
     * @param key
     * @return estado
     */
    public String getStatu(int key){
        BookDAO dao = new BookDAO();
        TreeMap<Integer, String> status = dao.getStatus();
        String statu = status.get(key);
        return statu;
    }
    
    
    
    
}
