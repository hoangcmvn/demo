/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import controller.authorization.BaseAuthController;
import dal.AccountDBContext;
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
import modal.Employee;
import modal.Group;

/**
 *
 * @author pv
 */
public class CreateAccountTeacher extends BaseAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GroupDBContext groupDB = new GroupDBContext(); 
        ArrayList<Group> groups = groupDB.getGroups();
        request.setAttribute("groups", groups);
        request.getRequestDispatcher("../../view/admin/createAccount/teacher.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        AccountDBContext accountDB = new AccountDBContext();
        // Group 
        String groups[] = request.getParameterValues("gid");         
        String username = request.getParameter("username");
        String password = request.getParameter("password"); 
        String fullname = request.getParameter("fullname"); 
        if(accountDB.checkAccountExist(username)){
            GroupDBContext groupDB = new GroupDBContext(); 
            ArrayList<Group> allgr = groupDB.getGroups();
            request.setAttribute("groups", allgr);
            request.setAttribute("message_existAccount", "Tài khoản này đã tồn tại, bạn hãy chọn tên khác");
            request.getRequestDispatcher("../../view/admin/createAccount/teacher.jsp").forward(request, response);
            return;
        }
        // Account
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        account.setFullname(fullname);
         for (String group : groups) {
            Group g = new Group(); 
            g.setId(Integer.parseInt(group));
            account.getGroups().add(g);
        }
        // Teacher
        String ename = request.getParameter("ename"); 
        String raw_dob = request.getParameter("dob"); 
        String email = request.getParameter("email"); 
        String raw_gender = request.getParameter("gender");
        Date dob = null;
        if(raw_dob.length() > 0 && raw_dob != null){
           dob = Date.valueOf(raw_dob);
        }
        boolean gender = raw_gender.equals("male")?true:false;
        Employee e = new Employee(); 
        e.setEname(ename);
        e.setEmail(email);
        e.setGender(gender);
        e.setDob(dob);
        e.setAccount(account);
        // Create Account và phân quyền luôn (insert vào 3 bảng, 1 là employee và 1 account và account_group )
        accountDB.createAccountTeacher(account, e);
        response.sendRedirect("view-update");
    }
}
