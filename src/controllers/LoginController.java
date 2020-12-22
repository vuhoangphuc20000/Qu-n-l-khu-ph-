/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Model.UserModel;
import services.SQLConnection;
/**
 *
 * @author Phúc
 */
public class LoginController {
     public static UserModel currentUser = new UserModel();
    
    public boolean login(String userName, String password) throws SQLException, ClassNotFoundException{
         try {
             Connection connection = SQLConnection.getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM [USER] WHERE USERNAME = '" + userName +"';");
             if (rs == null) {
                 return false;
             }
             while (rs.next()) {
                 if (rs.getString("PASSWORD") == null ? password == null : rs.getString("PASSWORD").equals(password)) {
                     currentUser.setUserID(rs.getInt("USERID"));
                     currentUser.setUserName(rs.getString("USERNAME"));
                     return true;
                 }
             }}
         catch(Exception e){
             e.printStackTrace();
         }
        return false;
    }
}
