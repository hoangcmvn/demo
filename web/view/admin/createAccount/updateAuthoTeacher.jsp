<%-- 
    Document   : updateTeacher
    Created on : Mar 18, 2022, 2:49:36 AM
    Author     : pv
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
        <script src ="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet"/>
        <link href="../../css/admin/booksAdmin.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/admin/insertAdmin.css" rel="stylesheet" type="text/css"/>
        <script src="../../js/validation/validateForm.js" type="text/javascript"></script>
        <title>Thêm tài khoản học sinh</title>
    </head>
    <body>
        <!--navbar header-->
        <nav class="position-edit navbar color-brand navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Quản Lý Thư Viện</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto flex-column vertical-nav">
                    <li class="nav-item left-item">
                        <a class="nav-link" href="../../home"><i style="margin-right: 5px" class="fa fa-home"></i>Về Trang Chủ Thư Viện</a>
                    </li>
                    <li class="nav-item left-item">
                        <a class="nav-link" href="../books">Quản Lý Sách</a>
                    </li>
                    <li class="nav-item left-item">
                        <a class="nav-link" href="../category/insert">Danh Mục Sách</a>
                    </li>
                    </li>
                    <li class="nav-item left-item">
                        <a class="nav-link" href="../publisher/insert">Nhà Xuất Bản</a>
                    </li>
                    <li class="nav-item left-item">
                        <a class="nav-link" href="../language/insert">Ngôn Ngữ</a>
                    </li>
                    <li class="nav-item left-item">
                        <a class="nav-link" href="create">Tạo tài khoản giáo viên</a>
                    </li>
                    <li class="nav-item left-item">
                        <a class="nav-link" href="../../admin/student/create">Tạo tài khoản học sinh</a>
                    </li>
                    <li class="nav-item left-item">
                        <a class="nav-link" href="#">Quản lý mượn trả</a>
                    </li>
                    <li class="nav-item left-item">
                        <a class="nav-link" href="#">Quản lý thông báo</a>
                    </li>
                </ul>
                <ul class="navbar-nav ml-auto infomation-admin">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle admin-name" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ${sessionScope.account.fullname}
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#">Thông tin cá nhân</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="../../logout">Đăng xuất</a>
                        </div>
                    </li>
                </ul>

            </div>
        </nav>
        <!--Body-Content-->
        <div class = "main-body">
          <!-- this is header of book management -->
            <div class = "header-adminBook">
                <h2>Thêm Tài Khoản Giáo Viên</h2>
            </div>
          <div class="book-table">
              <table class="table table-hover">
                <form action = "view-update" method="POST">
                    <thead>
                        <tr class = "bg-warning">
                            <th>Account</th>
                            <c:forEach items="${requestScope.groups}" var="g">
                            <th>${g.name}</th>
                            </c:forEach>
                        </tr>    
                    </thead>
                    <tbody>
                            <c:forEach items="${requestScope.getAccountsEmployee}" var = "a">   
                            <tr>
                                <td>${a.fullname}  (${a.username})</td>
                                <c:forEach items="${requestScope.groups}" var="g">
                                    <td>
                                        <input type="checkbox" name="user_group" value="${a.username}_${g.id}"
                                               <c:forEach items = "${a.groups}" var="ag" >
                                                   <c:if test = "${ag.id == g.id}">checked="checked"</c:if>
                                               </c:forEach>      
                                               />
                                    </td>   
                                </c:forEach>
                            </tr>
                           </c:forEach>
                    </tbody>               
                </table>
                <div class = "teacher-save">
                  <button class="btn btn-danger" type="submit">Save</button>
                </div>  
              </form> 
            </div>          
        </div>
    </body>
</html>

