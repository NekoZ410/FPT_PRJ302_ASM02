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
public class productChange extends HttpServlet {

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
            out.println("<title>Servlet productChange</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet productChange at " + request.getContextPath() + "</h1>");
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

        String mode = request.getParameter("mode");
        String pid = request.getParameter("pid");
        int productID = Integer.parseInt(pid);
//        out.print(mode + pid);

        ProductDAO pd = new ProductDAO();
        if (mode != null) {
            if (mode.equals("delete")) {
                pd.deleteProductByID(productID);
            } else if (mode.equals("edit")) {
                Product p = pd.getProductByID(productID);
//                ArrayList<Category> lc = new CategoryDAO().getListCategoryAll();
                request.setAttribute("p", p);
//                request.setAttribute("lc", lc);
                request.getRequestDispatcher("productEdit.jsp").forward(request, response);
                return;
            }
        }
        ArrayList<Product> lp = pd.getListProductAll();

        request.setAttribute("lp", lp);
        request.getRequestDispatcher("productManage.jsp").forward(request, response);
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

        String cmode = request.getParameter("cmode");

        if (cmode.equals("padd")) {
            String newProductName = request.getParameter("pname");
            String newCategoryID = request.getParameter("cid");
            int newcid = Integer.parseInt(newCategoryID);
            String newUnitsInStock = request.getParameter("pstock");
            int newuis = Integer.parseInt(newUnitsInStock);
            String newUnitPrice = request.getParameter("pprice");
            float newprice = Float.parseFloat(newUnitPrice);
            String newDiscount = request.getParameter("pdiscount");
            int newdiscount = Integer.parseInt(newDiscount);
            String newImage = request.getParameter("pimage");
            
//            out.print(newProductName+newCategoryID+newUnitsInStock+newUnitPrice+newDiscount+newImage);

            ProductDAO pd = new ProductDAO();
            CategoryDAO cd = new CategoryDAO();
            pd.insertProduct(new Product(0, newProductName, cd.getCategoryByCategoryID(newcid), newdiscount, newprice, newdiscount, newImage));

            ArrayList<Product> lp = pd.getListProductAll();

            request.setAttribute("lp", lp);
            request.getRequestDispatcher("productManage.jsp").forward(request, response);
        } else if (cmode.equals("pedit")) {
            String newProductID = request.getParameter("pid");
            int newpid = Integer.parseInt(newProductID);
            String newProductName = request.getParameter("pname");
            String newCategoryID = request.getParameter("cid");
            int newcid = Integer.parseInt(newCategoryID);
            String newUnitsInStock = request.getParameter("pstock");
            int newuis = Integer.parseInt(newUnitsInStock);
            String newUnitPrice = request.getParameter("pprice");
            float newprice = Float.parseFloat(newUnitPrice);
            String newDiscount = request.getParameter("pdiscount");
            int newdiscount = Integer.parseInt(newDiscount);
            String newImage = request.getParameter("pimage");

            ProductDAO pd = new ProductDAO();
            CategoryDAO cd = new CategoryDAO();
            pd.updateProductByID(new Product(newpid, newProductName, cd.getCategoryByCategoryID(newcid), newdiscount, newprice, newdiscount, newImage));

            ArrayList<Product> lp = pd.getListProductAll();

            request.setAttribute("lp", lp);
            request.getRequestDispatcher("productManage.jsp").forward(request, response);
        }

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
