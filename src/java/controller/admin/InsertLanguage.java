/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import controller.authorization.BaseAuthController;
import dal.LanguageDBContext;
import dal.PublisherDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Language;
import modal.Publisher;

/**
 *
 * @author pv
 */
public class InsertLanguage extends BaseAuthController {

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
        LanguageDBContext languageDB = new LanguageDBContext();
        ArrayList<Language> languages = languageDB.getLanguages(pageIndex, pageSize);         
        int count = languageDB.count();
        int totalPage = (count % pageSize == 0) ? (count / pageSize) : (count / pageSize) + 1;
        String url = "insert?";
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("languages", languages);
        request.setAttribute("url",url);
        request.getRequestDispatcher("../../view/admin/insertLanguage.jsp").forward(request, response);
    }


    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        LanguageDBContext languageDB = new LanguageDBContext(); 
        String languageName = request.getParameter("languageName");
        // kiểm tra tên danh mục sách vừa nhập đã tồn tại trong dbi chưa
        boolean checkExistLname = languageDB.checkExistLname(languageName.trim());
        // nếu không thì tiếp tục vào InsertCategory để insert vào dbi
        if(languageName != null || languageName.trim().length() > 0){
            // nếu có thì chuyển lại trang insert và thông báo message là đã có
            if (checkExistLname == true) {
                ArrayList<Language> languages = languageDB.getLanguages(1,10);
                int count = languageDB.count();
                int totalPage = (count % 10 == 0) ? (count / 10) : (count / 10) + 1;
                String url = "insert?";
                request.setAttribute("totalPage", totalPage);
                request.setAttribute("pageIndex", 1);
                request.setAttribute("url",url);
                request.setAttribute("languages",languages);
                request.setAttribute("message_ExistLanguageName", "Tên ngôn ngữ này đã tồn tại");
                request.getRequestDispatcher("../../view/admin/insertLanguage.jsp").forward(request, response);
            }else{
                Language l = new Language(); 
                l.setName(languageName);
                languageDB.insert(l);
                response.sendRedirect("insert");
            }
        }
    }
}
