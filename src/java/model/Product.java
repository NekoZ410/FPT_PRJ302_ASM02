/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author Admin
 */
public class Product {

    // attributes / fields
    private int ProductID;
    private String ProductName;
    private Category CategoryID;
    private int UnitsInStock;
    private float UnitPrice;
    private int Discount;
    private String Image;
    
    // constructor
    public Product() {
    }

    public Product(int ProductID, String ProductName, Category CategoryID, int UnitsInStock, float UnitPrice, int Discount, String Image) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.CategoryID = CategoryID;
        this.UnitsInStock = UnitsInStock;
        this.UnitPrice = UnitPrice;
        this.Discount = Discount;
        this.Image = Image;
    }

    // getters
    public int getProductID() {
        return ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public Category getCategoryID() {
        return CategoryID;
    }

    public int getUnitsInStock() {
        return UnitsInStock;
    }

    public float getUnitPrice() {
        return UnitPrice;
    }

    public int getDiscount() {
        return Discount;
    }

    public String getImage() {
        return Image;
    }
    
    // setters
    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public void setCategoryID(Category CategoryID) {
        this.CategoryID = CategoryID;
    }

    public void setUnitsInStock(int UnitsInStock) {
        this.UnitsInStock = UnitsInStock;
    }

    public void setUnitPrice(float UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public void setDiscount(int Discount) {
        this.Discount = Discount;
    }
    
    public void setImage(String Image) {
        this.Image = Image;
    }
    
    // others

    public String getCategoryName() {
        return CategoryID.getCategoryName();
    }

    public String getCategoryDesc() {
        return CategoryID.getDescription();
    }

    public float getDiscountedPrice() {
        return UnitPrice - (UnitPrice * Discount / 100);
    }


}
