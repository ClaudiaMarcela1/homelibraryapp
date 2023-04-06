<%-- 
    Document   : index
    Created on : 14/05/2022, 11:42:03 a. m.
    Author     : Claudia Marcela
--%>

<%
    //EL ARCHIVO INDEX CARGA APENAS EJECUTO EL PROYECTO
    
    //Opción 1 => REDIRECCIONAR:
    //response.sendRedirect("home.jsp");
    
    //Opción 2 => DESPACHAR:
    request.getRequestDispatcher("home.jsp").forward(request, response);
%>
