<%-- 
    Document   : login
    Created on : Mar 17, 2022, 2:28:55 AM
    Author     : pv
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet"/>
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.11.0/mdb.min.css"rel="stylesheet" />
        <title>JSP Page</title>
        <style>
            label.error{
            color : red !important;
            font-size: 14px;
            font-weight: 500;
         }
        </style>
    </head>
    <body>
        <form action = "login" method="POST">
        <section class="vh-100" style="background-color: #62AB00;">
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                        <div class="card shadow-2-strong" style="border-radius: 1rem;">
                            <div class="card-body p-5 text-center">

                                <h3 class="mb-5">Đăng nhập</h3>

                                <div class="form-outline mb-4">
                                    <input type="text" id="typeEmailX-2" name = "username" class="form-control form-control-lg" />
                                    <label class="form-label" for="typeEmailX-2">Tài khoản</label>
                                </div>

                                <div class="form-outline mb-4">
                                    <input type="password" name = "password" id="typePasswordX-2" class="form-control form-control-lg" />
                                    <label class="form-label" for="typePasswordX-2">Mật khẩu</label>
                                </div>
                                <c:if test="${requestScope.message_wrongpassword != null}">
                                        <label id="bname-error" class="error" for="bname">Tài Khoản Hoặc Mật Khẩu Không Đúng</label>
                                </c:if>
                                <p class="small mb-5 pb-lg-2"><a class="text-black-50" href="#!">Quên mật khẩu ?</a></p>
                                <button class="btn btn-primary btn-lg btn-block" type="submit">Login</button>
                                <hr class="my-4">
                                <div>
                                    <img src="images/logo_main.png" alt="" width="100px" height="100px"/>
                                    <span style="color: #62AB00; font-weight: 500; font-style: italic;">Thư viện xanh - Kho tàng sách</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        </form>    
        <script
            type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/3.11.0/mdb.min.js"
        ></script>
    </body>
</html>

