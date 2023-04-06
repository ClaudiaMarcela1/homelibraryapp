<%-- 
    Document   : books_list
    Created on : 9/05/2022, 10:08:52 a. m.
    Author     : Claudia Marcela
--%>

<%@page import="logic.Book"%>
<%@page import="logic.BookLogic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <!-- Agregar links de bootstrap para CSS e íconos -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
        
                
        <title>Home Library - list</title>
    </head>
    
    <body>
        <div class="container mt-5">
            <div class="row mb-2">
                <h1 class="col-sm-11">Home library</h1>
                <a href="books?action=home" class="col-sm-1"><button type="button" class="btn btn-light">BACK</button></a>
            </div>
         
            <hr>
            <br><br>
            
            <!-- Código embebido: obtener el valor digitado en la caja de búsqueda -->
            <%
                //Operador ternario. Crear variable filter. Si hay valor de filtro, asignarlo, sino dejarlo vacío
                String filter = request.getParameter("txtFilter") == null ? "" : request.getParameter("txtFilter");
            %>
            
            <!-- Barra para filtrar resultados -->
            <div class="row">
                <!-- Formulario envía por GET. ACTION vacía indica que hacia página actual (se recarga) -->
                <form class="col-sm-10" method="GET" action="">
                    <div class="row mb-3">
                        <label for="txtFilter" class="col-form-label col-sm-2">Filter results : </label>
                        <input type="text" class="col-form-control col-sm-9" name="txtFilter" id="txtFilter" placeholder="By title, author, genre, status, availability" value="<%= filter%>">
                        <button type="submit" class="btn btn-dark col-sm-1"><i class="bi bi-search"></i></button> <!-- Submit envía info del formulario según config "Method" y "Action" -->
                    </div>
                </form>
                
                <!-- Botón para INGRESAR NUEVO LIBRO. Se envía hacia CONTROLADOR el parámetro "action" con el valor "new" -->
                <a href="books?action=new" class="col-sm-2"><button type="submit" class="btn btn-primary" id="btnNuevo" name="btnNuevo">New book</button></a>
            </div>
            <hr>
            <br>
            
            <!-- Tabla con todos los libros registrados -->
            <table class="table table-hover">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col">Title</th>
                        <th scope="col">Author</th>
                        <th scope="col">Publication year</th>
                        <th scope="col">Genre</th>
                        <th scope="col">Language</th>
                        <th scope="col">Status</th>
                        <th scope="col">Availability</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    
                    <!-- Código embebido: cargar información de libros -->
                    <%
                        //Crear objeto tipo BookLogic para ejecutar métodos requeridos
                        BookLogic bLogic = new BookLogic();
                        
                        //Verficar tipo carga a realizar: TODOS o según FILTRO
                        boolean dataOk;
                        if (filter.equals("")){ //NO hay filtro
                            dataOk = bLogic.loadBooks(); //Ejecutar método loadBooks y guardar TRUE or FALSE
                        }else{ //SI hay filtro
                            dataOk = bLogic.loadBooksFilter(filter); //Ejecutar método loadBooksFilter y guardar TRUE or FALSE
                        }
                        
                        //Si existen datos recuperados de BD, se muestran
                        if (dataOk){
                            //Recorrer todos los valores del atributo List. Guardarlos en una varible tipo Book
                            for (Book b: bLogic.getList()){
                    %>
                    
                    <tr>
                        <!-- Se agrega cada valor dinámicamente -->
                        <th scope="row"><%= b.getTitle()%></td>
                        <td><%= b.getAuthor()%></td>
                        <td><%= b.getPublicationYear()%></td>
                        <td><%= bLogic.getGenre(b.getFk_genre())%></td>
                        <td><%= bLogic.getLanguage(b.getFk_language())%></td>
                        <td><%= bLogic.getStatu(b.getFk_status())%></td>
                        <td><%= b.getAvailability()%></td>
                        <td>
                            <!-- Botón para EDITAR UN LIBRO. Se envía hacia CONTROLADOR el parámetro "action" con el valor "edit" y el "ID" -->
                            <a href="books?action=edit&id=<%= b.getId()%>"><button type="button" class="btn btn-warning"><i class="bi bi-pencil-fill"></i></button></a>
                            <!-- Botón para ELIMINAR UN LIBRO. Se envía hacia CONTROLADOR el parámetro "action" con el valor "delete" y el "ID" -->
                            <a href="books?action=delete&id=<%= b.getId()%>"><button type="button" class="btn btn-danger"><i class="bi bi-trash"></i></button></a>
                        </td>
                    </tr>
                    
                    <%
                            }
                        }else{
                    %>
                    
                    <tr>
                        <!-- Se informa que no existen datos disponibles -->
                        <td colspan="7">No data</td>
                    </tr>
                    
                    <%
                        }
                    %>
                    
                </tbody>
            </table>
            
        </div>
    </body>
</html>
