<%-- 
    Document   : home
    Created on : 14/05/2022, 10:09:50 a. m.
    Author     : Claudia Marcela
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <!-- Agregar links de bootstrap para CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        
        <title>Home Library - home</title>
    </head>
    
    <body>
        <div class="container mt5">
            <br>
            <br>
            <!-- Bootstrap: jumbotron -->
            <div class="jumbotron">
                <h1 class="display-4">Home Library</h1>
                <p class="lead">Web application for management our books at home!</p>
                <hr class="my-4">
            </div>
            <br>
            <!-- Bootstrap: cards -->
            <div class="row">
                <div class="col-sm-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Books</h5>
                            <p class="card-text">From here you can view, filter, insert, modify and delete information about books.</p>
                            <a href="books" class="btn btn-primary">Go there</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Users</h5>
                            <p class="card-text">From here you can view, insert, modify and delete information about users</p>
                            <a href="#" class="btn btn-secondary">Under construction</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">Requests</h5>
                            <p class="card-text">From here you can ask for books lending and register returns</p>
                            <a href="#" class="btn btn-secondary">Under construction</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
