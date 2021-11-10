/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asm.controller;

import asm.user.UserDAO;
import asm.user.UserDTO;
import asm.user.UserError;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class CreateController extends HttpServlet {

    private static final String ERROR = "createUser.jsp";
    private static final String SUCCESS = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError userError = new UserError("", "", "", "", "");
        try {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String statusID = "Active";
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            String createDate = sdf.format(new Date());
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");

            boolean check = true;
            if (userID.length() > 10 || userID.length() < 2) {
                userError.setUserIDError("user ID [2,10]");
                check = false;
            }
            if (fullName.length() < 5) {
                userError.setFullNameError("Full Name > 5 characters");
                check = false;
            }
            if (!password.equals(confirm)) {
                userError.setConfirmPasswordError("Password not match");
                check = false;
            }
            if (check) {
                UserDAO dao = new UserDAO();
                boolean checkDuplicate = dao.checkDuplicate(userID);
                if (checkDuplicate) {
                    userError.setUserIDError("Duplicate UserID: " + userID);
                    request.setAttribute("USER_ERROR", userError);
                } else {
                    UserDTO user = new UserDTO(userID, fullName, roleID, password, phone, address, statusID, createDate);
                    boolean checkInsert = dao.insertUserNew(user);
                    if (checkInsert) {
                        url = SUCCESS;
                    }
                }
            } else {
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (Exception e) {
            log("Error at CreateController:"+ e.toString());
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
