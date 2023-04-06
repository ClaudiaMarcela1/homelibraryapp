/*
AUTHOR: Claudia Marcela
DATE: May 5/2022
 */

package logic;

public class Book {
    
    /*
    ATRIBUTOS
    */
    private int id = 0;
    private String title = "";
    private String author = "";
    private String publicationYear = ""; //yyyy
    private int fk_genre = 0;
    private int fk_language = 0;
    private int fk_status = 0;
    private String availability = "";
    
    
    // -----------------------------------------------------------------------------------------------------------
    
    /**
     * Método CONSTRUCTOR
     */
    public Book(){
    }
    
    /**
     * Método CONSTRUCTOR SOBRECARGADO
     * @param title
     * @param author
     * @param publicationYear
     * @param fk_genre
     * @param fk_language
     * @param fk_status
     * @param availability 
     */
    public Book(String title, String author, String publicationYear, int fk_genre, int fk_language, int fk_status, String availability) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.fk_genre = fk_genre;
        this.fk_language = fk_language;
        this.fk_status = fk_status;
        this.availability = availability;
    }
    
    /**
     * Método CONSTRUCTOR SOBRECARGADO
     * @param id
     * @param title
     * @param author
     * @param publicationYear
     * @param fk_genre
     * @param fk_language
     * @param fk_status
     * @param availability 
     */
    public Book(int id, String title, String author, String publicationYear, int fk_genre, int fk_language, int fk_status, String availability) {
        this(title, author, publicationYear, fk_genre, fk_language, fk_status, availability); //Reciclo el constructor anterior
        this.id = id;
    }
    
    
    // -----------------------------------------------------------------------------------------------------------
    
    /*
    Métodos GETTER and SETTER
    */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getFk_genre() {
        return fk_genre;
    }

    public void setFk_genre(int fk_genre) {
        this.fk_genre = fk_genre;
    }

    public int getFk_language() {
        return fk_language;
    }

    public void setFk_language(int fk_language) {
        this.fk_language = fk_language;
    }

    public int getFk_status() {
        return fk_status;
    }

    public void setFk_status(int fk_status) {
        this.fk_status = fk_status;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
    
    /**
     * Método toString
     * @return toda la información del libro
     */
    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", author=" + author + ", publicationYear=" + publicationYear + ", fk_genre=" + fk_genre + ", fk_language=" + fk_language + ", fk_status=" + fk_status + ", availability=" + availability + '}';
    }

}
