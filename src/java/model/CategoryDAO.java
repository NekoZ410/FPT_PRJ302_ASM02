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
public class CategoryDAO extends MyDAO {

    public ArrayList<Category> getListCategoryAll() {
        ArrayList<Category> l = new ArrayList<Category>();
        xSql = "select * from Categories";

        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();

            int xCategoryID;
            String xCategoryName;
            String xDescription;
            Category x;

            while (rs.next()) {
                xCategoryID = rs.getInt("CategoryID");
                xCategoryName = rs.getString("CategoryName");
                xDescription = rs.getString("Description");

                x = new Category(xCategoryID, xCategoryName, xDescription);
                l.add(x);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (l);
    }

    public Category getCategoryByCategoryID(int CategoryID) {
        Category x = new Category();
        xSql = "select * from Categories where CategoryID = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, CategoryID);
            rs = ps.executeQuery();

            String xCategoryName;
            String xDescription;

            if (rs.next()) {
                xCategoryName = rs.getString("CategoryName");
                xDescription = rs.getString("Description");

                x = new Category(CategoryID, xCategoryName, xDescription);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }
}
