<%-- 
    Document   : home
    Created on : Feb 27, 2022, 2:42:29 PM
    Author     : pv
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modal.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
        <link href="css/stylehome.css" rel="stylesheet" type="text/css"/>      
        <title>Thư viện</title>
        <c:set var = "now" value = "<%= new java.util.Date()%>" />
    </head>
    <body>
    <!--this is header-->
        <header>
            <div class = "container-fluid">
                <div class = "row">
                    <div class ="col-md-7">
                        <img src="images/logo_main.png" alt="Trường THPT Nghi lộc 4" width = "100px"/>
                        <span class = "name-banner">THƯ VIỆN XANH - KHO TÀNG SÁCH</span>
                    </div>
                    <div class ="col-md-5">
                        <ul class="nav">
                            <c:if test="${sessionScope.account != null}">
                                <c:if test="${sessionScope.isTeacher != null && sessionScope.isTeacher == true}">
                                   <li class="nav-item"> 
                                       <a class="nav-link" href="admin/books">Vào Trang Quản Lý</a>
                                   </li>        
                                </c:if>
                                <c:if test="${sessionScope.isTeacher == null || sessionScope.isTeacher != true}">
                                   <li class="nav-item"> 
                                    <a class="nav-link" href="#">Thông tin mượn sách <img src="images/icons8-book.png" style="margin-bottom: 6px;" width="20px" height="20px" alt=""/></a>
                                   </li>       
                                </c:if>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">${sessionScope.account.fullname}<img src="images/icons8-user.png" style="margin-bottom: 8px;" width="20px" height="20px" alt=""/></a> 
                                </li>   
                                <li class="nav-item">
                                    <a class="nav-link" href="logout">Đăng xuất<img src="images/icons8-logout-16.png" width="20px" style="margin-bottom: 6px;" height="20px" alt=""/></a>
                                </li>
                            </c:if>
                        </ul>
                        <c:if test="${sessionScope.account == null}">
                            <div class = "buttom-login" style="display: flex; justify-content: flex-end">
                                <img src="images/user.png" width="20px" height="20px" style="margin-top: 10px; margin-right: 5px" alt=""/>
                                <a class=" btn btn-primary"  href="login">Đăng nhập</a>
                            </div>
                        </c:if>
                    </div>  
                </div> 
                <div class = "row header-second">
                    <div class = "col-md-12">
                        <nav class="navbar navbar-expand-lg navbar-light">
                            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
                                <span class="navbar-toggler-icon"></span>
                            </button>
                            <div class="collapse navbar-collapse" id="collapsibleNavbar">
                                <ul class="navbar-nav mr-auto">
                                    <li class="nav-item color-menu">
                                        <a class="nav-link" href="home">Trang chủ</a>
                                    </li>
                                    <li class="nav-item color-menu">
                                        <a class="nav-link" href="#">Thông báo</a>
                                    </li>
                                    <li class="nav-item color-menu">
                                        <a class="nav-link" href="books">Kho sách</a>
                                    </li>
                                    <li class="nav-item color-menu">
                                        <a class="nav-link" href="#">Góc thảo luận</a>
                                    </li>
                                    <li class="nav-item color-menu">
                                        <a class="nav-link" href="#">Hướng dẫn mượn sách</a>
                                    </li>      
                                </ul>
                                <div class="dropdown mr-auto">
                                    <button type="button" class="my-size-category btn btn-success btn-block dropdown-toggle" data-toggle="dropdown">
                                        Danh Mục Sách
                                    </button>
                                    <div class="dropdown-menu btn-block">
                                        <c:forEach items="${requestScope.categories}" var ="c">
                                          <a class="dropdown-item" href="books?cid=${c.id}">${c.name}</a>      
                                        </c:forEach>
                                    </div>
                                </div>            
                                <form class="form-inline" action = "books" method="GET">
                                    <input class="form-control mr-sm-2" type="search" name ="bname" placeholder="Sách cần tìm...">
                                    <button class="btn btn-warning" type="submit">Tìm Kiếm</button>
                                </form>          
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
        </header>
    <!--notification-->
    <marquee width="60%" direction="left" height="50px" scrollamount="4">
        <span>Trường THPT Nghi Lộc 4 xin thông báo ngày 
                       <fmt:formatDate type = "date" 
                        value = "${now}" /> 
              còn ${sessionScope.turnNumber} lượt mượn sách
        </span>
        <span class = "notice-covid"> -  HỌC SINH PHẢI CHẤP HÀNH THỰC HIỆN QUY ĐỊNH 5K ĐẢM BẢO AN TOÀN PHÒNG CHỐNG DỊCH COVID-19 KHI QUAY TRỞ LẠI TRƯỜNG VÀ MƯỢN SÁCH</span>
    </marquee>
    <!--this is body -->
    <div class = "main-body">
        <div class = "show-book">
            <div class = "header-main">
                <!--Show new books-->
                <h2 class = "sec-title">
                    <p style="vertical-align: inherit">những quyển sách mới nhất</p>
                </h2>
                <div class = "books-item">                   
                    <c:forEach items="${requestScope.top10books}" var = "b">
                       <a href ="books/detail?bid=${b.id}" class = "book-item">
                        <figure>
                           <img src="images/books/${b.img}" alt="${b.name}"/>
                        </figure>                           
                        <div class = "book-info">
                            <p class = "book-title">${b.name}</p>
                            <h3 class = "book-category">${b.category.name}</h3>
                            <p class = "book-author">${b.author}</p>
                        </div>
                    </a>
                   </c:forEach>
                </div>
                <!--Show top 5 books-->
                <h2 class = "sec-title">
                    <p style="vertical-align: inherit">Gợi Ý Cho Bạn</p>
                </h2>
                <div class = "books-item">                   
                    <c:forEach items="${requestScope.top5pubyear}" var = "b">
                       <a href ="books/detail?bid=${b.id}" class = "book-item">
                        <figure>
                           <img src="images/books/${b.img}" alt="${b.name}"/>
                        </figure>                           
                        <div class = "book-info">
                            <p class = "book-title">${b.name}</p>
                            <h3 class = "book-category">${b.category.name}</h3>
                            <p class = "book-author">${b.author}</p>
                        </div>
                    </a>
                   </c:forEach>
                </div>
            </div>
        </div>
    </div>
        <!-- Footer -->
        <footer class="main-footer">
            <div class = "text-center">
                <p><strong>Thư viện Trường THPT Nghi Lộc 4</strong></p>
                <p>Địa chỉ : Xóm 2, Nghi Xá, Nghi Lộc, Nghệ An</p>
                <p>Điện Thoại : 02383 861 079</p>
                <p>Email :
                    <a href="mailto:thuviennghiloc4@nghean.edu.vn">thuviennghiloc4@nghean.edu.vn</a>
                </p>
            </div>
            <div class="footer-copyright text-center py-3">© 2022 Copyright:
                <a href="#"> thuviennghiloc4.edu.vn</a>
            </div>
        </footer>
    </body>
</html>
