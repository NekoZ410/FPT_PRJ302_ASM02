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
public class Purchased {

    // attributes / fields
    private User UserID;
    private int PurchasedID;
    private Product ProductID;
    private int Quantity;
    private Date PurchasedDate;
    private Date equiredDate;
    private Date ShippedDate;

    // constructor
    public Purchased() {
    }

    public Purchased(User UserID, int PurchasedID, Product ProductID, int Quantity, Date PurchasedDate, Date equiredDate, Date ShippedDate) {
        this.UserID = UserID;
        this.PurchasedID = PurchasedID;
        this.ProductID = ProductID;
        this.Quantity = Quantity;
        this.PurchasedDate = PurchasedDate;
        this.equiredDate = equiredDate;
        this.ShippedDate = ShippedDate;
    }

    // getters
    public User getUserID() {
        return UserID;
    }

    public int getPurchasedID() {
        return PurchasedID;
    }

    public Product getProductID() {
        return ProductID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public Date getPurchasedDate() {
        return PurchasedDate;
    }

    public Date getEquiredDate() {
        return equiredDate;
    }

    public Date getShippedDate() {
        return ShippedDate;
    }

    // setters
    public void setUserID(User UserID) {
        this.UserID = UserID;
    }

    public void setPurchasedID(int PurchasedID) {
        this.PurchasedID = PurchasedID;
    }

    public void setProductID(Product ProductID) {
        this.ProductID = ProductID;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public void setPurchasedDate(Date PurchasedDate) {
        this.PurchasedDate = PurchasedDate;
    }

    public void setEquiredDate(Date equiredDate) {
        this.equiredDate = equiredDate;
    }

    public void setShippedDate(Date ShippedDate) {
        this.ShippedDate = ShippedDate;
    }
    // others

}
