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
public class Order {

    // attributes / fields
    private User UserID;
    private int OrderID;
    private Product ProductID;
    private int Quantity;
    private Date OrderDate;
    
    // constructor
    public Order() {
    }

    public Order(User UserID, int OrderID, Product ProductID, int Quantity, Date OrderDate) {
        this.UserID = UserID;
        this.OrderID = OrderID;
        this.ProductID = ProductID;
        this.Quantity = Quantity;
        this.OrderDate = OrderDate;
    }    

    // getters
    public User getUserID() {
        return UserID;
    }

    public int getOrderID() {
        return OrderID;
    }

    public Product getProductID() {
        return ProductID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public Date getOrderDate() {
        return OrderDate;
    }
    
    // setters
    public void setUserID(User UserID) {
        this.UserID = UserID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public void setProductID(Product ProductID) {
        this.ProductID = ProductID;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public void setOrderDate(Date OrderDate) {
        this.OrderDate = OrderDate;
    }
    
    // others



}
