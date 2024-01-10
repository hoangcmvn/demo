<%-- 
    Document   : teacher
    Created on : Mar 17, 2022, 10:16:06 PM
    Author     : pv
--%>
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
        <title>JSP Page</title>
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
                <button class="btn btn-warning" onclick = "viewTeacher()" type="submit">Xem - Sửa Quyền Các Tài Khoản</button>
            </div>
            <form id ="validate-form-create-account" action = "create" method="POST">
                <div class = "insert-book">
                    <div class = "insert-book-item form-group">
                        <label for="ename" class="mr-sm-2">Họ Và Tên</label>
                        <input type="text" id = "ename" class="form-control" name ="ename" placeholder="Nhập vào họ tên" >
                    </div>
                    <div class = "insert-book-item form-group">
                        <label for="dob" class="mr-sm-2">Ngày Sinh</label>
                        <input type="date" id = "dob" class="form-control" name ="dob" >
                    </div>
                    <div class = "insert-book-item form-group">
                        <label for="email" class="mr-sm-2">Email Cá Nhân</label>
                        <input type="text" id = "email" class="form-control" name ="email" placeholder="Nhập vào email">
                    </div>            
                    <div class = "insert-book-item form-group">
                        <label class="mr-sm-2">Giới tính</label>
                        <div style="display: flex;">
                            <input type="radio" id="male" name = "gender" value ="male"/><p style="margin-right: 10px;" for = "male">Nam</p>
                            <input type="radio" id="female" name = "gender" value ="female"/><p for = "female" >Nữ</p>
                        </div>
                    </div>
                    <div class = "info-account">                       
                         <div class = "form-group">
                            <label for="username" class="mr-sm-2">Tài Khoản</label>
                            <i class="fas fa-user fa-lg me-3 fa-fw"></i> 
                            <input type="text" id = "username" class="form-control" name ="username" placeholder="Nhập tài khoản đăng kí">
                            <c:if test="${requestScope.message_existAccount != null}">
                                   <label id="ename-error" class="error" for="ename">${requestScope.message_existAccount}</label>
                            </c:if>                             
                         </div>
                        <div class = "form-group">
                            <label for="fullname" class="mr-sm-2">Tên hiện thị ở tài khoản</label>
                             <i class="fa-solid fa-file-signature"></i>
                            <input type="text" id = "fullname" class="form-control" name ="fullname" placeholder="Nhập tên">
                         </div>                        
                         <div class = "form-group">
                            <label for="password" class="mr-sm-2">Mật Khẩu</label>
                            <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
                            <input type="password" id = "password" class="form-control" name ="password" placeholder="Nhập mật khẩu">
                         </div>  
                        <div class = "form-group">
                            <label for="confirm_password" class="mr-sm-2">Xác Nhận Lại Mật Khẩu</label>
                            <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                            <input type="password" id = "confirm_password" class="form-control" name ="confirm_password" placeholder="Nhập lại mật khẩu trên">
                         </div>
                         <div class = "form-group">
                            <label class="mr-sm-2">Chọn quyền cho người này</label>
                            <i class="fa-solid fa-users-gear"></i>
                            <c:forEach items="${requestScope.groups}" var = "g">
                                <div>
                                    <div class="form-check form-check-inline">
                                    <input name = "gid" class="form-check-input" type="checkbox" id="${g.name}" value="${g.id}">
                                    <label class="form-check-label text-warning" for="${g.name}">${g.name}</label>
                                    </div> 
                                </div>
                            </c:forEach>
                         </div>
                    </div>                        
                    <form>                
                </div>               
                <div class = "insert-book-submit">
                    <button id = "btn-Save" class="btn btn-success" type="submit">Tạo tài khoản</button>
                </div>
            </form>
                
        </div>
        <script>
           // validate 
           $('#validate-form-create-account').validate({
           rules:{
               ename: {
                   required:true
               },
               dob: {
                   required:true
               },
               email: {
                   required:true,
                   email: true                  
               },
               gender: {
                   required:true,
               },
               username:  {
                   required:true
               },
               fullname: {
                   required:true
               },
               password: {
                   required:true
               },
               confirm_password:{
                   equalTo: "#password"
               },
               group:{
                   required:true
               }
           },
           messages:{
                ename:{
                   required:"Tên giáo viên không được bỏ trống"
                },
                dob: {
                   required:"Ngày tháng không được bỏ trống"
                },
                email: {
                   required:"Email không được bỏ trống",
                   email: "Nhập đúng định dạng email"
               },
               gender: {
                   required:"Giới tính không được bỏ trống",
               },
               fullname:  {
                   required:"Tên hiện thị tài khoản không được bỏ trống"
               },
               password: {
                   required:"Mật khẩu không được bỏ trống"
               },
               confirm_password: {
                   equalTo:"Mật khẩu không khớp với mật khẩu bạn vừa nhập"
               },
               group:{
                   required:"Phải chọn quyền cho tài khoản"
               },
               username:{
                   required:"Tên đăng nhập không được bỏ trống"  
               }
            }
        });
        function viewTeacher(){
            window.location.href = "view-update"; 
        }
        </script>
    </body>
</html>

