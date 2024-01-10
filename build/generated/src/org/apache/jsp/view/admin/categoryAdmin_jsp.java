package org.apache.jsp.view.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class categoryAdmin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_forEach_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_var_items.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');
      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js\"></script>\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js\"></script>\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js\"></script>\n");
      out.write("        <link href=\"../css/admin/booksAdmin.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <script src=\"../js/pagination/pagger.js\" type=\"text/javascript\"></script>\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <!--navbar header-->\n");
      out.write("        <nav class=\"position-edit navbar color-brand navbar-expand-lg navbar-light bg-light\">\n");
      out.write("            <a class=\"navbar-brand\" href=\"#\">Quản Lý Thư Viện</a>\n");
      out.write("            <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n");
      out.write("                <span class=\"navbar-toggler-icon\"></span>\n");
      out.write("            </button>\n");
      out.write("\n");
      out.write("            <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n");
      out.write("                <ul class=\"navbar-nav mr-auto flex-column vertical-nav\">\n");
      out.write("                    <li class=\"nav-item left-item\">\n");
      out.write("                        <a class=\"nav-link\" href=\"../home\"><i style=\"margin-right: 5px\" class=\"fa fa-home\"></i>Về Trang Chủ User</a>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"nav-item left-item\">\n");
      out.write("                        <a class=\"nav-link\" href=\"books\"><i style=\"margin-right: 5px\" class=\"fa fa-book\"></i>Quản Lý Sách</a>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"nav-item left-item\">\n");
      out.write("                        <a class=\"nav-link\" href=\"#\">Danh Mục Sách</a>\n");
      out.write("                    </li>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"nav-item left-item\">\n");
      out.write("                        <a class=\"nav-link\" href=\"#\">Nhà Xuất Bản</a>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"nav-item left-item\">\n");
      out.write("                        <a class=\"nav-link\" href=\"#\">Lượt mượn ngày hôm nay</a>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"nav-item left-item\">\n");
      out.write("                        <a class=\"nav-link\" href=\"#\">Quản lý mượn trả</a>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"nav-item left-item\">\n");
      out.write("                        <a class=\"nav-link\" href=\"#\">Quản lý thông báo</a>\n");
      out.write("                    </li>\n");
      out.write("                </ul>\n");
      out.write("                <ul class=\"navbar-nav ml-auto infomation-admin\">\n");
      out.write("                    <li class=\"nav-item\">\n");
      out.write("                        <a class=\"nav-link admin-name\" href=\"#\">Foo</a>\n");
      out.write("                    </li>\n");
      out.write("                    <li class=\"nav-item dropdown\">\n");
      out.write("                        <a class=\"nav-link dropdown-toggle admin-name\" href=\"#\" id=\"navbarDropdown\" role=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\">\n");
      out.write("                            Admin\n");
      out.write("                        </a>\n");
      out.write("                        <div class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">\n");
      out.write("                            <a class=\"dropdown-item\" href=\"#\">Action</a>\n");
      out.write("                            <div class=\"dropdown-divider\"></div>\n");
      out.write("                            <a class=\"dropdown-item\" href=\"#\">Log Out</a>\n");
      out.write("                        </div>\n");
      out.write("                    </li>\n");
      out.write("                </ul>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("        <!--Body-Content-->\n");
      out.write("        <div class = \"main-body\">\n");
      out.write("          <!-- this is header of book management -->\n");
      out.write("            <div class = \"header-adminBook\">\n");
      out.write("                <h2>Quản Lý Sách</h2>\n");
      out.write("                <button class=\"btn btn-warning\" onclick = \"insertBook()\" type=\"submit\">+ Thêm Sách</button>\n");
      out.write("            </div>\n");
      out.write("          <!-- advanced search -->\n");
      out.write("            <form action = \"books\" method=\"GET\">\n");
      out.write("                <div class=\"advanced-search\">\n");
      out.write("                    <div class = \"advanced-search-item\">\n");
      out.write("                        <label for=\"author\" class=\"mr-sm-2\">Tên Tác Giả</label>\n");
      out.write("                        <input value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.author}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" type=\"text\" class=\"form-control\" name =\"author\" id=\"author\" placeholder=\"Nhập vào tên tác giả\" >\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div class = \"advanced-search-submit\">\n");
      out.write("                    <button class=\"btn btn-danger\" type=\"submit\">Tìm Kiếm</button>\n");
      out.write("                </div>\n");
      out.write("            </form>\n");
      out.write("            <!-- show book table - and delete update -->\n");
      out.write("            <div class=\"book-table\">\n");
      out.write("             <table class=\"table table-hover\">\n");
      out.write("                <thead>\n");
      out.write("                    <tr class = \"bg-warning\">\n");
      out.write("                        <th scope=\"col\">Mã</th>\n");
      out.write("                        <th scope=\"col\">Tên Sách</th>\n");
      out.write("                        <th scope=\"col\">Tác Giả</th>\n");
      out.write("                        <th scope=\"col\">Thể Loại</th>\n");
      out.write("                        <th scope=\"col\">Nhà Xuất Bản</th>\n");
      out.write("                        <th scope=\"col\">Năm</th>\n");
      out.write("                        <th scope=\"col\">Sửa</th>\n");
      out.write("                        <th scope=\"col\">Xóa</th>\n");
      out.write("                    </tr>\n");
      out.write("                </thead>\n");
      out.write("                <tbody>\n");
      out.write("                    ");
      if (_jspx_meth_c_forEach_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("                </tbody>\n");
      out.write("              </table>\n");
      out.write("            </div>\n");
      out.write("            <!-- This is pagination -->\n");
      out.write("            <div class = \"books-pagination\">\n");
      out.write("                <ul class=\"pagination\" id = \"paggerBottom\">\n");
      out.write("\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <script>\n");
      out.write("            // có sách mới phân trang \n");
      out.write("            if(");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.books.size() > 0}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("){\n");
      out.write("               pagger_Books(\"paggerBottom\",2,");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.totalPage}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(',');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.pageIndex}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(',');
      out.write('\'');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.url}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("'); \n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_forEach_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent(null);
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${requestScope.books}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_c_forEach_0.setVar("b");
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write(" \n");
          out.write("                    <tr>\n");
          out.write("                        <th scope=\"row\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${b.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</th>\n");
          out.write("                        <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${b.name}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</th>\n");
          out.write("                        <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${b.author}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</th>\n");
          out.write("                        <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${b.category.name}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</th>\n");
          out.write("                        <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${b.publisher.name}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</th>\n");
          out.write("                        <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${b.publicationYear}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</th>\n");
          out.write("                        <td><button type=\"button\" onclick=\"updateBook(");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${b.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write(")\" class=\"btn btn-info\">Sửa</button></th>\n");
          out.write("                        <td>\n");
          out.write("                           \n");
          out.write("                        </td>\n");
          out.write("                    </tr>\n");
          out.write("                    ");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }
}
