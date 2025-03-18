/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

/**
 *
 * @author Admin
 */
public class Cart {
    // attributes / fields
    private int UserID;
    private int CartID;
    private int ProductID;
    private int Quantity;
    
    // constructor
    public Cart() {
    }

    public Cart(int UserID, int CartID, int ProductID, int Quantity) {
        this.UserID = UserID;
        this.CartID = CartID;
        this.ProductID = ProductID;
        this.Quantity = Quantity;
    }
    
    // getters
    public int getUserID() {
        return UserID;
    }

    public int getCartID() {
        return CartID;
    }

    public int getProductID() {
        return ProductID;
    }

    public int getQuantity() {
        return Quantity;
    }
    
    // setters
    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public void setCartID(int CartID) {
        this.CartID = CartID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }
    
    // others



}
