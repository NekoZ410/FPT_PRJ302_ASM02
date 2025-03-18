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
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.*;

/**
 *
 * @author Admin
 */
public class orderViaCart extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet orderViaCart</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet orderViaCart at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // processRequest(request, response); // uncomment when needed
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        
        
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // processRequest(request, response); // uncomment when needed
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("userAccount") == null) {
            response.sendRedirect("signIn.jsp");
            return;
        }
        User u = (User) session.getAttribute("userAccount");
        
        ArrayList<Cart> lcart = new CartDAO().getListCartAll(u.getUserID());
        for (Cart c : lcart) {
            int quan = c.getQuantity();
            ProductDAO pd = new ProductDAO();
            Product p = pd.getProductByID(c.getProductID());            
            OrderDAO od = new OrderDAO();
            
            od.insertOrder(new Order(u, 0, p, quan, null));
            
            new CartDAO().deleteCart(u.getUserID(), c.getCartID());
        }
        
        request.getRequestDispatcher("order.jsp").forward(request, response);
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
