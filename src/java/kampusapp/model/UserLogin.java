/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kampusapp.model;

import java.io.Serializable;

/**
 *
 * @author piryandyp
 */
public class UserLogin implements Serializable {
    private int id;
    private String username;
    private String Password;
    private String role;

    public UserLogin(int id, String username, String Password, String role) {
        this.id = id;
        this.username = username;
        this.Password = Password;
        this.role = role;
    }

    public UserLogin(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}
