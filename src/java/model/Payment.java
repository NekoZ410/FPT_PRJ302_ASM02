/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Payment {
    // attributes / fields
    private User userID;
    private int PaymentID;
    private String PaymentMethod;
    private String Address;
    private Date PaymentDate;
    private float Price;
    // constructor
    public Payment() {
    }

    public Payment(User userID, int PaymentID, String PaymentMethod, String Address, Date PaymentDate, float Price) {
        this.userID = userID;
        this.PaymentID = PaymentID;
        this.PaymentMethod = PaymentMethod;
        this.Address = Address;
        this.PaymentDate = PaymentDate;
        this.Price = Price;
    }
    
    // getters
    public User getUserID() {
        return userID;
    }

    public int getPaymentID() {
        return PaymentID;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public String getAddress() {
        return Address;
    }

    public Date getPaymentDate() {
        return PaymentDate;
    }

    public float getPrice() {
        return Price;
    }
    
    // setters
    public void setUserID(User userID) {
        this.userID = userID;
    }

    public void setPaymentID(int PaymentID) {
        this.PaymentID = PaymentID;
    }

    public void setPaymentMethod(String PaymentMethod) {
        this.PaymentMethod = PaymentMethod;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setPaymentDate(Date PaymentDate) {
        this.PaymentDate = PaymentDate;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }
    
    // others



}
