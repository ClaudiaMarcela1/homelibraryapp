<%-- 
    Document   : index
    Created on : 14/05/2022, 11:42:03 a.�m.
    Author     : Claudia Marcela
--%>

<%
    //EL ARCHIVO INDEX CARGA APENAS EJECUTO EL PROYECTO
    
    //Opci�n 1 => REDIRECCIONAR:
    //response.sendRedirect("home.jsp");
    
    //Opci�n 2 => DESPACHAR:
    request.getRequestDispatcher("home.jsp").forward(request, response);
%>
