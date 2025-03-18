/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

/**
 *
 * @author Admin
 */
public class signUp extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific err occurs
     * @throws IOException if an I/O err occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet signUp</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet signUp at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific err occurs
     * @throws IOException if an I/O err occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // processRequest(request, response); // uncomment when needed
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        
        String email = request.getParameter("email");
        String realname = request.getParameter("realname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        
        UserDAO ud = new UserDAO();
        if (ud.getUserByEmail(email) != null) {
            request.setAttribute("email", "");
            request.setAttribute("realname", realname);
            request.setAttribute("username", username);
            request.setAttribute("password", "");
            request.setAttribute("repassword", "");
            request.setAttribute("err", "Email existed!");
            request.getRequestDispatcher("signUp.jsp").forward(request, response);
            return;
        }

        if (!password.equals(repassword)) {
            request.setAttribute("email", email);
            request.setAttribute("realname", realname);
            request.setAttribute("username", username);
            request.setAttribute("password", "");
            request.setAttribute("repassword", "");
            request.setAttribute("err", "Confirm password doesn't match");
            request.getRequestDispatcher("signUp.jsp").forward(request, response);
            return;
        }

        if (ud.getUserByUserName(username) == null) {
            ud.insertUser(realname, username, password, "user", email);
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.getRequestDispatcher("signIn.jsp").forward(request, response);
        } else {
            request.setAttribute("email", email);
            request.setAttribute("realname", realname);
            request.setAttribute("username", "");
            request.setAttribute("password", "");
            request.setAttribute("repassword", "");
            request.setAttribute("err", "Account existed!");
            request.getRequestDispatcher("signUp.jsp").forward(request, response);
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific err occurs
     * @throws IOException if an I/O err occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // processRequest(request, response); // uncomment when needed
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
