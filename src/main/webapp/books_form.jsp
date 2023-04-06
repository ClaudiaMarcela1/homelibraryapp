<%--
    Document   : books_form
    Created on : 9/05/2022, 10:09:33 a. m.
    Author     : Claudia Marcela
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.TreeMap"%>
<%@page import="logic.Book"%>
<%@page import="logic.BookLogic"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <!-- Agregar links de bootstrap para CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        
        <title>Home Library - form</title>
    </head>
    
    <body>
        <div class="container mt-5">
            <h3 class="mb-3">Home library - form</h3>
            <hr>
            
            <!-- Código embebido: obtener datos a mostrar en campos del formulario -->
            <%
                //Capturar parámetro "action". Para saber si va INGRESAR o EDITAR
                String action = request.getAttribute("action").toString(); //Atributo se recibe del controlador como objeto
                int id = Integer.parseInt(request.getAttribute("id").toString()); //Atributo se recibe del controlador como objeto
                
                //Crear varible tipo Book
                Book b;
                
                //Evaluar acción recibida
                if (action.equals("edit")){ //Opción editar
                    BookLogic bLogic = new BookLogic();
                    b = bLogic.loadBook(id);
                }else{ //Opción ingresar
                    b = new Book();
                }
                
                //Clase para ejecutar métodos para mostrar tipos de géneros, idiomas y estados
                BookLogic bLogic = new BookLogic(); //Crear objeto tipo BookLogic
            %>
            
            <!-- Formulario para ingresar datos -->
            <!-- Formulario envía por POST. ACTION hacia el controlador -->
            <form method="POST" action="books?action=save">
                <div class="form-group col-md-1 mb-3">
                    <input type="hidden" class="form-control" id="txtId" name="txtId" readonly value="<%= id%>"> <!-- Campo NO modificable = readonly, OCULTO -->
                </div>
                <div class="row mb-3">
                    <div class="form-group col-md-6">
                        <label for="txtTitle">Title</label>
                        <input type="text" class="form-control" id="txtTitle" name="txtTitle" required="true" value="<%= b.getTitle() %>">
                    </div>
                    <div class="form-group col-md-6">
                        <label for="txtAuthor">Author</label>
                        <input type="text" class="form-control" id="txtAuthor" name="txtAuthor" required="true" value="<%= b.getAuthor() %>">
                    </div>
                </div>
                <div class="row mb-3">
                    <div class="form-group col-md-3">
                        <label for="txtPublication">Publication year</label>
                        <select id="txtPublication" name="txtPublication" class="form-control" required="true">
                            <option selected value=""></option>
                            <!-- Código embebido: Mostrar los años de un rango de tiempo -->
                            <%
                                for(int i=2022; i >= 1900; i--){
                            %>
                            <option <%= b.getPublicationYear().equals(String.valueOf(i)) ? "selected" : ""%>><%= i%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                    <div class="form-group col-md-3">
                        <!-- Código embebido: Obtener listado de géneros y mostrar en selected -->
                        <%
                            TreeMap<Integer, String> genres = bLogic.getGenres(); //Ejecutar método getGenres y guardar resultado
                        %>
                        <label for="txtGenre">Genre</label>
                        <select id="txtGenre" name="txtGenre" class="form-control" required="true">
                            <option selected value=""></option>
                            <%
                                for (Map.Entry<Integer, String> genre : genres.entrySet()){ //Obtener info de cada registro del TreeMap
                            %>
                            <option <%= genre.getKey() == b.getFk_genre() ? "selected" : ""%> value="<%= genre.getKey()%>"><%= genre.getValue()%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                    <div class="form-group col-md-3">
                        <!-- Código embebido: Obtener listado de idiomas y mostrar en selected -->
                        <%
                            TreeMap<Integer, String> languages = bLogic.getLanguages(); //Ejecutar método getLanguages y guardar resultado
                        %>
                        <label for="txtLanguage">Language</label>
                        <select id="txtLanguage" name="txtLanguage" class="form-control" required="true">
                            <option selected value=""></option>
                            <%
                                for (Map.Entry<Integer, String> language: languages.entrySet()){
                            %>
                            <option <%= language.getKey() == b.getFk_language() ? "selected" : ""%> value="<%= language.getKey()%>"><%= language.getValue()%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                    <div class="form-group col-md-3">
                        <!-- Código embebido: Obtener listado de estados y mostrar en selected -->
                        <%
                            TreeMap<Integer, String> status = bLogic.getStatus(); //Ejecutar método getStatus y guardar resultado
                        %>
                        <label for="txtStatus">Status</label>
                        <select id="txtStatus" name="txtStatus" class="form-control" required="true">
                            <option selected value=""></option>
                            <%
                                for (Map.Entry<Integer, String> statu: status.entrySet()){
                            %>
                            <option <%= statu.getKey() == b.getFk_status() ? "selected" : ""%> value="<%= statu.getKey()%>"><%= statu.getValue()%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                </div>
                <div class="form-group mb-3">
                    <legend class="col-form-label col-sm-2">Availability</legend>
                    <!-- El "name" es igual para todos porque es la variable. El "id" si es para cada uno -->
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="radAvailability" id="radAvailable" value="Available" required="true" <%= b.getAvailability().equals("Available") ? "checked" : ""%> >
                        <label class="form-check-label" for="radAvailable">Available</label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="radAvailability" id="radBorrowed" value="Borrowed" <%= b.getAvailability().equals("Borrowed") ? "checked" : ""%>>
                        <label class="form-check-label" for="radBorrowed">Borrowed</label>
                    </div>
                </div>
                <hr>
                <br>
                <!-- Botón para GUARDAR. Redirecciona al controlador, ya está indicado en el formulario -->
                <button type="submit" class="btn btn-primary" id="btnSubmit" name="btnSubmit" value="<%= action%>">SUBMIT</button>
                <!-- Botón para CANCELAR. Redirecciona a la página books_lista.jsp -->
                <a href="books"><button type="button" class="btn btn-secondary" id="btnCancel" name="btnCancel" value="cancel">CANCEL</button></a>
            </form>
            
        </div>
    </body>
</html>
