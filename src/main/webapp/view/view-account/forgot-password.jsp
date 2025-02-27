<%--
  Created by IntelliJ IDEA.
  User: hdanh
  Date: 24/02/2025
  Time: 8:49 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Quên Mật Khẩu</title>
</head>
<body>
<%@ include file="/view/view-index/header.jsp" %>
<section class="section-content padding-y">
    <!-- ============================ COMPONENT FORGOT PASSWORD ================================= -->
    <div class="card mx-auto" style="max-width:520px; margin-top:40px;">
        <article class="card-body">
            <header class="mb-4"><h4 class="card-title">Quên Mật Khẩu</h4></header>
            <form action="${pageContext.request.contextPath}/forgot-password" method="post">
                <div class="form-group">
                    <label>Email của bạn</label>
                    <input type="email" name="email" class="form-control" placeholder="Nhập email" required>
                </div> <!-- form-group end.// -->

                <div class="form-group">
                    <button type="submit" class="btn btn-primary btn-block">Gửi Yêu Cầu</button>
                </div> <!-- form-group// -->

                <%-- Hiện thông báo lỗi --%>
                <c:if test="${not empty error}">
                    <div class="error-message" style="color: red;">${error}</div>
                </c:if>

                <%-- Hiện thông báo thành công --%>
                <c:if test="${not empty success}">
                    <div class="success-message" style="color: green;">${success}</div>
                </c:if>

            </form>
        </article><!-- card-body.// -->
    </div> <!-- card .// -->
    <p class="text-center mt-4">Bạn nhớ mật khẩu? <a href="${pageContext.request.contextPath}/login">Đăng nhập</a></p>
    <br><br>
    <!-- ============================ COMPONENT FORGOT PASSWORD END ================================= -->
</section>

<%@ include file="/view/view-index/footer.jsp" %>
</body>
</html>
