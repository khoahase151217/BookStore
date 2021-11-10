<%-- 
    Document   : user
    Created on : Jun 1, 2021, 1:38:00 PM
    Author     : ADMIN
--%>

<%@page import="asm.product.Category"%>
<%@page import="asm.product.ProductDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="asm.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="asm.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">;
    </head>

    <%
        UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
        if (user != null) {
            if (user.getRoleID().equals("AD")) {
                session.setAttribute("ERROR_MESSAGE", "Admin can not login user page");
                response.sendRedirect("login.jsp");
                return;
            }else if(user.getStatusID().equals("Unactive")){
                session.invalidate();
                response.sendRedirect("user.jsp");
                return;
            }
            
        }
    %>
    <%
        String search1 = (String) request.getParameter("search1");
        if (search1 == null) {
            search1 = "";
        }
    %> 
    <%
        String message = (String) request.getAttribute("SHOPPING_MESSAGE");
        if (message == null) {
            message = "";
        }
    %>
    <style>
        .mr-3{
            border: 1px solid #ddd;
            border-radius: 4px;
            padding: 5px;
            width: 150px;
        }
        .log{
            float: right;
        }
    </style>
    <body>
        <div>
            <div class="row">
                <div class="col">
                    <%
                        if (user != null) {
                    %>
                    <h2>Welcome User :<%=user.getFullName()%>  </h2>
                    <%
                    } else {
                    %>
                    <h2>Welcome Passersby</h2>
                    <%
                        }
                    %> 
                </div>
                <div class="col" >
                    <div class="log">
                        <%
                            if (user == null) {
                        %>
                        <a  class="btn btn-success" role="button" href="login.jsp" >Login</a>
                        <%
                        } else {
                        %>
                        <form action="MainController">
                            <input  type="submit" name="action" value="Logout" class="btn btn-success"/>
                        </form>
                        <%
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>



        <h1 class="row justify-content-center" >Alaba Book Store</h1>
        </br>
        <nav class="navbar navbar-light bg-light justify-content-between" >
            <form action="MainController">
                <input type="submit" name="action" value="View cart"/>
            </form>
            <form class="form-inline" action="MainController">
                <input class="form-control mr-sm-2" type="text"  name="search1" value="${param.search1}">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit" name="action" value="Search1">Search</button>
            </form>
        </nav>
        <p> 
        <h5>CATEGORY:</h5>
        <%
            ProductDAO cate = new ProductDAO();
            List<Category> listC = cate.getCategory();
            for (Category o : listC) {
        %>

        <a class="btn btn-success"  href="MainController?cid=<%=o.getCid()%>&action=Category" role="button" >
            <%=o.getCname()%>
        </a>
        <%
            }
        %>
    </p>

    </br>
    <p style="color:white; background-color:red; ">
        <%=message%>
    </p>
    </br>
    <%
        List<ProductDTO> list = (List<ProductDTO>) request.getAttribute("LIST_PRODUCT");
        if (list != null) {
            if (!list.isEmpty()) {
                int count = 1;
                for (ProductDTO dto : list) {
    %>
    </br>

    <div class="media">
        <img class="mr-3"  src="<%=dto.getImg()%>" >
        <div class="media-body">
            <h1 class="mt-0"><%=dto.getProductName()%></h1>
            <p><%=dto.getDes()%></p>
            <h3 class="mt-0">Price: <%=dto.getPrice()%> VND </h3>
            <p>Quantity: <%=dto.getQuantity()%></p>
        </div>
    </div>
    <form action="MainController">
        <input type="submit" name="action" value="Add to cart"/>
        <input type="hidden" name="productID" value="<%=dto.getProductID()%>"/>
        <input type="hidden" name="productName" value="<%=dto.getProductName()%>"/>
        <input type="hidden" name="des" value="<%=dto.getDes()%>"/>
        <input type="hidden" name="price" value="<%=dto.getPrice()%>"/>
        <input type="hidden" name="categoryID" value="<%=dto.getCategoryID()%>"/>
        <input type="hidden" name="img" value="<%=dto.getImg()%>"/>
        <input type="number" name="quantity" value="1"/>
        <input type="hidden" name="search1" value="<%=search1%>"/>
    </form>    

    </br>
    <%
                }
            }
        }
    %>



    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
