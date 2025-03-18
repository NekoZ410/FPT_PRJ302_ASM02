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
public class UserDAO extends MyDAO {

    public User getUserByUserNamePassword(String UserName, String Password) {
        User x = null;
        xSql = "select * from Users where (UserName = ?) and (Password = ?)";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, UserName);
            ps.setString(2, Password);
            rs = ps.executeQuery();

            int xUserID;
            String xRealName;
            String xUserName;
            String xPassword;
            String xRole;
            String xEmail;
            Date xCreateTime;

            if (rs.next()) {
                xUserID = rs.getInt("UserID");
                xRealName = rs.getString("RealName");
                xUserName = rs.getString("UserName");
                xPassword = rs.getString("Password");
                xRole = rs.getString("Role");
                xEmail = rs.getString("Email");
                xCreateTime = rs.getDate("CreateTime");

                x = new User(xUserID, xRealName, xUserName, xPassword, xRole, xEmail, xCreateTime);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }
    
    public User getUserByUserName(String UserName) {
        User x = null;
        xSql = "select * from Users where UserName = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, UserName);
            rs = ps.executeQuery();

            int xUserID;
            String xRealName;
            String xUserName;
            String xPassword;
            String xRole;
            String xEmail;
            Date xCreateTime;

            if (rs.next()) {
                xUserID = rs.getInt("UserID");
                xRealName = rs.getString("RealName");
                xUserName = rs.getString("UserName");
                xPassword = rs.getString("Password");
                xRole = rs.getString("Role");
                xEmail = rs.getString("Email");
                xCreateTime = rs.getDate("CreateTime");

                x = new User(xUserID, xRealName, xUserName, xPassword, xRole, xEmail, xCreateTime);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }
    
    public User getUserByUserID(int UserID) {
        User x = null;
        xSql = "select * from Users where UserID = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, UserID);
            rs = ps.executeQuery();

            int xUserID;
            String xRealName;
            String xUserName;
            String xPassword;
            String xRole;
            String xEmail;
            Date xCreateTime;

            if (rs.next()) {
                xUserID = rs.getInt("UserID");
                xRealName = rs.getString("RealName");
                xUserName = rs.getString("UserName");
                xPassword = rs.getString("Password");
                xRole = rs.getString("Role");
                xEmail = rs.getString("Email");
                xCreateTime = rs.getDate("CreateTime");

                x = new User(xUserID, xRealName, xUserName, xPassword, xRole, xEmail, xCreateTime);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }
    
    public User getUserByEmail(String Email) {
        User x = null;
        xSql = "select * from Users where Email = ?";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, Email);
            rs = ps.executeQuery();

            int xUserID;
            String xRealName;
            String xUserName;
            String xPassword;
            String xRole;
            String xEmail;
            Date xCreateTime;

            if (rs.next()) {
                xUserID = rs.getInt("UserID");
                xRealName = rs.getString("RealName");
                xUserName = rs.getString("UserName");
                xPassword = rs.getString("Password");
                xRole = rs.getString("Role");
                xEmail = rs.getString("Email");
                xCreateTime = rs.getDate("CreateTime");

                x = new User(xUserID, xRealName, xUserName, xPassword, xRole, xEmail, xCreateTime);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (x);
    }

    public void insertUser(String RealName, String UserName, String Password, String Role, String Email) {
        xSql = "insert into Users (RealName, UserName, [Password], [Role], Email) values (?, ?, ?, ?, ?)";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, RealName);
            ps.setString(2, UserName);
            ps.setString(3, Password);
            ps.setString(4, Role);
            ps.setString(5, Email);

            ps.executeUpdate();

            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
