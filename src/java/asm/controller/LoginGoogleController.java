/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asm.controller;

import asm.google.GooglePojo;
import asm.google.GoogleUtils;
import asm.user.UserDAO;
import asm.user.UserDTO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class LoginGoogleController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String SUCCESS = "user.jsp";
    private static final long serialVersionUID = 1L;

    public LoginGoogleController() {
        super();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            String code = request.getParameter("code");
            if (code == null || code.isEmpty()) {
                RequestDispatcher dis = request.getRequestDispatcher("login.html");
                dis.forward(request, response);
            } else {
                String accessToken = GoogleUtils.getToken(code);
                GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
                String userID = googlePojo.getId();
                String email = googlePojo.getEmail();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String createDate = sdf.format(new Date());
                UserDAO dao = new UserDAO();
                UserDTO checkUser = dao.checkLogin1(userID);
                if (checkUser != null) {
                    if ("Active".equals(checkUser.getStatusID())) {
                        session.setAttribute("LOGIN_USER", checkUser);
                        url = SUCCESS;
                    } else {
                        session.setAttribute("ERROR_MESSAGE", "Your account is unactive, can not login!");
                    }
                } else {
                    UserDTO user = new UserDTO(userID, email, "US", " ", " ", " ", "Active", createDate);
                    boolean checkInsert = dao.insertUserNew(user);
                    if (checkInsert) {
                        session.setAttribute("LOGIN_USER", user);
                        url = SUCCESS;
                    } else {
                        session.setAttribute("ERROR_MESSAGE", "Can not login with google account");
                    }
                }
            }
        } catch (Exception e) {
            log("Error at LoginGoogleController" + e.toString());
        } finally {
            response.sendRedirect(url);
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
