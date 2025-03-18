/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Category {

    // attributes / fields
    private int CategoryID;
    private String CategoryName;
    private String Description;

    // constructor
    public Category() {
    }

    public Category(int CategoryID, String CategoryName, String Description) {
        this.CategoryID = CategoryID;
        this.CategoryName = CategoryName;
        this.Description = Description;
    }

    // getters
    public int getCategoryID() {
        return CategoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public String getDescription() {
        return Description;
    }

    // setters
    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    // others
}
