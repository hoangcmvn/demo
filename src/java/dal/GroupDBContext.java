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
import modal.Group;

/**
 *
 * @author pv
 */
public class GroupDBContext extends DBContext{
    public ArrayList<Group> getGroups(){
        ArrayList<Group> groups = new ArrayList<>(); 
        try {
            String sql = "select gid, gname from [Group]";
            PreparedStatement stm = connection.prepareStatement(sql); 
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Group g = new Group(); 
                g.setId(rs.getInt("gid"));
                g.setName(rs.getString("gname"));
                groups.add(g);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groups;
    }
}
