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
public class addCart extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addCart</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addCart at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        // processRequest(request, response); // uncomment when needed
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("userAccount") == null) {
            response.sendRedirect("signIn.jsp");
            return;
        }
        User u = (User) session.getAttribute("userAccount");

        String pid = request.getParameter("pid");
        String quantity = request.getParameter("quantity");
        int quan = Integer.parseInt(quantity);
        int productID = Integer.parseInt(pid);

        ProductDAO pd = new ProductDAO();
        CartDAO cd = new CartDAO();
        if (quan < 0) {
            request.setAttribute("p", pd.getProductByID(productID));
            request.setAttribute("err", "Invalid quantity");
            request.getRequestDispatcher("product.jsp").forward(request, response);
            return;
        }
        
        if ((quan + cd.getProductSumQuantity(quan)) > pd.getProductByID(productID).getUnitsInStock()) {
            request.setAttribute("p", pd.getProductByID(productID));
            request.setAttribute("err", "Selected amount cannot be larger than numbers in stock");
            request.getRequestDispatcher("product.jsp").forward(request, response);
            return;
        }

        Cart c = new Cart(u.getUserID(), 0, productID, quan);
        cd.insertCart(c);
        
        ArrayList<Cart> lcart = cd.getListCartAll(u.getUserID());
        request.setAttribute("lcart", lcart);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
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
        // processRequest(request, response); // uncomment when needed
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");

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
