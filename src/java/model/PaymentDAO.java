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
public class PaymentDAO extends MyDAO{
    
    public ArrayList<Payment> getPaymentAll() {
        ArrayList<Payment> l = new ArrayList<Payment>();
        xSql = "select * from Payments";

        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            
            User xUserID;
            int xPaymentID;
            String xPaymentMethod;
            String xAddress;
            Date xPaymentDate;
            float xPrice;            
            Payment x;
            
            while (rs.next()) {
                xUserID = new UserDAO().getUserByUserID(rs.getInt("UserID"));
                xPaymentID = rs.getInt("PaymentID");
                xPaymentMethod = rs.getString("PaymentMethod");
                xAddress = rs.getString("Address");
                xPaymentDate = rs.getDate("PaymentDate");
                xPrice = rs.getFloat("Price");
                
                x = new Payment(xUserID, xPaymentID, xPaymentMethod, xAddress, xPaymentDate, xPrice);
                l.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (l);
    }
    
    public ArrayList<Payment> getPaymentByUserID(int userID) {
        ArrayList<Payment> l = new ArrayList<Payment>();
        xSql = "select * from Payments where UserID = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userID);
            rs = ps.executeQuery();

            int xPaymentID;
            String xPaymentMethod;
            String xAddress;
            Date xPaymentDate;
            float xPrice;            
            Payment x;
            
            while (rs.next()) {
                xPaymentID = rs.getInt("PaymentID");
                xPaymentMethod = rs.getString("PaymentMethod");
                xAddress = rs.getString("Address");
                xPaymentDate = rs.getDate("PaymentDate");
                xPrice = rs.getFloat("Price");
                
                x = new Payment(new UserDAO().getUserByUserID(userID), xPaymentID, xPaymentMethod, xAddress, xPaymentDate, xPrice);
                l.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (l);
    }
    
    public void insertPayment(Payment x, int userID) {
        xSql = "insert into Payments (UserID, PaymentMethod, Address, Price) values (?, ?, ?, ?)";
        
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userID);
            ps.setString(2, x.getPaymentMethod());
            ps.setString(3, x.getAddress());
            ps.setDouble(4, x.getPrice());

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
