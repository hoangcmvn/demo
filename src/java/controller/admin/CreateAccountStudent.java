/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import controller.authorization.BaseAuthController;
import dal.AccountDBContext;
import dal.ClassDBContext;
import dal.GroupDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Account;
import modal.Class;
import modal.Group;
import modal.Student;

/**
 *
 * @author pv
 */
public class CreateAccountStudent extends BaseAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClassDBContext classDB = new ClassDBContext(); 
        ArrayList<Class> classes = classDB.getClasses();
        request.setAttribute("classes", classes);
        request.getRequestDispatcher("../../view/admin/createAccount/student.jsp").forward(request, response);
    }


    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        AccountDBContext accountDB = new AccountDBContext();
        String username = request.getParameter("username");
        String password = request.getParameter("password"); 
        String fullname = request.getParameter("fullname");
        if(accountDB.checkAccountExist(username)){
            ClassDBContext classDB = new ClassDBContext(); 
            ArrayList<Class> classes = classDB.getClasses();
            request.setAttribute("classes", classes);
            request.setAttribute("message_existAccount", "Tài khoản này đã tồn tại, bạn hãy chọn tên khác");
            request.getRequestDispatcher("../../view/admin/createAccount/student.jsp").forward(request, response);
            return;
        }
        // Account
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setFullname(fullname);
        Group g = new Group();
        // fix cứng id = 4 - user ( NOTICE ) 
        g.setId(4);
        account.getGroups().add(g);
        // Student
        String sname = request.getParameter("sname"); 
        String raw_dob = request.getParameter("dob"); 
        String email = request.getParameter("email");
        String phone = request.getParameter("phone"); 
        String raw_gender = request.getParameter("gender");
        String raw_cid = request.getParameter("cid"); 
        Date dob = null;
        int cid = -1; 
        if(raw_dob.length() > 0 && raw_dob != null){
           dob = Date.valueOf(raw_dob);
        }
        if(raw_cid.length() > 0 && raw_cid != null){
            cid =Integer.parseInt(raw_cid); 
        }
        boolean gender = raw_gender.equals("male")?true:false;
        Student student = new Student(); 
        student.setName(sname);
        student.setDob(dob);
        student.setEmail(email);
        student.setPhone(phone);
        Class c = new Class();
        c.setId(cid);
        student.setC(c);
        student.setGender(gender);
        student.setAccount(account);
        // Create Account và phân quyền luôn (insert vào 3 bảng, 1 là student và 1 account và account_group )
        accountDB.createAccountStudent(account, student);       
        response.sendRedirect("view-account");
    }


}
