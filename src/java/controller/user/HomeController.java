/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

import dal.BookDBContext;
import dal.BorrowDBContext;
import dal.CategoryDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Account;
import modal.Book;
import modal.Category;

/**
 *
 * @author pv
 */
public class HomeController extends HttpServlet {    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        CategoryDBContext categoryDB = new CategoryDBContext(); 
        ArrayList<Category> categories = categoryDB.getAllCategories();
        request.setAttribute("categories", categories);
         // test dữ liệu, phân trang sau
        BookDBContext bookDB = new BookDBContext();
        BorrowDBContext borrowDB = new BorrowDBContext(); 
        // số lượng tối đa là 10 lượt
        int max = 10; 
        int turnNumber = borrowDB.turnNumber(10);
        request.getSession().setAttribute("turnNumber", turnNumber);
        ArrayList<Book> top10books = bookDB.getTop10books();
        ArrayList<Book> top5pubyear = bookDB.getTop5publicYear();
        request.setAttribute("top10books",top10books);
        request.setAttribute("top5pubyear", top5pubyear);
        request.getRequestDispatcher("view/home.jsp").forward(request, response);
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
