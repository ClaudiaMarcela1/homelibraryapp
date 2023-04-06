/*
AUTHOR: Claudia Marcela
DATE: May 25/2022
 */

package logic;

public class Person {
    
    /*
    ATRIBUTOS
    */
    private int id = 0;
    private String name = "";
    private String username = "";
    private String passw = "";
    private String birthday = ""; //yyyy
    private int fk_relationship = 0;
    private int fk_role = 0;
    
    
    // -----------------------------------------------------------------------------------------------------------
    
    /**
     * Método CONSTRUCTOR
     */
    public Person() {
    }
    
    /**
     * Método CONSTRUCTOR SOBRECARGADO
     * @param name
     * @param username
     * @param passw
     * @param birthday
     * @param fk_relationship
     * @param fk_role 
     */
    public Person(String name, String username, String passw, String birthday, int fk_relationship, int fk_role){
        this.name = name;
        this.username = username;
        this.passw = passw;
        this.birthday = birthday;
        this.fk_relationship = fk_relationship;
        this.fk_role = fk_role;
    }
    
    /**
     * Método CONSTRUCTOR SOBRECARGADO
     * @param id
     * @param name
     * @param username
     * @param passw
     * @param birthday
     * @param fk_relationship
     * @param fk_role 
     */
    public Person(int id, String name, String username, String passw, String birthday, int fk_relationship, int fk_role){
        this(name, username, passw, birthday, fk_relationship, fk_role);
        this.id = id;
    }
    
    
    // -----------------------------------------------------------------------------------------------------------

    /*
    Métodos GETTER and SETTER
    */
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassw() {
        return passw;
    }

    public String getBirthday() {
        return birthday;
    }

    public int getFk_relationship() {
        return fk_relationship;
    }

    public int getFk_role() {
        return fk_role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setFk_relationship(int fk_relationship) {
        this.fk_relationship = fk_relationship;
    }

    public void setFk_role(int fk_role) {
        this.fk_role = fk_role;
    }
    
    /**
     * Método toString
     * @return toda la información de la persona 
     */
    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name=" + name + ", username=" + username + ", passw=" + passw + ", birthday=" + birthday + ", fk_relationship=" + fk_relationship + ", fk_role=" + fk_role + '}';
    }
    
 
}
