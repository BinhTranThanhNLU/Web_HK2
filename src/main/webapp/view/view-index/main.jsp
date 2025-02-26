<%@ page import="java.util.List, java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<title>Trang Chủ</title>
<%
    // Lấy danh sách ảnh từ request
    Object imageObj = request.getAttribute("images");

    // Kiểm tra nếu `images` chưa được set hoặc không phải List thì tạo danh sách trống
    List<String> images;
    if (imageObj instanceof List) {
        images = (List<String>) imageObj;
    } else {
        images = new ArrayList<>();
    }

    // Nếu không có ảnh, hiển thị thông báo
    if (images.isEmpty()) {
%>
<p>Không có sản phẩm nào để hiển thị.</p>
<%
} else {
    for (String imageUrl : images) {
%>
<div class="col-md-3">
    <div class="card card-product-grid">
        <a href="./product-detail.jsp" class="img-wrap">
            <img src="<%= request.getContextPath() + imageUrl %>" alt="Product">
        </a>
        <figcaption class="info-wrap">
            <a href="./product-detail.jsp" class="title">Tên Sản Phẩm</a>
            <div class="price mt-1">$179.00</div>
        </figcaption>
    </div>
</div>
<%
        }
    }
%>
