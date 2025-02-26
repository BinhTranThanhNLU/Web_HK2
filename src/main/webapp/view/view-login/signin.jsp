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
</head>
<body>

<!-- ========================= SECTION CONTENT ========================= -->
<section class="section-conten padding-y" style="min-height:84vh">
    <!-- ============================ COMPONENT LOGIN   ================================= -->
    <div class="card mx-auto" style="max-width: 380px; margin-top:100px;">
        <div class="card-body">
            <h4 class="card-title mb-4">Đăng Nhập</h4>
            <form action="${pageContext.request.contextPath}/sign" method="post">
                <div class="form-group">
                    <input name ="email" type="email" class="form-control" placeholder="Địa Chỉ Email" >
                </div> <!-- form-group// -->
                <div class="form-group">
                    <input name ="password" type="password" class="form-control" placeholder="Mật Khẩu" >
                </div> <!-- form-group// -->

                <div class="form-group">
                    <a href="#" class="float-right">Quên Mật Khẩu?</a>

                </div> <!-- form-group form-check .// -->
                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block"> Đăng Nhập  </button>
                </div> <!-- form-group// -->
            </form>
        </div> <!-- card-body.// -->
    </div> <!-- card .// -->
    <p class="text-center mt-4">Không Có Tài Khoản ? <a href="#">Đăng Ký</a></p>
    <br><br>
    <!-- ============================ COMPONENT LOGIN  END.// ================================= -->
</section>
<%@ include file="/view/view-index/footer.jsp" %>
<!-- ========================= SECTION CONTENT END// ========================= -->
</body>
</html>
