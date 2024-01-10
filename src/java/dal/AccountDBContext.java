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
import modal.Account;
import modal.Employee;
import modal.Group;
import modal.Student;
import modal.Class;
/**
 *
 * @author pv
 */
public class AccountDBContext extends DBContext{
    public Account getAccount(String user, String pass){
        String sql = "select username, [password], fullname from Account\n" +
                    "where username = ? and [password] = ?"; 
        PreparedStatement stm = null; 
        try { 
            stm = connection.prepareStatement(sql);
            stm.setString(1, user);
            stm.setString(2, pass);
            ResultSet rs = stm.executeQuery(); 
            while(rs.next()){
               Account account = new Account(); 
               account.setUsername(rs.getString("username"));
               account.setPassword(rs.getString("password"));
               account.setFullname(rs.getString("fullname"));
               return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;   
    } 
    public int getPermission(String user, String url){
        String sql = "select count(*) as total from Account as a INNER JOIN Account_Group as ag on a.username = ag.username\n" +
                     "INNER JOIN [Group] as g on ag.gid = g.gid\n" +
                     "INNER JOIN Group_Feature as gf on gf.gid = g.gid\n" +
                     "INNER JOIN Feature as f on f.fid = gf.fid\n" +
                     "where a.username = ? and f.url = ? "; 
        PreparedStatement stm = null; 
        try { 
            stm = connection.prepareStatement(sql);
            stm.setString(1, user);
            stm.setString(2, url);
            ResultSet rs = stm.executeQuery(); 
            while(rs.next()){
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(stm != null){
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return -1;   
    }
    public boolean checkAccountExist(String user){
        String sql = "select * from Account where username = ? "; 
        PreparedStatement stm = null; 
        try { 
            stm = connection.prepareStatement(sql);
            stm.setString(1, user);
            ResultSet rs = stm.executeQuery(); 
            while(rs.next()){
               return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;   
    }
    public void createAccountTeacher(Account account, Employee e){
        try {
            String sql_insertAccount = "INSERT INTO [Account]\n" +
                    "           ([username]\n" +
                    "           ,[password]\n" +
                    "           ,[fullname])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?\n" +
                    "           ,?)";
            String sql_insertEmployee = "INSERT INTO [Employee]\n" +
                    "           ([ename]\n" +
                    "           ,[gender]\n" +
                    "           ,[dob]\n" +
                    "           ,[email]\n" +
                    "           ,[username])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?)";
            String sql_insertAccount_Group = "INSERT INTO [Account_Group]\n" +
                    "           ([username]\n" +
                    "           ,[gid])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?)";
            connection.setAutoCommit(false);
            // insert Account
            PreparedStatement stm_insertAccount = connection.prepareStatement(sql_insertAccount);
            stm_insertAccount.setString(1, account.getUsername());
            stm_insertAccount.setString(2, account.getPassword());
            stm_insertAccount.setString(3, account.getFullname());
            stm_insertAccount.executeUpdate(); 
            // insert Employee
            PreparedStatement stm_insertEmployee = connection.prepareStatement(sql_insertEmployee); 
            stm_insertEmployee.setString(1, e.getEname());
            stm_insertEmployee.setBoolean(2, e.isGender());
            stm_insertEmployee.setDate(3, e.getDob());
            stm_insertEmployee.setString(4, e.getEmail());
            stm_insertEmployee.setString(5, account.getUsername());
            stm_insertEmployee.executeUpdate(); 
            // insert Account_Group
            for (Group group : account.getGroups()) {
                  PreparedStatement stm_insertAccount_Group = connection.prepareStatement(sql_insertAccount_Group); 
                  stm_insertAccount_Group.setString(1, account.getUsername());
                  stm_insertAccount_Group.setInt(2, group.getId());
                  stm_insertAccount_Group.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally
        {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }         
        }      
    }
    public void createAccountStudent(Account account, Student s){
        try {
            String sql_insertAccount = "INSERT INTO [Account]\n" +
                    "           ([username]\n" +
                    "           ,[password]\n" +
                    "           ,[fullname])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?\n" +
                    "           ,?)";
            String sql_insertStudent = "INSERT INTO [Student]\n" +
                                "           ([sname]\n" +
                                "           ,[dob]\n" +
                                "           ,[gender]\n" +
                                "           ,[class_id]\n" +
                                "           ,[email]\n" +
                                "           ,[phone]\n" +
                                "           ,[username])\n" +
                                "     VALUES\n" +
                                "           (?\n" +
                                "           ,?\n" +
                                "           ,?\n" +
                                "           ,?\n" +
                                "           ,?\n" +
                                "           ,?\n" +
                                "           ,?)";
            String sql_insertAccount_Group = "INSERT INTO [Account_Group]\n" +
                    "           ([username]\n" +
                    "           ,[gid])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?)";
            connection.setAutoCommit(false);
            // insert Account
            PreparedStatement stm_insertAccount = connection.prepareStatement(sql_insertAccount);
            stm_insertAccount.setString(1, account.getUsername());
            stm_insertAccount.setString(2, account.getPassword());
            stm_insertAccount.setString(3, account.getFullname());
            stm_insertAccount.executeUpdate(); 
            // insert Student
            PreparedStatement stm_sql_insertStudent = connection.prepareStatement(sql_insertStudent); 
            stm_sql_insertStudent.setString(1, s.getName());
            stm_sql_insertStudent.setDate(2, s.getDob());
            stm_sql_insertStudent.setBoolean(3, s.isGender());
            stm_sql_insertStudent.setInt(4,s.getC().getId()); 
            stm_sql_insertStudent.setString(5, s.getEmail());
            stm_sql_insertStudent.setString(6, s.getPhone());
            stm_sql_insertStudent.setString(7, account.getUsername());
            stm_sql_insertStudent.executeUpdate(); 
            // insert Account_Group
            for (Group group : account.getGroups()) {
                  PreparedStatement stm_insertAccount_Group = connection.prepareStatement(sql_insertAccount_Group); 
                  stm_insertAccount_Group.setString(1, account.getUsername());
                  stm_insertAccount_Group.setInt(2, group.getId());
                  stm_insertAccount_Group.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally
        {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }         
        }      
    }
    public ArrayList<Account> getAccountsEmployee(){
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            String sql = "select a.username, [password], fullname from Account as a INNER JOIN Employee as e on a.username = e.username";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
               Account a = new Account();
               a.setUsername(rs.getString("username"));
               a.setPassword(rs.getString("password"));
               a.setFullname(rs.getString("fullname"));
               String sql_Account_Group = "select a.username,ag.gid, g.gname from Account as a "
                                      + " INNER JOIN Account_Group as ag on a.username = ag.username\n" +
                                           " INNER JOIN [Group] as g on ag.gid = g.gid where a.username = ?";
               PreparedStatement stm_sql_Account_Group = connection.prepareStatement(sql_Account_Group);
               stm_sql_Account_Group.setString(1, a.getUsername());
               ResultSet rs_Account_Group = stm_sql_Account_Group.executeQuery();
               while(rs_Account_Group.next()){
                   Group g = new Group();
                   g.setId(rs_Account_Group.getInt("gid"));
                   g.setName(rs_Account_Group.getString("gid"));
                   a.getGroups().add(g); 
               }
               accounts.add(a); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accounts;
    }
    public ArrayList<Account> getAllUsernameEmployee(){
        ArrayList<Account> getAllUsernames = new ArrayList<>(); 
        try {
            String sql = "select a.username from Account as a INNER JOIN Employee as e on a.username = e.username"; 
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
               Account a = new Account(); 
               a.setUsername(rs.getString("username"));
               getAllUsernames.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getAllUsernames; 
    }
    public void updateAccount_Group(Account a){
        try {             
            String sql_delete = "DELETE FROM [Account_Group] where username = ?";
            String sql_insert = "INSERT [Account_Group]\n" +
                    "           ([username]\n" +
                    "           ,[gid])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?)";
            connection.setAutoCommit(false);
            // xóa toàn bộ bản ghi của acc employee đó và insert lại từ đầu
            PreparedStatement stm_sql_delete = connection.prepareStatement(sql_delete); 
            stm_sql_delete.setString(1, a.getUsername());
            stm_sql_delete.executeUpdate();
            // insert
            PreparedStatement stm_sql_insert = connection.prepareStatement(sql_insert);
            for (Group group : a.getGroups()) {
                 stm_sql_insert.setString(1, a.getUsername());
                 stm_sql_insert.setInt(2, group.getId());
                 stm_sql_insert.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally
        {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            //close connection
            //close connection
        }
    }
    public boolean isTeacher(Account a){
        try {
            String sql = "select * from Account as a INNER JOIN Employee as e on a.username = e.username where a.username = ? "; 
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, a.getUsername());
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
   } 
    public boolean isStudent(Account a){
        try {
            String sql = "select * from Account as a INNER JOIN Student as s on a.username = s.username where a.username = ? "; 
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, a.getUsername());
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
   }
    public Student getStudent(Account a){
        try {
            String sql = "select student_id, sname, dob, gender, class_id, email, phone, a.username from Account as a \n" +
                    "INNER JOIN Student as s on a.username = s.username where a.username = ? "; 
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, a.getUsername());
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Student s = new Student(); 
                s.setId(rs.getInt("student_id"));
                s.setName(rs.getString("sname"));
                s.setDob(rs.getDate("dob"));
                s.setGender(rs.getBoolean("gender"));
                s.setEmail(rs.getString("email"));
                s.setPhone(rs.getString("phone"));
                Class c = new Class(); 
                c.setId(rs.getInt("class_id"));
                Account acc = new Account(); 
                acc.setUsername(rs.getString("username"));
                s.setC(c);
                s.setAccount(acc);
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
