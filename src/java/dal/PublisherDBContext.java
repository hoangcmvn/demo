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
import modal.Publisher;

/**
 *
 * @author pv
 */
public class PublisherDBContext extends DBContext{
   public void insert(Publisher p){
        PreparedStatement stm = null;
        try {
            String sql = "INSERT INTO [Publisher]\n" +
                    "           ([name])\n" +
                    "     VALUES\n" +
                    "           (?)";
            stm = connection.prepareStatement(sql); 
            stm.setString(1, p.getName());
            stm.executeUpdate(); 
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }if(connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }    
    }
   public boolean checkExistPname(String pname){
        try {
            String sql = "select [name] from Publisher where [name] like ?"; 
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, pname);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;       
    }
   public ArrayList<Publisher> getPublisher() {
        ArrayList<Publisher> publishers = new ArrayList<>();
        try {
            String sql = "select publisher_id, [name] from Publisher";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Publisher p = new Publisher(); 
                p.setId(rs.getInt("publisher_id"));
                p.setName(rs.getString("name"));
                publishers.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LanguageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return publishers;
    }
    public ArrayList<Publisher> getPublisher(int pageIndex, int pageSize) {
        ArrayList<Publisher> publishers = new ArrayList<>();
        try {
            String sql = "select * from \n" +
                    "( select ROW_NUMBER() OVER (order by publisher_id asc) as row_index,  publisher_id, [name] from Publisher ) "
                    + "as tbl\n" +
                    "where row_index >= (? - 1) * ? + 1 and row_index <= ? * ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageIndex);
            stm.setInt(2, pageSize);
            stm.setInt(3, pageIndex);
            stm.setInt(4, pageSize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Publisher p = new Publisher(); 
                p.setId(rs.getInt("publisher_id"));
                p.setName(rs.getString("name"));
                publishers.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LanguageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return publishers;
    }
     public int count(){
        try {
            String sql = "Select Count(*) as Total from Publisher";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs =  stm.executeQuery();
            while(rs.next()){
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
     public void delete(int id){
        try {
            String sql = "DELETE FROM  [Publisher]\n" +
                    "      WHERE publisher_id = ?"; 
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PublisherDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
