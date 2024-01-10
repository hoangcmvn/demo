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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Account;
import modal.Group;

/**
 *
 * @author pv
 */
public class UpdateAuthoTeacherAccount extends BaseAuthController {

    
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDBContext accountDB = new AccountDBContext();
        GroupDBContext groupDB = new GroupDBContext(); 
        ArrayList<Account> getAccountsEmployee = accountDB.getAccountsEmployee();
        ArrayList<Group> groups = groupDB.getGroups();
        request.setAttribute("groups", groups);
        request.setAttribute("getAccountsEmployee", getAccountsEmployee);
        request.getRequestDispatcher("../../view/admin/createAccount/updateAuthoTeacher.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDBContext accountDB = new AccountDBContext(); 
        ArrayList<Account> allUsername = accountDB.getAllUsernameEmployee(); 
        String account_group[] =  request.getParameterValues("user_group"); 
        if(account_group != null){
            for (Account a : allUsername) {
                for (String ag : account_group) {
                    String[] temp = ag.split("_");
                    String username_temp = temp[0];
                    int gid_temp = Integer.parseInt(temp[1]);
                    if(username_temp.equalsIgnoreCase(a.getUsername())){
                         Group g = new Group(); 
                         g.setId(gid_temp);
                         a.getGroups().add(g);
                    }
                }
            }
        }
        // Những username trên đã được cập nhật lại gid mà mới click vừa gửi
        // xóa tất cả bản ghi cũ trong database để insert lại mới
        // rồi Insert lại 
        for (Account a : allUsername) {
             accountDB.updateAccount_Group(a);
        }
        response.sendRedirect("view-update");
    }
}
