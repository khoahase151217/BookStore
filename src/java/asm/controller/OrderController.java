/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asm.controller;

import asm.product.Cart;
import asm.product.Order;
import asm.product.OrderDAO;
import asm.product.OrderDetail;
import asm.product.ProductDAO;
import asm.product.ProductDTO;
import asm.user.UserDTO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;

/**
 *
 * @author ADMIN
 */
public class OrderController extends HttpServlet {

    private static final String SUCCESS = "viewCart.jsp";
    private static final String ERROR = "viewCart.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            boolean checkQuantity = true;
            OrderDAO Odao = new OrderDAO();
            Order order;
            OrderDetail detail;
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            ProductDAO Pdao = new ProductDAO();
            List<ProductDTO> listQ = Pdao.getProductQuantity();
            UserDTO user = (UserDTO) session.getAttribute("LOGIN_USER");
            String orderID = Odao.getOrderID();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String date = sdf.format(new Date());
            Double total = Double.parseDouble(request.getParameter("total"));
            String paymentStatus = request.getParameter("payment");
            if (paymentStatus == null) {
                String message = "You must choose payment method!";
                request.setAttribute("CHECKOUT_MESSAGE", message);
            } else {
                for (ProductDTO book : cart.getCart().values()) {
                    for (ProductDTO check : listQ) {
                        if (check.getProductID().equals(book.getProductID()) && check.getQuantity() < book.getQuantity()) {
                            checkQuantity = false;
                        }
                    }
                }
                order = new Order(orderID, date, total, paymentStatus);
                if (checkQuantity) {
                    boolean checkInsertO = Odao.insertOder(user, order);
                    if (checkInsertO) {
                        for (ProductDTO book : cart.getCart().values()) {
                            String detailID = Odao.getDetailID();
                            String pid = book.getProductID();
                            Double pprice = book.getPrice() * book.getQuantity();
                            int pquantity = book.getQuantity();
                            detail = new OrderDetail(detailID, pid, pprice, pquantity);
                            boolean checkInsertOD = Odao.insertOderDetail(order, detail);
                            if (checkInsertOD) {
                                for (ProductDTO check : listQ) {
                                    if (check.getProductID().equals(pid)) {
                                        int newQuantity = check.getQuantity() - pquantity;
                                        boolean checkUpdateQ = Pdao.updateQuantity(newQuantity, pid);
                                        if (checkUpdateQ) {
                                            url = SUCCESS;
                                        }

                                    }
                                }
                            }
                        }
                    }
                    if (user.getFullName().contains("@gmail.com")) {
                        HtmlEmail email = new HtmlEmail();
                        email.setHostName("smtp.googlemail.com");
                        email.setSmtpPort(465);
                        email.setAuthenticator(new DefaultAuthenticator("alababookstore@gmail.com", "Verystrongpassword123"));
                        email.setSSLOnConnect(true);
                        email.setFrom("alababookstore@gmail.com");
                        email.addTo(user.getFullName());
                        email.setSubject("Alaba Book Store");
                        StringBuffer body = new StringBuffer("<html>");
                        body.append("<head>");
                        body.append("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css\" integrity=\"sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm\" crossorigin=\"anonymous\"/>");
                        body.append("<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>"
                                + "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js\" integrity=\"sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q\" crossorigin=\"anonymous\"></script>"
                                + "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js\" integrity=\"sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl\" crossorigin=\"anonymous\"></script>");
                        body.append("<style>");
                        body.append("td{"
                                + "width: 150px;"
                                + "}");
                        body.append("</style>");
                        body.append("</head>");
                        body.append("<body>");
                        body.append("<div class=\"container\">"
                                + "     <div class=\"card\">"
                                + "         <p style=\"color: green\">Your order has been already submited !</p>"
                                + "         <p style=\"color: #333\">Order: </p>"
                                + "         <table>"
                                + "             <thead>"
                                + "                 <tr style=\"text-align: center\">"
                                + "                     <th>NO</th>"
                                + "                     <th>ID</th>"
                                + "                     <th>Name</th>"
                                + "                     <th>Quantity</th>"
                                + "                     <th>Price</th>"
                                + "                 </tr>"
                                + "             </thead>"
                                + "             <tbody>");
                        int count = 1;
                        for (ProductDTO book : cart.getCart().values()) {
                            String bookID = book.getProductID();
                            String Bookname = book.getProductName();
                            int quantity = book.getQuantity();
                            double price = book.getPrice();
                            body.append("<tr style=\"text-align: center\">"
                                    + "     <td>" + (count++) + "</td>"
                                    + "     <td>" + bookID + "</td>"
                                    + "     <td>" + Bookname + "</td>"
                                    + "     <td>" + quantity + "</td>"
                                    + "     <td>" + price + "</td>");
                            body.append("</tr>");
                        }
                        body.append("</tbody>");
                        body.append("</table>");
                        body.append("Total: " + total + " VND");
                        body.append(" </div>");
                        body.append("</div>");
                        body.append("</body>");
                        body.append("</html>");

                        email.setHtmlMsg(body.toString());
                        email.setTextMsg("Your email client does not support HTML messages");
                        email.send();
                    }
                    cart = null;
                    session.setAttribute("CART", cart);
                    String message = "";
                    if (order.getPaymentStatus().equals("Online")) {
                        message = "Check out successfully, your order has been paid";
                    } else {
                        message = "Check out successfully, your order is pending";
                    }
                    request.setAttribute("CHECKOUT_MESSAGE", message);
                } else {
                    String message = "Not enough quantity for the order";
                    request.setAttribute("CHECKOUT_MESSAGE", message);
                }
            }
        } catch (Exception e) {
            log("Error at OrderController" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
