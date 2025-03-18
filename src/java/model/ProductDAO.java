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
public class ProductDAO extends MyDAO {

    public ArrayList<Product> getListProductAll() {
        ArrayList<Product> l = new ArrayList<Product>();
        xSql = "select * from Products";

        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();

            int xProductID;
            String xProductName;
            Category xCategoryID;
            int xUnitsInStock;
            float xUnitPrice;
            int xDiscount;
            String xImage;
            Product x;

            while (rs.next()) {
                xProductID = rs.getInt("ProductID");
                xProductName = rs.getString("ProductName");
                xCategoryID = new CategoryDAO().getCategoryByCategoryID(rs.getInt("CategoryID"));
                xUnitsInStock = rs.getInt("UnitsInStock");
                xUnitPrice = rs.getFloat("UnitPrice");
                xDiscount = rs.getInt("Discount");
                xImage = rs.getString("Image");

                x = new Product(xProductID, xProductName, xCategoryID, xUnitsInStock, xUnitPrice, xDiscount, xImage);
                l.add(x);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (l);
    }

    public ArrayList<Product> getListProductByCategoryID(int CategoryID) {
        ArrayList<Product> l = new ArrayList<Product>();
        xSql = "select * from Products where CategoryID = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, CategoryID);
            rs = ps.executeQuery();

            int xProductID;
            String xProductName;
            Category xCategoryID;
            int xUnitsInStock;
            float xUnitPrice;
            int xDiscount;
            String xImage;
            Product x;

            while (rs.next()) {
                xProductID = rs.getInt("ProductID");
                xProductName = rs.getString("ProductName");
                xCategoryID = new CategoryDAO().getCategoryByCategoryID(rs.getInt("CategoryID"));
                xUnitsInStock = rs.getInt("UnitsInStock");
                xUnitPrice = rs.getFloat("UnitPrice");
                xDiscount = rs.getInt("Discount");
                xImage = rs.getString("Image");

                x = new Product(xProductID, xProductName, xCategoryID, xUnitsInStock, xUnitPrice, xDiscount, xImage);
                l.add(x);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (l);
    }
    
    public ArrayList<Product> getListProductByName(String ProductName) {
        ArrayList<Product> l = new ArrayList<Product>();
        xSql = "select * from Products where ProductName like ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + ProductName + "%");
            rs = ps.executeQuery();

            int xProductID;
            String xProductName;
            Category xCategoryID;
            int xUnitsInStock;
            float xUnitPrice;
            int xDiscount;
            String xImage;
            Product x;

            while (rs.next()) {
                xProductID = rs.getInt("ProductID");
                xProductName = rs.getString("ProductName");
                xCategoryID = new CategoryDAO().getCategoryByCategoryID(rs.getInt("CategoryID"));
                xUnitsInStock = rs.getInt("UnitsInStock");
                xUnitPrice = rs.getFloat("UnitPrice");
                xDiscount = rs.getInt("Discount");
                xImage = rs.getString("Image");

                x = new Product(xProductID, xProductName, xCategoryID, xUnitsInStock, xUnitPrice, xDiscount, xImage);
                l.add(x);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (l);
    }

    public Product getProductByID(int ProductID) {
        Product x = new Product();
        xSql = "select * from Products where ProductID = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, ProductID);
            rs = ps.executeQuery();

            String xProductName;
            Category xCategoryID;
            int xUnitsInStock;
            float xUnitPrice;
            int xDiscount;
            String xImage;

            if (rs.next()) {
                xProductName = rs.getString("ProductName");
                xCategoryID = new CategoryDAO().getCategoryByCategoryID(rs.getInt("CategoryID"));
                xUnitsInStock = rs.getInt("UnitsInStock");
                xUnitPrice = rs.getFloat("UnitPrice");
                xDiscount = rs.getInt("Discount");
                xImage = rs.getString("Image");

                x = new Product(ProductID, xProductName, xCategoryID, xUnitsInStock, xUnitPrice, xDiscount, xImage);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }
    
    public void insertProduct(Product x) {
        xSql = "insert into Products (ProductName, CategoryID, UnitsInStock, UnitPrice, Discount, [Image]) values (?, ?, ?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(xSql); 
            ps.setString(1, x.getProductName());
            ps.setInt(2, x.getCategoryID().getCategoryID());
            ps.setInt(3, x.getUnitsInStock());
            ps.setFloat(4, x.getUnitPrice());
            ps.setInt(5, x.getDiscount());
            ps.setString(6, x.getImage());

            ps.executeUpdate();

            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
    
    public void updateProductByID(Product x) {
        xSql = "update Products set ProductName = ?, CategoryID = ?, UnitsInStock = ?, UnitPrice = ?, Discount = ?, [Image] = ? where ProductID = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getProductName());
            ps.setInt(2, x.getCategoryID().getCategoryID());
            ps.setInt(3, x.getUnitsInStock());
            ps.setFloat(4, x.getUnitPrice());
            ps.setInt(5, x.getDiscount());
            ps.setString(6, x.getImage());
            
            ps.setInt(7, x.getProductID());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteProductByID(int ProductID) {
        xSql = "delete from Products where ProductID = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, ProductID);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
