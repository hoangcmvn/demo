/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.authorization;

import dal.AccountDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modal.Account;

/**
 *
 * @author pv
 */
public class loginController extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/view/auth/login.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String user = request.getParameter("username"); 
        String pass = request.getParameter("password"); 
        AccountDBContext accountDB = new AccountDBContext(); 
        Account account = accountDB.getAccount(user, pass); 
        if(account != null){
            HttpSession session = request.getSession();
            session.setAttribute("account", account);
            // check xem acc này là teacher hay student
            boolean isTeacher = accountDB.isTeacher(account);
            if(isTeacher){
                request.getSession().setAttribute("isTeacher", isTeacher);
                response.sendRedirect("admin/books");
            }else{
                request.getSession().setAttribute("isTeacher", null);
                response.sendRedirect("home");
            }
        }else{
           request.getSession().setAttribute("account", null);
           request.setAttribute("message_wrongpassword", "message_wrongpassword");
           request.getRequestDispatcher("/view/auth/login.jsp").forward(request, response);
        }
    }
}
