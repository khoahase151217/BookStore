<%-- 
    Document   : viewCart.jsp
    Created on : Jun 22, 2021, 12:02:37 PM
    Author     : ADMIN
--%>

<%@page import="asm.product.ProductDTO"%>
<%@page import="asm.product.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">;
        <title>View Cart</title>
    </head>
    <body>
        <%
            String message = (String) request.getAttribute("CHECKOUT_MESSAGE");
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
        </style>
        <%
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart == null) {
        %>
        </br>
        <p style="color:white; background-color:red; ">
            <%=message%>
        </p>
        </br>
        <h1 class="mb-4">Empty cart!</h1>
        <a href="user.jsp">Add more</a>
        <%
        } else {
        %>

        <div class="container">
            <div class="row">

                <div class="col">
                    <h1 class="mb-4">CART</h1>
                    <p style="color:white; background-color:red; ">
                        <%=message%>
                    </p>
                    <a href="user.jsp">Add more</a>
                    <%
                        int count = 1;
                        double total = 0;
                        for (ProductDTO book : cart.getCart().values()) {
                            total += book.getQuantity() * book.getPrice();
                    %>

                    <form action="MainController">
                        <div class="row mb-4">
                            <div class="col-md-5 col-lg-3 col-xl-3">
                                <div>
                                    <img class="img-fluid w-100"
                                         src="<%=book.getImg()%>" alt="Sample">
                                </div>
                            </div>
                            <div class="col-md-7 col-lg-9 col-xl-9">
                                <div>
                                    <div>
                                        <h5><%=book.getProductName()%></h5>
                                        <input type="hidden" name="id" value="<%=book.getProductID()%>" readonly=""/>
                                        <p class="mb-3 text-muted text-uppercase small">ID: <%=book.getProductID()%></p>
                                        <p class="mb-2 text-muted text-uppercase small">Category: <%=book.getCategoryID()%></p>
                                        <p class="mb-3 text-muted text-uppercase small">Price: <%=book.getPrice()%> VND</p>
                                    </div>
                                    <div>
                                        <div >
                                            <input type="number" name="quantity" value="<%=book.getQuantity()%>"/>
                                            <input type="submit" name="action" value="Modify"/>
                                            <input type="submit" name="action" value="Remove"/> 
                                        </div>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-between align-items-center">
                                    <p class="mb-0"><span><strong>Total: <%=book.getQuantity() * book.getPrice()%> VND</strong></span></p>
                                </div>
                            </div>
                        </div>
                    </form>
                    <%
                        }
                    %>
                </div>
                <div class="col">
                    <div class="card">
                        <div class="card-header">
                            Order
                        </div>
                        <div class="card-body">
                            <h5 class="card-title">The total value of the cart</h5>
                            <p class="card-text"><%=total%> VND</p>
                            <h6>Choose payment method</h6>
                            <form action="MainController">
                                <input type="radio" name="payment" value="Online" />
                                <h8>Online payment</h8>
                                <br>
                                <input type="radio" name="payment" value="COD" />
                                <h8>COD</h8>
                                <input type="hidden" name="total" value="<%=total%>"/>
                                <br>
                                <br>
                                <input type="submit" name="action" value="Check out"/>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <%
            }
        %>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    </body>
</html>
