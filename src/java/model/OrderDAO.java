/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class OrderDAO extends MyDAO {

    public ArrayList<Order> getListOrderAll(int UserID) {
        ArrayList<Order> l = new ArrayList<Order>();
        xSql = "select * from Orders where UserID = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, UserID);
            rs = ps.executeQuery();

            User xUserID;
            int xOrderID;
            Product xProductID;
            int xQuantity;
            Date xOrderDate;
            Order x;

            while (rs.next()) {
                xUserID = new UserDAO().getUserByUserID(rs.getInt("UserID"));
                xOrderID = rs.getInt("OrderID");
                xProductID = new ProductDAO().getProductByID(rs.getInt("ProductID"));
                xQuantity = rs.getInt("Quantity");
                xOrderDate = rs.getDate("OrderDate");
                
                x = new Order(xUserID, xOrderID, xProductID, xQuantity, xOrderDate);
                l.add(x);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (l);
    }
    
//    public Order getOrderByUserIDAndProductID(int UserID, int ProductID) {
//        Order x = new Order();
//        xSql = "select * from Orders where UserID = ? and ProductID";
//
//        try {
//            ps = con.prepareStatement(xSql);
//            ps.setInt(1, UserID);
//            ps.setInt(1, UserID);
//            rs = ps.executeQuery();
//
//            User xUserID;
//            int xOrderID;
//            Product xProductID;
//            int xQuantity;
//            Date xOrderDate;
//            Order x;
//
//            while (rs.next()) {
//                xUserID = new UserDAO().getUserByUserID(rs.getInt("UserID"));
//                xOrderID = rs.getInt("OrderID");
//                xProductID = new ProductDAO().getProductByID(rs.getInt("ProductID"));
//                xQuantity = rs.getInt("Quantity");
//                xOrderDate = rs.getDate("OrderDate");
//                
//                x = new Order(xUserID, xOrderID, xProductID, xQuantity, xOrderDate);
//                l.add(x);
//            }
//
//            rs.close();
//            ps.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return (l);
//    }
    
    public void insertOrder(Order x) {
        xSql = "insert into Orders (UserID, ProductID, Quantity) values (?, ?, ?)";
        try {

            ps = con.prepareStatement(xSql);
            ps.setInt(1, x.getUserID().getUserID());
            ps.setInt(2, x.getProductID().getProductID());
            ps.setInt(3, x.getQuantity());

            ps.executeUpdate();

            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int UserID, int OrderID) {
        xSql = "delete from Orders where (OrderID = ?) and (UserID = ?)";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, OrderID);
            ps.setInt(2, UserID);
            ps.executeUpdate();
            //con.commit();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
