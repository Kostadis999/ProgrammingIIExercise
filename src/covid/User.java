/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid;

/**
 *
 * @author stefm
 */
public class User {
    private String username;
    private String password;
    public User(String user, String pass){
        this.username=user;
        this.password=pass;
    }
    public String getUser(){
        return username;
    }
    public String getPass(){
        return password;
    }
}
