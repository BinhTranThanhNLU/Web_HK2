<%@ page import="java.util.List, java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<title>Trang Chủ</title>
<%--<%--%>
<%--    // Lấy danh sách ảnh từ request--%>
<%--    Object imageObj = request.getAttribute("images");--%>

<%--    // Kiểm tra nếu `images` chưa được set hoặc không phải List thì tạo danh sách trống--%>
<%--    List<String> images;--%>
<%--    if (imageObj instanceof List) {--%>
<%--        images = (List<String>) imageObj;--%>
<%--    } else {--%>
<%--        images = new ArrayList<>();--%>
<%--    }--%>

<%--    // Nếu không có ảnh, hiển thị thông báo--%>
<%--    if (images.isEmpty()) {--%>
<%--%>--%>
<%--<p>Không có sản phẩm nào để hiển thị.</p>--%>
<%--<%--%>
<%--} else {--%>
<%--    for (String imageUrl : images) {--%>
<%--%>--%>
<%--<div class="col-md-3">--%>
<%--    <div class="card card-product-grid">--%>
<%--        <a href="./product-detail.jsp" class="img-wrap">--%>
<%--            <img src="<%= request.getContextPath() + imageUrl %>" alt="Product">--%>
<%--        </a>--%>
<%--        <figcaption class="info-wrap">--%>
<%--            <a href="./product-detail.jsp" class="title">Tên Sản Phẩm</a>--%>
<%--            <div class="price mt-1">$179.00</div>--%>
<%--        </figcaption>--%>
<%--    </div>--%>
<%--</div>--%>
<%--<%--%>
<%--        }--%>
<%--    }--%>
<%--%>--%>




<%--<c:choose>--%>
<%--    <c:when test="${empty products}">--%>
<%--        <p>Không có sản phẩm nào để hiển thị.</p>--%>
<%--    </c:when>--%>
<%--    <c:otherwise>--%>
<%--        <c:forEach var="product" items="${products}">--%>
<%--            <div class="col-md-3">--%>
<%--                <div class="card card-product-grid">--%>
<%--                    <c:set var="imageUrl" value="default-image.jpg" /> <!-- Ảnh mặc định -->--%>

<%--                    <c:if test="${not empty product.productImages}">--%>
<%--                        <c:forEach var="image" items="${product.productImages}" varStatus="loop">--%>
<%--                            <c:if test="${loop.index == 0}">--%>
<%--                                <c:set var="imageUrl" value="${image.imageUrl}" />--%>
<%--                            </c:if>--%>
<%--                        </c:forEach>--%>
<%--                    </c:if>--%>

<%--                    <a href="./product-detail.jsp?id=${product.idProduct}" class="img-wrap">--%>
<%--                        <img src="${imageUrl}"--%>
<%--                             alt="${product.title}"--%>
<%--                             onerror="this.onerror=null; this.src='${pageContext.request.contextPath}/default-image.jpg';">--%>
<%--                    </a>--%>
<%--                    <figcaption class="info-wrap">--%>
<%--                        <a href="./product-detail.jsp?id=${product.idProduct}" class="title">${product.title}</a>--%>
<%--                        <div class="price mt-1">$${product.price}</div>--%>
<%--                    </figcaption>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </c:forEach>--%>
<%--    </c:otherwise>--%>
<%--</c:choose>--%>


<div class="col-md-3">
    <div class="card card-product-grid">
        <a href="./product-detail.html" class="img-wrap">
            <img src="path/to/image.jpg" alt="Product">
        </a>
        <figcaption class="info-wrap">
            <a href="./product-detail.html" class="title">Tên Sản Phẩm</a>
            <div class="price mt-1">$179.00</div>
        </figcaption>
    </div>
</div>

<div class="col-md-3">
    <div class="card card-product-grid">
        <a href="./product-detail.html" class="img-wrap">
            <img src="path/to/image.jpg" alt="Product">
        </a>
        <figcaption class="info-wrap">
            <a href="./product-detail.html" class="title">Tên Sản Phẩm</a>
            <div class="price mt-1">$179.00</div>
        </figcaption>
    </div>
</div>

<div class="col-md-3">
    <div class="card card-product-grid">
        <a href="./product-detail.html" class="img-wrap">
            <img src="path/to/image.jpg" alt="Product">
        </a>
        <figcaption class="info-wrap">
            <a href="./product-detail.html" class="title">Tên Sản Phẩm</a>
            <div class="price mt-1">$179.00</div>
        </figcaption>
    </div>
</div>
