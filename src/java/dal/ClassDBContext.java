/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modal.Class;

/**
 *
 * @author pv
 */
public class ClassDBContext extends DBContext{
    public ArrayList<Class> getClasses(){
        ArrayList<Class> classes = new ArrayList<>(); 
        try {
            String sql = "select class_id, [name]  from Class"; 
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Class c = new Class();
                c.setId(rs.getInt("class_id"));
                c.setName(rs.getString("name"));
                classes.add(c); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classes;
    }
}
