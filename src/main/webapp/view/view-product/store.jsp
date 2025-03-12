<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<body>
<%@ include file="/view/view-index/header.jsp" %>
<section class="section-pagetop bg">
    <div class="container">
        <h2 class="title-page">Tất Cả Sản Phẩm</h2>

    </div> <!-- container //  -->
</section>
<!-- ========================= SECTION INTRO END// ========================= -->
<!-- ========================= SECTION CONTENT ========================= -->
<section class="section-content padding-y">
    <div class="container">
        <div class="row">
            <aside class="col-md-3">
                <div class="card">
                    <article class="filter-group">
                        <header class="card-header">
                            <a href="#" data-toggle="collapse" data-target="#collapse_1" aria-expanded="true" class="">
                                <i class="icon-control fa fa-chevron-down"></i>
                                <h6 class="title">Thể Loại</h6>
                            </a>
                        </header>
                        <div class="filter-content collapse show" id="collapse_1" style="">
                            <div class="card-body">
                                <ul class="list-menu">
                                    <li><a href="${pageContext.request.contextPath}/boy-t-shirt">Bé Trai</a></li>
                                    <li><a href="${pageContext.request.contextPath}/boy-t-shirt?idCategory=1">--Áo</a></li>
                                    <li><a href="${pageContext.request.contextPath}/boy-t-shirt?idCategory=2">--Quần</a></li>
                                    <li><a href="${pageContext.request.contextPath}/boy-t-shirt?idCategory=3">--Giày,Dép</a></li>
                                    <li><a href="${pageContext.request.contextPath}/boy-t-shirt?idCategory=4">--Đồ Bộ</a></li>
                                    <li><a href="#">Bé Gái</a></li>
                                    <li><a href="${pageContext.request.contextPath}/boy-t-shirt?idCategory=7">--Váy </a></li>
                                    <li><a href="${pageContext.request.contextPath}/boy-t-shirt?idCategory=5">--Áo </a></li>
                                    <li><a href="${pageContext.request.contextPath}/boy-t-shirt?idCategory=6">--Quần </a></li>
                                    <li><a href="${pageContext.request.contextPath}/boy-t-shirt?idCategory=8">--Đồ Bộ </a></li>
                                </ul>
                            </div> <!-- card-body.// -->
                        </div>
                    </article> <!-- filter-group  .// -->
                    <article class="filter-group">
                        <header class="card-header">
                            <a href="#" data-toggle="collapse" data-target="#collapse_4" aria-expanded="true" class="">
                                <i class="icon-control fa fa-chevron-down"></i>
                                <h6 class="title">Kích Cỡ </h6>
                            </a>
                        </header>
                        <div class="filter-content collapse show" id="collapse_4" style="">
                            <div class="card-body">
                                <label class="checkbox-btn">
                                    <input type="checkbox">
                                    <span class="btn btn-light"> 2 Tuổi </span>
                                </label>
                                <label class="checkbox-btn">
                                    <input type="checkbox">
                                    <span class="btn btn-light"> 3 Tuổi </span>
                                </label>
                                <label class="checkbox-btn">
                                    <input type="checkbox">
                                    <span class="btn btn-light"> 4 Tuổi </span>
                                </label>
                                <label class="checkbox-btn">
                                    <input type="checkbox">
                                    <span class="btn btn-light">5 Tuổi </span>
                                </label>
                            </div><!-- card-body.// -->
                        </div>
                    </article> <!-- filter-group .// -->
                    <article class="filter-group">
                        <header class="card-header">
                            <a href="#" data-toggle="collapse" data-target="#collapse_3" aria-expanded="true" class="">
                                <i class="icon-control fa fa-chevron-down"></i>
                                <h6 class="title">Giá</h6>
                            </a>
                        </header>
                        <div class="filter-content collapse show" id="collapse_3" style="">
                            <div class="card-body">
                                <div class="form-row">
                                    <div class="form-group col-md-6">
                                        <label>Nhỏ Nhất</label>
                                        <!-- <input class="form-control" placeholder="$0" type="number"> -->
                                        <select class="mr-2 form-control">
                                            <option value="50">50.000đ</option>
                                            <option value="100">100.000đ</option>
                                            <option value="150">200.000đ</option>
                                            <option value="200">300.000đ</option>
                                            <option value="500">400.000đ</option>
                                            <option value="1000">500.000đ</option>
                                        </select>
                                    </div>
                                    <div class="form-group text-right col-md-6">
                                        <label>Lớn Nhất</label>
                                        <select class="mr-2 form-control">
                                            <option value="50">100.000đ</option>
                                            <option value="100">300.000đ</option>
                                            <option value="150">400.000đ</option>
                                            <option value="200">500.000đ</option>
                                            <option value="500">600.000đ</option>
                                            <option value="1000">700.000đ</option>
                                        </select>
                                    </div>
                                </div> <!-- form-row.// -->
                                <button class="btn btn-block btn-primary">Áp Dụng</button>
                            </div><!-- card-body.// -->
                        </div>
                    </article> <!-- filter-group .// -->
                </div> <!-- card.// -->
            </aside> <!-- col.// -->
            <main class="col-md-9">
                <header class="border-bottom mb-4 pb-3">
                    <div class="form-inline">
                        <span class="mr-md-auto">32 Sản Phẩm </span>
                    </div>
                </header>
                <div class="row">
                    <c:forEach var="product" items="${products}" varStatus="status">
                        <c:if test="${status.index < 151}">
                            <div class="col-md-4 mb-4">
                                <figure class="card card-product-grid">
                                    <div class="img-wrap">
                                        <c:set var="imageUrl"
                                               value="${not empty product.productImages ? product.productImages[0].imageUrl : 'default.jpg'}"/>
                                        <img src="${imageUrl}" alt="${product.title}">
                                    </div>
                                    <figcaption class="info-wrap">
                                        <div class="fix-height">
                                            <a href="#" class="title">${product.title}</a>
                                            <div class="price-wrap mt-2">
                                        <span class="price">
                                            <fmt:formatNumber value="${product.price}" pattern="#,##0 đ"/>
                                        </span>
                                            </div>
                                        </div>
                                        <a href="#" class="btn btn-block btn-primary">Thêm Vào Giỏ</a>
                                    </figcaption>
                                </figure>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
                <nav class="mt-4" aria-label="Page navigation">
                    <ul class="pagination">
                        <c:if test="${currentPage > 1}">
                            <li class="page-item">
                                <a class="page-link" href="?idCategory=${idCategory}&page=${currentPage - 1}">Lùi</a>
                            </li>
                        </c:if>

                        <c:forEach var="i" begin="1" end="${totalPages}">
                            <li class="page-item ${i == currentPage ? 'active' : ''}">
                                <a class="page-link" href="?idCategory=${idCategory}&page=${i}">${i}</a>
                            </li>
                        </c:forEach>

                        <c:if test="${currentPage < totalPages}">
                            <li class="page-item">
                                <a class="page-link" href="?idCategory=${idCategory}&page=${currentPage + 1}">Tiến</a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </main> <!-- col.// -->
        </div>
    </div> <!-- container .//  -->
</section>
<%@ include file="/view/view-index/footer.jsp" %>
</body>
</html>