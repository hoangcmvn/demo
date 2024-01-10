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
import modal.Language;

/**
 *
 * @author pv
 */
public class LanguageDBContext extends DBContext{
    public void insert(Language l){
        PreparedStatement stm = null;
        try {
            String sql = "INSERT INTO [Language]\n" +
                    "           (name_language)\n" +
                    "     VALUES\n" +
                    "           (?)";
            stm = connection.prepareStatement(sql); 
            stm.setString(1, l.getName());
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
    public boolean checkExistLname(String lname){
        try {
            String sql = "select name_language from [Language] where name_language like ?"; 
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lname);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;       
    }
    public ArrayList<Language> getLanguages(){
        ArrayList<Language> languages = new ArrayList<>(); 
        try {
            String sql = "select language_id, name_language from Language"; 
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery(); 
            while(rs.next()){
               Language l = new Language();
               l.setId(rs.getInt("language_id"));
               l.setName(rs.getString("name_language"));
               languages.add(l); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(LanguageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return languages; 
    }
    public ArrayList<Language> getLanguages(int pageIndex, int pageSize){
        ArrayList<Language> languages = new ArrayList<>(); 
        try {
            String sql = "select * from \n" +
                        "( select ROW_NUMBER() OVER (order by language_id asc) as row_index,  language_id, name_language from Language ) "
                      + "as tbl \n" +
                        "where row_index >= (? - 1) * ? + 1 and row_index <= ? * ?"; 
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageIndex);
            stm.setInt(2, pageSize);
            stm.setInt(3, pageIndex);
            stm.setInt(4,pageSize); 
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
               Language l = new Language();
               l.setId(rs.getInt("language_id"));
               l.setName(rs.getString("name_language"));
               languages.add(l); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(LanguageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return languages; 
    }
    public int count(){
        try {
            String sql = "Select Count(*) as Total from Language";
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
            String sql = "DELETE FROM  [Language]\n" +
                    "      WHERE language_id = ?"; 
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PublisherDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
