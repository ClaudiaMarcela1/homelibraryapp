/*
AUTHOR: Claudia Marcela
DATE: May 6/2022
*/

package test;

import logic.Book;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import persistence.BookDAO;
import persistence.ConnectionDB;

public class UnitTests {
    
    /**
     * Método para VERIFICAR CONEXIÓN Y DESCONEXIÓN
     */
    @Test //Identifica que esto es un método de prueba unitaria y se debe ejecutar al inicio
    public void checkConnection(){
        ConnectionDB conn = new ConnectionDB(); //Crear objeto tipo ConnectionDB. Ejecutar método constructor, que incluye el método conectar
        
        //"Assert" si es true no pasa nada. Si es falso, crea un error interno
        Assert.assertTrue(conn.isConnected(), "FAILED CONNECTION"); //Verificar conexión a BD
        
        conn.disconnect(); //Desconectar
        Assert.assertFalse(conn.isConnected(), "FAILED DISCONNECTION"); //Verificar desconexión de BD
    }
    
    
    // -----------------------------------------------------------------------------------------------------------
    
    /**
     * Método para VERIFICAR CONSULTA DE TODOS LOS LIBROS
     */
    @Test
    public void checkGetBooks(){
        BookDAO dao = new BookDAO(); //Crear objeto tipo BookDAO
        Assert.assertTrue(dao.getBooks().size() > 0, "FAILED BOOK LOAD"); //Verificar si cargo todos los libros (ArrayList.size > 0)
    }
    
    /**
     * Método para VERIFICAR CONSULTA DE TODOS LOS GÉNEROS DE LIBROS
     */
    @Test
    public void checkGetGenres(){
        BookDAO dao = new BookDAO(); //Crear objeto tipo BookDAO
        Assert.assertTrue(dao.getGenres().size() > 0, "FAILED GENRES LOAD"); //Verificar si cargo todos los géneros (TreeMap.size > 0)
    }
    
    /**
     * Método para VERIFICAR CONSULTA DE TODOS LOS IDIOMAS DE LIBROS
     */
    @Test
    public void checkGetLanguages(){
        BookDAO dao = new BookDAO(); //Crear objeto tipo BookDAO
        Assert.assertTrue(dao.getLanguages().size() > 0, "FAILED LANGUAGES LOAD"); //Verificar si cargo todos los idiomas (TreeMap.size > 0)
    }
    
    /**
     * Método para VERIFICAR CONSULTA DE TODOS LOS ESTADOS DE LIBROS
     */
    @Test
    public void checkGetStatus(){
        BookDAO dao = new BookDAO(); //Crear objeto tipo BookDAO
        Assert.assertTrue(dao.getStatus().size() > 0, "FAILED STATUS LOAD"); //Verificar si cargo todos los estados (TreeMap.size > 0)
    }
    
    
    // -----------------------------------------------------------------------------------------------------------
    
    /**
     * Método para VERIFICAR INSERCIÓN, OBTENCIÓN Y ACTUALIZACIÓN DE UN LIBRO
     */
    @Ignore
    public void checkInsertGetUpdateBook(){
        BookDAO dao = new BookDAO(); //Crear objeto tipo BookDAO
        
        Book b = new Book("Titulo", "Autor", "2015", 1, 1, 1, "Disponible"); //Crear objeto tipo Book
        int id = dao.insertBook(b); //Ejecutar método insertBook y guardar ID devuelto
        Assert.assertTrue(id > 0, "DON'T INSERT CORRECTLY"); //Verificar si insertó el libro (id > 0)
        
        Book b2 = dao.getBook(id); //Consultar el registro insertado anteriormente
        b2.setTitle("Otro titulo"); //Modificar registro
        b2.setAuthor("Otro autor");
        int query = dao.updateBook(b2); //Ejecutar método updateBook y guardar # fila afectadas
        Assert.assertTrue(query > 0, "DON'T UPDATE CORRECTLY"); //Verificar si actualizó el libro (#fila == 1)
        
        //Borrado físico para eliminar registro creado de prueba
        String sql = "DELETE FROM book WHERE id = " + id;
        ConnectionDB con = new ConnectionDB(); //Crear objeto tipo ConnectionDB. Ejecutar método constructor, que incluye el método conectar
        con.executeUpdate(sql);
        con.disconnect();
    }
    
}
