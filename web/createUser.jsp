<%-- 
    Document   : createUser
    Created on : Jun 8, 2021, 3:13:52 PM
    Author     : ADMIN
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="asm.user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">;
    </head>
    <body>
        <jsp:useBean id="USER_ERROR" class="asm.user.UserError" scope="session" >
            <jsp:setProperty name="USER_ERROR" property="userIDError" value="abc"/>
        </jsp:useBean>
        <form action="MainController" method="POST">

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">User ID</span>
                </div>
                <input type="text" class="form-control" name="userID" required="" aria-describedby="basic-addon1">
                ${requestScope.USER_ERROR.getUserIDError()}</br>
            </div>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Full Name</span>
                </div>
                <input type="text" class="form-control" name="fullName" required="" aria-label="" aria-describedby="basic-addon1">
                ${requestScope.USER_ERROR.getFullNameError()}</br>
            </div>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Role ID</span>
                </div>
                <select class="form-control" required=""  aria-describedby="basic-addon1" name="roleID">
                    <option value=""></option>
                    <option value="AD">AD</option>
                    <option value="US">US</option>
                </select>
                ${requestScope.USER_ERROR.getRoleIDError()}</br>
            </div>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Phone</span>
                </div>
                <input type="text" class="form-control" name="phone" required="" aria-label="" aria-describedby="basic-addon1">
                </br>
            </div>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Address</span>
                </div>
                <input type="text" class="form-control" name="address" required="" aria-label="" aria-describedby="basic-addon1">
                </br>
            </div>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Password</span>
                </div>
                <input type="password" class="form-control" name="password" required=""  aria-describedby="basic-addon1">
                ${requestScope.USER_ERROR.passwordError}</br>
            </div>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Confirm Password</span>
                </div>
                <input type="password" class="form-control" name="confirm" required="" aria-describedby="basic-addon1">
                ${requestScope.USER_ERROR.confirmPasswordError}</br>
            </div>

            <button type="submit" name="action" value="Create" class="btn btn-success">Create</button>
            <button type="reset" value="Reset" class="btn btn-success">Reset</button>
        </form>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
