<%-- 
    Document   : admin
    Created on : Jun 1, 2021, 1:34:20 PM
    Author     : ADMIN
--%>

<%@page import="java.util.List"%>
<%@page import="asm.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">;
    </head>
    <body>

        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !"AD".equals(loginUser.getRoleID())) {
                session.setAttribute("ERROR_MESSAGE", "User can not login admin page");
                response.sendRedirect("login.jsp");
                return;
            }else if(loginUser.getStatusID().equals("Unactive")){
                session.setAttribute("ERROR_MESSAGE", "Your account is unactive, can not login!");
                session.invalidate();
                response.sendRedirect("login.jsp");
                return;
            }
            String search = (String) request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        <h1>Hello Admin:<%= loginUser.getFullName()%></h1>
        <form action="MainController">
            <button type="submit" name="action" value="Logout" class="btn btn-success">Logout</button>
        </form>

        <nav class="navbar navbar-light bg-light justify-content-between">
            <a class="navbar-brand">Search User by name</a>
            <form class="form-inline" action="MainController">
                <input class="form-control mr-sm-2" type="text"  name="search" value="${param.search}">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit" name="action" value="Search">Search</button>
            </form>
        </nav>

        <h1>${requestScope.ERROR_MESSAGE}</h1>

        <%
            List<UserDTO> list = (List<UserDTO>) request.getAttribute("LIST_USER");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1" class="table table-striped">
            <thead>
                <tr class="table-success">
                    <th>No</th>
                    <th>User ID</th>
                    <th>Full name</th>
                    <th>Role ID</th>
                    <th>Password</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Status</th>
                    <th>Create Date</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (UserDTO user : list) {
                %>   
            <form action="MainController">
                <tr >
                    <td ><%=count++%></td>
                    <td ><%=user.getUserID()%></td>
                    <td ><%=user.getFullName()%></td>
                    <td ><%=user.getRoleID()%></td>
                    <td ><%=user.getPassword()%></td>
                    <td ><%=user.getPhone()%></td>
                    <td ><%=user.getAddress()%></td>
                    <td ><%=user.getStatusID()%></td>
                    <td ><%=user.getCreateDate()%></td>
                    <td >
                        <input type="submit" name="action" value="Update"/>
                        <input type="hidden" name="userID" value="<%= user.getUserID()%>"/>
                        <input type="hidden" name="fullName" value="<%= user.getFullName()%>"/>
                        <input type="hidden" name="roleID" value="<%= user.getRoleID()%>"/>
                        <input type="hidden" name="phone" value="<%=user.getPhone()%>"/>
                        <input type="hidden" name="address" value="<%=user.getAddress()%>"/>
                        <input type="hidden" name="statusID" value="<%=user.getStatusID()%>"/>
                        <input type="hidden" name="createDate" value="<%=user.getCreateDate()%>"/>
                        <input type="hidden" name="search" value="<%=search%>"/>
                    </td>
                </tr>
            </form>
            <%
                }
            %>    
        </tbody>
    </table>

    <%
            }
        }
    %>  
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
