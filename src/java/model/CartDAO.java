/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class CartDAO extends MyDAO {

    public ArrayList<Cart> getListCartAll(int UserID) {
        ArrayList<Cart> l = new ArrayList<Cart>();
        xSql = "select * from Carts where UserID = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, UserID);
            rs = ps.executeQuery();

            int xCartID;
            int xProductID;
            int xQuantity;
            Cart x;

            while (rs.next()) {
                xCartID = rs.getInt("CartID");
                xProductID = rs.getInt("ProductID");
                xQuantity = rs.getInt("Quantity");

                x = new Cart(UserID, xCartID, xProductID, xQuantity);
                l.add(x);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (l);
    }

//    public Cart getListCartByUserIDProductID(int UserID, int ProductID) {
//        Cart x = null;
//        xSql = "select * from Carts where UserID = ? and ProductID = ?";
//
//        try {
//            ps = con.prepareStatement(xSql);
//            ps.setInt(1, UserID);
//            rs = ps.executeQuery();
//
//            int xCartID;
//            int xProductID;
//            int xQuantity;
//
//            while (rs.next()) {
//                xCartID = rs.getInt("CartID");
//                xProductID = rs.getInt("ProductID");
//                xQuantity = rs.getInt("Quantity");
//
//                x = new Cart(UserID, xCartID, xProductID, xQuantity);
//            }
//
//            rs.close();
//            ps.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return (x);
//    }

    public int getProductSumQuantity(int productID) {
        int quantity = 0;
        xSql = "select SUM(Quantity) from Carts where ProductID = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, productID);
            rs = ps.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (quantity);
    }

    public void insertCart(Cart x) {
        xSql = "insert into Carts (UserID, ProductID, Quantity) values (?, ?, ?)";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, x.getUserID());
            ps.setInt(2, x.getProductID());
            ps.setInt(3, x.getQuantity());

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteCart(int userID, int cartID) {
        xSql = "delete from Carts where CartID = ? and UserID = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, cartID);
            ps.setInt(2, userID);

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
