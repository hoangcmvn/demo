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
import modal.Group;
import modal.Student;
import modal.Class;

/**
 *
 * @author pv
 */
public class StudentDBContext extends DBContext{
    public ArrayList<Student> getStudent(){
        ArrayList<Student> students = new ArrayList<>(); 
        try {
            String sql_selectStudent = "select student_id, sname, dob, gender, c.class_id,c.[name] as nclass, email, phone, username\n" +
                                       " from Student as s INNER JOIN Class as c on s.class_id = c.class_id"; 
            PreparedStatement stm_sql_selectStudent = connection.prepareStatement(sql_selectStudent);
            ResultSet rs = stm_sql_selectStudent.executeQuery();
            while(rs.next()){
                Student s = new Student();
                s.setId(rs.getInt("student_id"));
                s.setName(rs.getString("sname"));
                s.setDob(rs.getDate("dob"));
                s.setGender(rs.getBoolean("gender"));
                Class c = new Class();
                c.setId(rs.getInt("class_id"));
                c.setName(rs.getString("nclass"));
                s.setC(c);
                s.setEmail(rs.getString("email"));
                s.setPhone(rs.getString("phone"));
                Account a = new Account(); 
                a.setUsername(rs.getString("username"));
                String sql_selectAccountGroup = "select g.gid, g.gname from Account as a INNER JOIN Account_Group as ag on a.username = ag.username\n" +
                                                " INNER JOIN Student as s on s.username = a.username\n" +
                                                " INNER JOIN [Group] as g on g.gid= ag.gid where a.username = ?";
                PreparedStatement stm_selectAccountGroup = connection.prepareStatement(sql_selectAccountGroup); 
                stm_selectAccountGroup.setString(1,a.getUsername());
                ResultSet rs_selectAccountGroup = stm_selectAccountGroup.executeQuery(); 
                while(rs_selectAccountGroup.next()){
                    Group g = new Group(); 
                    g.setId(rs_selectAccountGroup.getInt("gid"));
                    g.setName(rs_selectAccountGroup.getString("gname"));
                    a.getGroups().add(g);
                }
                s.setAccount(a);
                students.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
     return students;
    }
}
