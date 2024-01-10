/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

import dal.AccountDBContext;
import dal.BorrowDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Account;
import modal.Student;

/**
 *
 * @author pv
 */
public class waitingBorrowController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet waitingBorrowController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet waitingBorrowController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String raw_bid = request.getParameter("bid"); 
        int bid = Integer.parseInt(raw_bid);
        Account account = (Account)request.getSession().getAttribute("account");
        AccountDBContext accountDB = new AccountDBContext();
        BorrowDBContext borrowDB = new BorrowDBContext(); 
        // Điều kiện 1 : Account phải là student mới có quyền mượn
        Student student = accountDB.getStudent(account);
        if(student != null){
           // Điều kiện 2 : Còn có số lượt mượn trong ngày <= 50 lượt
           int numberBorrowed = borrowDB.checkTurnBoroww(student); 
           if(numberBorrowed < 5){
               // Điều kiện 3 : 1 ngày tối đa mượn được 4 quyển
               int numberBook = borrowDB.checkNumberBook(student);
               if(numberBook < 4){
                   // Điều kiện 4 : sách mượn tiếp theo không được trùng trong waiting borrow
                   if(!borrowDB.bookExist(student, bid)){
                        // Thêm vào bảng borrow waiting 
                        borrowDB.add(student, bid);
                        request.setAttribute("message_borrow", "Đã mượn thành công - bạn mang thẻ sinh viên lên thư viện để lấy sách");
                   }else{
                       // quyển sách vừa mượn bạn đã được mượn
                       request.setAttribute("message_borrow", "Bạn đã mượn quyển này");
                   }
               }else{
                   // thông báo về đã hết số lược mượn sách trong ngày của account
                    request.setAttribute("message_borrow", "Một ngày bạn chỉ được mượn tối đa 4 quyển");
               }
           }else{
               // thông báo về không đủ số lượt mượn
               request.setAttribute("message_borrow", "Số lượt mượn sách trong ngày đã hết");
           }
        }else{
             request.setAttribute("message_borrow", "Bạn không phải là học sinh");
        }
        request.getRequestDispatcher("books/detail").forward(request, response);
        
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
