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
import modal.Book;
import modal.Student;
import modal.Class;
import modal.WaitingBorrow;

/**
 *
 * @author pv
 */
public class BorrowDBContext extends DBContext{
    public int checkTurnBoroww(Student s){
        try {
            String sql = "select count(*) as total from ( SELECT DISTINCT student_id\n" +
                         "FROM WaitingBorrow where student_id != ? ) as tbl"; 
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, s.getId());
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                return (rs.getInt("total"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
       return -1;  
    }
    public int checkNumberBook(Student s){
        try {
            String sql = "select count(*) as total from ( SELECT student_id\n" +
                         "FROM WaitingBorrow where student_id = ? ) as tbl"; 
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, s.getId());
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                return (rs.getInt("total"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
       return -1;  
    }
    public boolean bookExist(Student s, int bid){
        try {
            String sql = "SELECT student_id FROM WaitingBorrow where student_id = ? and book_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, s.getId());
            stm.setInt(2, bid);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public void add(Student s, int bid){
        try {
            String sql = "INSERT INTO[WaitingBorrow]\n" +
                    "           ([student_id]\n" +
                    "           ,[book_id]\n" +
                    "           ,[timeregister]\n" +
                    "           ,[isBrrow])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?\n" +
                    "           ,GETDATE()\n" +
                    "           ,0)";
            PreparedStatement stm = connection.prepareStatement(sql); 
            stm.setInt(1, s.getId());
            stm.setInt(2, bid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int turnNumber(int max){
        try {
            String sql = "select count(*) as total from ( SELECT DISTINCT student_id\n" +
                         "FROM WaitingBorrow) as tbl"; 
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                return max - (rs.getInt("total"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
       return 0;  
    }
    public ArrayList<Student> getAllWaitingBorrow(){
        ArrayList<Student> getAllWaitingBorrow = new ArrayList<>();
        try {
            String sql = "select Distinct s.student_id, sname, dob, gender, class_id, email, s.username from WaitingBorrow as w INNER JOIN Student as s on w.student_id = s.student_id";
            String sql_borrow = "select timeregister, isBrrow, b.book_id, b.name as bname  from WaitingBorrow as w INNER JOIN Student as s on w.student_id = s.student_id\n" +
" INNER JOIN Book as b on w.book_id = b.book_id where s.student_id = ?"; 
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Student s = new Student(); 
                s.setId(rs.getInt("student_id"));
                s.setName(rs.getString("sname"));
                s.setDob(rs.getDate("dob"));
                s.setGender(rs.getBoolean("gender"));
                Class c = new Class(); 
                c.setId(rs.getInt("class_id"));
                s.setC(c);
                s.setEmail(rs.getString("email"));
                Account a = new Account(); 
                a.setUsername(rs.getString("username"));
                // Waiting Borrow
                PreparedStatement stm_sql_borrow = connection.prepareStatement(sql_borrow);
                stm_sql_borrow.setInt(1, s.getId());
                ResultSet rs_borrow = stm_sql_borrow.executeQuery();
                while(rs_borrow.next()){
                    WaitingBorrow w = new WaitingBorrow();
                    w.setIsBorrow(rs.getBoolean("isBrrow"));
                    w.setDob(rs.getDate("timeregister"));
                    w.setStudent(s);
                    Book b = new Book();
                    b.setId(rs.getInt("book_id"));
                    b.setName(rs.getString("bname"));
                    w.setBook(b);
                    s.getWaitingBorrows().add(w);
              }
              getAllWaitingBorrow.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BorrowDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }       
        return getAllWaitingBorrow;
    }
}
