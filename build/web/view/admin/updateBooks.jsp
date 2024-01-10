<%-- 
    Document   : updateBooks
    Created on : Mar 16, 2022, 12:28:01 AM
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
        <script src="../../js/pagination/pagger.js" type="text/javascript"></script>
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
                        <a class="nav-link" href="../category/insert">Nhà Xuất Bản</a>
                    </li>
                    <li class="nav-item left-item">
                        <a class="nav-link" href="../category/insert">Ngôn Ngữ</a>
                    </li>
                    <li class="nav-item left-item">
                        <a class="nav-link" href="../../admin/teacher/create">Tạo tài khoản giáo viên</a>
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
                <h2>Chỉnh Sửa Sách</h2>
            </div>
            <form id ="validate-form-update" action = "update" method="POST" enctype="multipart/form-data">
                <div class = "insert-book">
                    <div class = "insert-book-item form-group">
                        <label for="bookname" class="mr-sm-2">Tên Sách</label>
                        <!--Gửi id lên nhưng hidden-->
                        <input name = "bid" type="text" value="${requestScope.book.id}" hidden="hidden">
                        <input value = "${requestScope.book.name}" type="text" class="form-control" name ="bname" placeholder="Nhập vào tên sách" >
                    </div>
                    <div class = "insert-book-item form-group">
                        <label for="author" class="mr-sm-2">Tên Tác Giả</label>
                        <input value = "${requestScope.book.author}" type="text" class="form-control" name ="author" id="author" placeholder="Nhập vào tên tác giả" >
                    </div>
                    <div class = "insert-book-item form-group">
                        <label for="category">Thể Loại</label>
                        <select class="form-control" id="category" name = "category_id">
                            <c:forEach items = "${requestScope.categories}" var = "c">
                                <option ${requestScope.book.category.id == c.id?"selected = selected":""} value = "${c.id}">${c.name}</option>
                            </c:forEach>                                        
                        </select>
                    </div>
                    <div class = "insert-book-item form-group">
                        <label for="publisher">Nhà Xuất Bản</label>
                        <select class="form-control" id="publisher" name = "publisher_id">
                            <c:forEach items = "${requestScope.publishers}" var = "p">
                                <option ${requestScope.book.publisher.id == p.id?"selected = selected":""} value = "${p.id}">${p.name}</option>
                            </c:forEach>                                        
                        </select>
                    </div>
                    <div class = "insert-book-item form-group">
                        <label for="language">Ngôn Ngữ</label>
                        <select class="form-control" id="language" name = "language_id">
                            <c:forEach items="${requestScope.languages}" var = "l">
                                <option ${requestScope.book.language.id == l.id?"selected = selected":""} value="${l.id}">${l.name}</option>
                            </c:forEach>                                        
                        </select>
                    </div>
                    <div class = "insert-book-item form-group">
                        <label for="publicyear" class="mr-sm-2">Năm Xuất Bản</label>
                        <input value = "${requestScope.book.publicationYear}" type="text" class="form-control" name ="publication_year"  id="publicyear" placeholder="Nhập vào năm xuất bản" >
                    </div>
                    <div class = "insert-book-item form-group">
                        <label for="numberpage" class="mr-sm-2">Số Trang</label>
                        <input value = "${requestScope.book.numberPages}" type="text" class="form-control" name ="numberpage"  id="numberpage" placeholder="Nhập vào số trang" >
                    </div>
                    <div class = "insert-book-item form-group">
                        <label for="location" class="mr-sm-2">Vị Trị Đặt Sách Ở Thư Viện</label>
                        <input value = "${requestScope.book.location}" type="text" class="form-control" name ="location" id="location" placeholder="Nhập vào vị trị" >
                    </div>
                    <div class="insert-book-item form-group">
                        <label for="exampleFormControlTextarea1">Mô Tả</label>
                        <textarea class="form-control" id="exampleFormControlTextarea1" name = "descrip">${requestScope.book.description}</textarea>
                    </div>
                    <form>
                    <div class="form-group insert-book-item">
                      <label for="exampleFormControlFile1">Ảnh</label>
                      <input value="${requestScope.book.img}" type="text" hidden="hidden" name = "old_img"/>
                      <input type="file" onchange="getImgPreview(event)" class="form-control-file" id="exampleFormControlFile1" name = "img">
                    </div>
                    <div class = "preview-image" id = "preview">
                       <img src="../../images/books/${requestScope.book.img}" />
                    </div>
                </div>
                <div class = "insert-book-submit">
                    <button id = "btn-Save" class="btn btn-success" type="submit">Save</button>
                </div>
            </form>
        </div>
        <script>
           validateUpdateBook();
           function getImgPreview(event){
               var image = URL.createObjectURL(event.target.files[0]);
               var imagediv = document.getElementById("preview"); 
               var newimg = document.createElement("img"); 
               imagediv.innerHTML=''; 
               newimg.src = image; 
               imagediv.appendChild(newimg);
           }
        </script>
    </body>
</html>

