/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pv
 */
public class DBContext {
    protected Connection connection; 
    public DBContext(){
        try {
            String user = "thanhnthe153122";
            String password = "123456";
            String url = "jdbc:sqlserver://LAPTOP-BQH0F96N:1433;databaseName=LibraryProject";
            // load driver jdbc
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // kết nối với cơ sở dũ liệu
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
