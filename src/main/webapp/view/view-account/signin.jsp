<%--
  Created by IntelliJ IDEA.
  User: hdanh
  Date: 24/02/2025
  Time: 8:50 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/view/view-index/header.jsp" %>
<html>
<head>
    <title>Đăng Nhập</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<body>

<!-- ========================= SECTION CONTENT ========================= -->
<section class="section-conten padding-y" style="min-height:84vh">
    <!-- ============================ COMPONENT LOGIN   ================================= -->
    <div class="card mx-auto" style="max-width: 380px; margin-top:100px;">
        <div class="card-body">
            <h4 class="card-title mb-4 text-center">Đăng Nhập</h4>
            <form action="${pageContext.request.contextPath}/sign" method="post">
                <div class="form-group">
                    <input name="username" type="text" class="form-control" placeholder="nhập vào tài khoản" required>
                </div>
                <div class="form-group">
                    <input name="password" type="password" class="form-control" placeholder="Mật Khẩu" required>
                </div>
                <div class="form-group">
                    <a href="#" class="float-right">Quên Mật Khẩu?</a>
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block"> Đăng Nhập </button>
                </div>
            </form>

            <!-- Nút đăng nhập bằng Google -->
            <div class="form-group">
                <a href="https://accounts.google.com/o/oauth2/auth?client_id=YOUR_GOOGLE_CLIENT_ID&redirect_uri=YOUR_REDIRECT_URI&response_type=code&scope=email%20profile"
                   class="btn btn-danger btn-block">
                    <i class="fa-brands fa-google"></i> Đăng nhập với Google
                </a>
            </div>

            <!-- Nút đăng nhập bằng Facebook -->
            <div class="form-group">
                <a href="https://www.facebook.com/v17.0/dialog/oauth?client_id=YOUR_FACEBOOK_APP_ID&redirect_uri=YOUR_REDIRECT_URI&response_type=code&scope=email,public_profile"
                   class="btn btn-primary btn-block">
                    <i class="fa-brands fa-facebook"></i> Đăng nhập với Facebook
                </a>
            </div>
        </div>
    </div>

    <p class="text-center mt-4">Không Có Tài Khoản ? <a href="<%= request.getContextPath() %>/view/view-account/register.jsp">Đăng Ký</a></p>
    <br><br>
</section>
<%@ include file="/view/view-index/footer.jsp" %>
</body>
</html>
