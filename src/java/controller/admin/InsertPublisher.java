/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import controller.authorization.BaseAuthController;
import dal.PublisherDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Publisher;

/**
 *
 * @author pv
 */
public class InsertPublisher extends BaseAuthController {


    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        int pageSize = 10; 
        String page = request.getParameter("page");
        if(page == null || page.trim().length() == 0){
            page = "1"; 
        }
        int pageIndex = Integer.parseInt(page);
        PublisherDBContext publisherDB = new PublisherDBContext(); 
        ArrayList<Publisher> publishers = publisherDB.getPublisher(pageIndex, pageSize);
        int count = publisherDB.count();
        int totalPage = (count % pageSize == 0) ? (count / pageSize) : (count / pageSize) + 1;
        String url = "insert?";
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("publishers", publishers);
        request.setAttribute("url",url);
        request.getRequestDispatcher("../../view/admin/insertPublisher.jsp").forward(request, response);        
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        PublisherDBContext publisherDB = new PublisherDBContext();
        String publisherName = request.getParameter("publisherName");
        // kiểm tra tên danh mục sách vừa nhập đã tồn tại trong dbi chưa
        boolean checkExistCname = publisherDB.checkExistPname(publisherName.trim());
        // nếu không thì tiếp tục vào InsertCategory để insert vào dbi
        if(publisherName != null || publisherName.trim().length() > 0){
            // nếu có thì chuyển lại trang insert và thông báo message là đã có
            if (checkExistCname == true) {
                ArrayList<Publisher> publishers = publisherDB.getPublisher(1, 10);
                int count = publisherDB.count();
                int totalPage = (count % 10 == 0) ? (count / 10) : (count / 10) + 1;
                String url = "insert?";
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("pageIndex", 1);
                request.setAttribute("publishers", publishers);
                request.setAttribute("url",url);
                request.setAttribute("message_ExistPublisherName", "Nhà Xuất Bản Này Đã Tồn Tại");
                request.getRequestDispatcher("../../view/admin/insertPublisher.jsp").forward(request, response);
            }else{
                Publisher p = new Publisher();
                p.setName(publisherName);
                publisherDB.insert(p);
                response.sendRedirect("insert");
            }
        }
    }
}
