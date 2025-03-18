/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class PurchasedDAO extends MyDAO {

    public ArrayList<Purchased> getListOrderAll(int UserID, int PurchasedID) {
        ArrayList<Purchased> l = new ArrayList<Purchased>();
        xSql = "select * from Orders where UserID = ? and PurchasedID = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, UserID);
            ps.setInt(2, PurchasedID);
            rs = ps.executeQuery();

            User xUserID;
            int xPurchasedID;
            Product xProductID;
            int xQuantity;
            Date xPurchasedDate;
            Date xequiredDate;
            Date xShippedDate;
            Purchased x;

            while (rs.next()) {
                xUserID = new UserDAO().getUserByUserID(rs.getInt("UserID"));
                xPurchasedID = rs.getInt("PurchasedID");
                xProductID = new ProductDAO().getProductByID(rs.getInt("ProductID"));
                xQuantity = rs.getInt("Quantity");
                xPurchasedDate = rs.getDate("PurchasedDate");
                xequiredDate = rs.getDate("RequiredDate");
                xShippedDate = rs.getDate("ShippedDate");

                x = new Purchased(xUserID, PurchasedID, xProductID, xQuantity, xPurchasedDate, xequiredDate, xShippedDate);
                l.add(x);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (l);
    }

    public int getMaxPurchasedIDByUserID(int UserID) {
        int purchasedID = 0;
        xSql = "  SELECT MAX(PurchasedID) AS maxID\n"
                + "  FROM Purchased\n"
                + "  WHERE UserID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, UserID);
            rs = ps.executeQuery();
            if (rs.next()) {
                purchasedID = rs.getInt("maxID");
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return purchasedID;
    }

    public void insertPurchased(int userID, ArrayList<Order> listOrder) {
        int purchasedID = getMaxPurchasedIDByUserID(userID) + 1;
        xSql = "insert into Purchased(UserID, PurchasedID, ProductID, Quantity) VALUES";

        for (int i = 0; i < listOrder.size(); i++) {
            if (i != (listOrder.size() - 1)) {
                xSql += "(" + userID + ", " + purchasedID + ", " + listOrder.get(i).getProductID().getProductID() + ", " + listOrder.get(i).getQuantity() + "),\n";
            } else {
                xSql += "(" + userID + ", " + purchasedID + ", " + listOrder.get(i).getProductID().getProductID() + ", " + listOrder.get(i).getQuantity() + ")";
            }
        }

        try {
            ps = con.prepareStatement(xSql);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
