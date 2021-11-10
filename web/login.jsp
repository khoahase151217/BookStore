<%-- 
    Document   : login
    Created on : Jul 9, 2021, 10:57:40 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">;
    </head>

    <style>
        .form-container{
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px 0px ;
        }

    </style>
    <%
        String message = (String) session.getAttribute("ERROR_MESSAGE");
        if (message == null) {
            message = "";
        }
    %>

    <body>
        <h1 align="center">Alaba Book Store</h1><br/>
        <section class="container-fluid">
            <section class="row justify-content-center">
                <section class="col-12 col-sm-6 col-md-3">
                    <form class="form-container" action="MainController" method="POST">
                        <p style="color:white; background-color:red; ">
                            <%=message%>
                        </p>
                        <div class="form-group">
                            <label for="InputUserID">User ID</label>
                            <input type="text" name="userID" value="" class="form-control" id="InputUserID" aria-describedby="userIDHelp" placeholder="Enter User ID">
                        </div>
                        <div class="form-group">
                            <label for="InputPassword1">Password</label>
                            <input type="password" name="password" value="" class="form-control" id="InputPassword1" placeholder="Password">
                        </div>
                        <div>
                            <button type="submit" value="Login" name="action" class="btn btn-success">Login</button>
                            <a href="createUser.jsp" align="right" class="btn btn-success">Create new account</a>
                        </div>
                        <br>
                        <div>
                            <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8084/Assignment/LoginGoogleController&response_type=code
                               &client_id=1045675256761-kdrcg17c2b3a78emsa09v1msmle4qbvh.apps.googleusercontent.com&approval_prompt=force" class="btn btn-success">Login With Google</a>
                            <a href="user.jsp"   class="btn btn-success">Shopping now</a>
                        </div>
                    </form>
                </section>
            </section>
        </section>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
