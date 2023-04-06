/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.Book;
import logic.BookLogic;

/**
 *
 * @author Claudia Marcela
 */
public class BooksController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //Variables
        String pageForm = "books_form.jsp";
        String pageList = "books_list.jsp";
        String home = "home.jsp";
        
        //Capturar parámetros. Se reciben como String
        String action = request.getParameter("action") + ""; //En caso que no se envíe una acción, quede VACÍO
        int id = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id")); //Si es nulo, asignar 0. En caso contrario asignar el valor obtenido
        
        //Crear variables a utilizar en el switch
        BookLogic bLogic = null;
        String html;
        String html2;
        PrintWriter out;
        
        switch(action){
            //LINK 1: books?action=new
            //LINK 2: books?action=edit&id=...
            //LINK 3: books?action=delete&id=...
            //RUTA: books_list --> books_form
            case "new":
            case "edit":
                request.setAttribute("action", action); //Le envío los parámetros como atributos
                request.setAttribute("id", id);
                request.getRequestDispatcher(pageForm).forward(request, response); //Despachar a books_form
                break;
            
            //LINK: books?action=delete&id=...
            case "delete":
                bLogic = new BookLogic();
                boolean deleted = bLogic.deleteBook(id);
                
                //Confirmar operación OK
                
                if (deleted){
                    html = "Deleted successfully";
                }else{
                    html = "Operation not executed";
                }
                
                //Preparar página HTML a mostrar
                response.setContentType("text/html");
                out = response.getWriter();
                html2 = "<!DOCTOR html>" +
                        "<html>" +
                        "<head>" +
                        "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
                        "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">" +
                        "</head>" +
                        "<body>" +
                        "<div class=\"container mt5\">" +
                        "<br>" +
                        "<div class=\"jumbotron\">" +
                        "  <h1 class=\"display-4\">" + html + "</h1>" +
                        "  <hr class=\"my-4\">" +
                        "  <p class=\"lead\">" +
                        "    <a class=\"btn btn-primary btn-lg\" href=\"books\" role=\"button\">RETURN TO THE LIST</a>" +
                        "  </p>" +
                        "</div>";
                          
                out.println(html2);
                break;     
            
            //LINK: books?action=save
            //RUTA: books_form. Botón submit --> página temporal
            case "save":
                //Capturar valores del formulario. Se reciben como String
                id = Integer.parseInt(request.getParameter("txtId"));
                String title = request.getParameter("txtTitle");
                String author = request.getParameter("txtAuthor");
                String yearPublication = request.getParameter("txtPublication");
                int fkGenre = Integer.parseInt(request.getParameter("txtGenre"));
                int fkLanguage = Integer.parseInt(request.getParameter("txtLanguage"));
                int fkStatus = Integer.parseInt(request.getParameter("txtStatus"));
                String availability = request.getParameter("radAvailability");
                
                //Crear objeto con estos datos. Y ejecutar método registerBook (insertar o actualizar)
                Book b = new Book(id, title, author, yearPublication, fkGenre, fkLanguage, fkStatus, availability);
                bLogic = new BookLogic();
                boolean saved = bLogic.registerBook(b);
                
                //Confirmar operación OK
                if (saved){
                    html = "Saved successfully";
                }else{
                    html = "Operation not executed";
                }
                
                //Preparar página HTML a mostrar
                response.setContentType("text/html");
                out = response.getWriter();
                html2 = "<!DOCTOR html>" +
                        "<html>" +
                        "<head>" +
                        "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" +
                        "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">" +
                        "</head>" +
                        "<body>" +
                        "<div class=\"container mt5\">" +
                        "<br>" +
                        "<div class=\"jumbotron\">" +
                        "  <h1 class=\"display-4\">" + html + "</h1>" +
                        "  <hr class=\"my-4\">" +
                        "  <p class=\"lead\">" +
                        "    <a class=\"btn btn-primary btn-lg\" href=\"books\" role=\"button\">RETURN TO THE LIST</a>" +
                        "  </p>" +
                        "</div>";
                          
                out.println(html2);
                break;
            
            //LINK: books_list. Botón back --> home    
            case "home":
                request.getRequestDispatcher(home).forward(request, response);
                
            //LINK: books
            //RUTA 1: Inicio. Módulo books --> books_list
            //RUTA 2: books_form. Botón cancel --> books_list
            //RUTA 3: página temporal --> books_list
            default:
                request.getRequestDispatcher(pageList).forward(request, response); //Cargue la página, no la muestre en barra de direcciones
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
