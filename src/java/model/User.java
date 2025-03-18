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
public class User {
    // attributes / fields
    private int UserID;
    private String RealName;
    private String UserName;
    private String Password;
    private String Role;
    private String Email;
    private Date CreateTime;        
    
    // constructor
    public User() {
    }

    public User(int UserID, String RealName, String UserName, String Password, String Role, String Email, Date CreateTime) {
        this.UserID = UserID;
        this.RealName = RealName;
        this.UserName = UserName;
        this.Password = Password;
        this.Role = Role;
        this.Email = Email;
        this.CreateTime = CreateTime;
    }
    
    // getters
    public int getUserID() {
        return UserID;
    }

    public String getRealName() {
        return RealName;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public String getRole() {
        return Role;
    }

    public String getEmail() {
        return Email;
    }

    public Date getCreateTime() {
        return CreateTime;
    }
    
    // setters
    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public void setRealName(String RealName) {
        this.RealName = RealName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setCreateTime(Date CreateTime) {
        this.CreateTime = CreateTime;
    }
    
    // others



}
