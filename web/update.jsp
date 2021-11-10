<%-- 
    Document   : update.jsp
    Created on : Jun 8, 2021, 12:46:47 PM
    Author     : ADMIN
--%>

<%@page import="asm.user.UserDTO"%>
<%@page import="asm.user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">;

    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !"AD".equals(loginUser.getRoleID())) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <%
            String message = (String) request.getAttribute("STATUS_MESSAGE");
            if (message == null) {
                message = "";
            }
        %>
        <%
            UserError userError = (UserError) request.getAttribute("USER_ERROR");
            if (userError == null) {
                userError = new UserError("", "", "", "", "");
            }
        %>
        <p style="color:white; background-color:red; ">
            <%=message%>
        </p>
        <form action="MainController">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">User ID</span>
                </div>
                <input type="text" class="form-control" name="userID" value="<%= request.getParameter("userID")%>" readonly="" aria-describedby="basic-addon1">
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Full Name</span>
                </div>
                <input type="text" class="form-control" name="fullName" value="<%= request.getParameter("fullName")%>" required="" aria-label="" aria-describedby="basic-addon1">
                ${requestScope.USER_ERROR.getFullNameError()}</br>
            </div>

            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Role ID</span>
                </div>
                <input type="text" class="form-control" name="roleID" value="<%= request.getParameter("roleID")%>" readonly="" aria-label="" aria-describedby="basic-addon1">
            </div>
            
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Phone</span>
                </div>
                <input type="text" class="form-control" name="phone" value="<%= request.getParameter("phone")%>" required="" aria-label="" aria-describedby="basic-addon1">
                </br>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Address</span>
                </div>
                <input type="text" class="form-control" name="address" value="<%= request.getParameter("address")%>" required="" aria-label="" aria-describedby="basic-addon1">
                </br>
            </div>
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="basic-addon1">Status</span>
                </div>
                <select class="form-control" required=""  aria-describedby="basic-addon1" name="statusID">
                    <option><%=request.getParameter("statusID")%></option>
                    <%if (request.getParameter("statusID").equals("Active")) {
                    %> 
                    <option value="Unactive">Unactive</option>
                    <%}
                        if (request.getParameter("statusID").equals("Unactive")) {
                    %>
                    <option value="Active">Active</option>  
                    <%}%>
                </select>
            </div>
            <input type="hidden" name="search" value="<%=request.getParameter("search")%>"/>
            ${requestScope.USER_ERROR.getRoleIDError()}</br>
            <input type="submit" name="action" value="Confirm Update" class="btn btn-success"/>
            <input type="reset" value="Reset" class="btn btn-success"/>
        </form>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
