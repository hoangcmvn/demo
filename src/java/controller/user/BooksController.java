/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

import dal.BookDBContext;
import dal.BorrowDBContext;
import dal.CategoryDBContext;
import dal.PublisherDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Book;
import modal.Category;
import modal.Publisher;

/**
 *
 * @author pv
 */
public class BooksController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        CategoryDBContext categoryDB = new CategoryDBContext();
        BookDBContext bookDB = new BookDBContext();
        PublisherDBContext publisherDB = new PublisherDBContext(); 
        ArrayList<Publisher> publishers = publisherDB.getPublisher();
        ArrayList<Category> categories = categoryDB.getAllCategories();
        ArrayList<Integer> publicationYears = bookDB.getPublicationYear();
        // phân trang
        int pageSize = 10; 
        String page = request.getParameter("page");
        if(page == null || page.trim().length() == 0){
            page = "1"; 
        }
        int pageIndex = Integer.parseInt(page);
        if(pageIndex < 1) pageIndex = 1;
        // Searching : 
        //cid=&pid=&from=&to=&bname=&aname=
        String raw_cid = request.getParameter("cid"); 
        String raw_pid = request.getParameter("pid"); 
        String raw_from = request.getParameter("from");
        String raw_to = request.getParameter("to");
        String raw_bname = request.getParameter("bname"); 
        String raw_author = request.getParameter("author"); 
        
        if(raw_cid == null || raw_cid.length() == 0){
           raw_cid = "-1"; 
        }
        if(raw_pid == null || raw_pid.length() == 0){
           raw_pid = "-1"; 
        }
        if(raw_from == null || raw_from.length() == 0){
           raw_from = "-1"; 
        }
        if(raw_to == null || raw_to.length() == 0){
           raw_to = "-1"; 
        }
        if(raw_bname == null || raw_bname.length() == 0){
            raw_bname = null;
        }
        if(raw_author == null || raw_author.length() == 0){
            raw_author = null;
        } 
        int cid = Integer.parseInt(raw_cid); 
        int pid = Integer.parseInt(raw_pid); 
        int from = Integer.parseInt(raw_from); 
        int to = Integer.parseInt(raw_to);
        String bname = raw_bname; 
        String author = raw_author; 

        // Lấy ra book từ những dữ liệu trên 
        ArrayList<Book> books = bookDB.advancedSearch(cid, pid, from, to, bname, author, pageIndex, pageSize);
        int count = bookDB.countAdvanceSearch(cid, pid, from, to, bname, author);
        int totalPage = (count % pageSize == 0) ? (count / pageSize) : (count / pageSize) + 1;
        request.setAttribute("books",books);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("categories", categories);
        request.setAttribute("publishers", publishers);
        request.setAttribute("publicationYears", publicationYears);
        // sau khi đã search thì vẫn select những dữ liệu mà mình đã chọn search 
        request.setAttribute("cid", cid);
        request.setAttribute("pid", pid);
        request.setAttribute("from", from);
        request.setAttribute("to", to);
        request.setAttribute("bname", bname);
        request.setAttribute("author", author);
        // lấy Url để phân trang bằng js 
        String url = "books?";
        String url_param = request.getQueryString();
        if(url_param != null && url_param.length() > 0){
            if(url_param.endsWith("page=" + pageIndex)){
               url_param = url_param.replaceAll("page=" +pageIndex, "");      
            }
            // nếu nó không rời vào trường hợp book?page=x và thiếu & thì thêm vào
            if(!url_param.equals("") && !url_param.endsWith("&")){
                url_param += "&"; 
            }
            url += (url_param); 
        }
        request.setAttribute("url", url);
        BorrowDBContext borrowDB = new BorrowDBContext();
        int max = 10; 
        int turnNumber = borrowDB.turnNumber(10);
        request.getSession().setAttribute("turnNumber", turnNumber);
        request.getRequestDispatcher("view/books.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
