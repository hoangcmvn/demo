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
import modal.Category;

/**
 *
 * @author pv
 */
public class CategoryDBContext extends DBContext{
    public ArrayList<Category> getAllCategories(){
        ArrayList<Category> categories = new ArrayList<>(); 
        String sql = "select category_id, [name] from Categories";
        PreparedStatement stm = null; 
        try { 
            stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery(); 
            while(rs.next()){
                Category c = new Category(); 
                c.setId(rs.getInt("category_id"));
                c.setName(rs.getString("name"));
                categories.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return categories;
    }
    public void insert(Category c){
        PreparedStatement stm = null;
        try {
            String sql = "INSERT INTO [Categories]\n" +
                    "           ([name])\n" +
                    "     VALUES\n" +
                    "           (?)";
            stm = connection.prepareStatement(sql); 
            stm.setString(1, c.getName());
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
    public boolean checkExistCname(String cname){
        try {
            String sql = "select [name] from Categories where [name] like ?"; 
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, cname);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;       
    }
    public void delete(int id){
        try {
            String sql = "DELETE FROM  [Categories]\n" +
                    "      WHERE category_id = ?"; 
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public ArrayList<Category> getAllCategories(int pageIndex, int pageSize){
        ArrayList<Category> categories = new ArrayList<>(); 
        String sql = "select * from \n" +
                    "( select ROW_NUMBER() OVER (order by category_id asc) as row_index, category_id, [name] from Categories ) as tbl\n" +
                    "where row_index >= (? - 1) * ? + 1 and row_index <= ? * ?";
        try { 
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageIndex);
            stm.setInt(2, pageSize);
            stm.setInt(3, pageIndex); 
            stm.setInt(4, pageSize);
            ResultSet rs = stm.executeQuery(); 
            while(rs.next()){
                Category c = new Category(); 
                c.setId(rs.getInt("category_id"));
                c.setName(rs.getString("name"));
                categories.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }
     public int count(){
        try {
            String sql = "Select Count(*) as Total from Categories";
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
}
