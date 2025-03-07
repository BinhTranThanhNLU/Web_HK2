<%--<%@ page import="vn.edu.hcmuaf.st.web.entity.Account" %>--%>
<%--<%--%>
<%--    Account user = (Account) session.getAttribute("user");--%>
<%--    if (user == null) {--%>
<%--        // Nếu không có thông tin người dùng trong session, chuyển hướng đến trang đăng nhập--%>
<%--        response.sendRedirect(request.getContextPath() + "/view/view-account/login.jsp");--%>
<%--        return;--%>
<%--    }--%>
<%--%>--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/card-product.css">
</head>
<body>
<!-- ========================= SECTION MAIN ========================= -->
<section class="section-intro padding-y-sm">
    <div class="container">
        <div class="intro-banner-wrap">
            <img src="images/banners/1.jpg" class="img-fluid rounded">
        </div>
    </div>
</section>
<!-- ========================= SECTION MAIN END// ========================= -->

<!-- ========================= SAN PHAM HOT MUA NHIEU  ========================= -->
<section class="section-name padding-y-sm my-4">
    <div class="container">
        <header class="section-heading">
            <a href="${pageContext.request.contextPath}/all-product"
               class="btn btn-outline-primary float-right">Xem tất cả</a>
            <a href="${pageContext.request.contextPath}/product?page=2"
               class="btn btn-outline-primary float-right">Xem Phân Trang</a>
            <h3 class="section-title">Các Sản Phẩm Hot</h3>
        </header>

        <div class="best-seller" id="best-seller">
            <div class="row">
                <c:forEach var="product" items="${products}" begin="1" end="8">
                    <div class="col-md-3 gap-3 mb-5">
                        <div class="product-card">
                            <div class="badge-custome">Hot</div>

                            <c:set var="imageUrl"
                                   value="${not empty product.productImages ? product.productImages[0].imageUrl : 'default.jpg'}"/>

                            <div class="product-tumb">
                                <img src="${imageUrl}" alt="${product.title}">
                            </div>
                            <div class="product-details">
                                <span class="product-catagory">${product.category.name}</span>
                                <h4>
                                    <a href="#">${product.title}</a>
                                </h4>
                                <div class="product-bottom-details">
                                    <div class="product-price">
                                        <small class="ori-price"><fmt:formatNumber value="${product.price}" pattern="#,##0 đ"/></small>
                                        <p class="dis-price"><fmt:formatNumber value="200000" pattern="#,##0 đ"/></p>
                                    </div>
                                    <div class="product-links">
                                        <a href="#"><i class="fa fa-heart"></i></a>
                                        <a href="#"><i class="fa fa-shopping-cart"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="load-more">
            <button>Xem thêm</button>
        </div>
    </div>
</section>

<!-- ========================= SAN PHAM GIAM GIA  ========================= -->
<section class="section-name padding-y-sm my-4">
    <div class="container">
        <header class="section-heading">
            <a href="${pageContext.request.contextPath}/view/view-product/store.jsp"
               class="btn btn-outline-primary float-right">Xem tất cả</a>
            <h3 class="section-title">Các Sản Phẩm Giảm Giá</h3>
        </header>

        <div class="disount-products" id="disount-products">
            <div class="row">
                <c:forEach var="product" items="${products}" begin="1" end="8">
                    <div class="col-md-3 gap-3 mb-5">
                        <div class="product-card">
                            <div class="badge-custome">Hot</div>

                            <c:set var="imageUrl"
                                   value="${not empty product.productImages ? product.productImages[0].imageUrl : 'default.jpg'}"/>

                            <div class="product-tumb">
                                <img src="${imageUrl}" alt="${product.title}">
                            </div>
                            <div class="product-details">
                                <span class="product-catagory">${product.category.name}</span>
                                <h4>
                                    <a href="#">${product.title}</a>
                                </h4>
                                <div class="product-bottom-details">
                                    <div class="product-price">
                                        <small class="ori-price"><fmt:formatNumber value="${product.price}" pattern="#,##0 đ"/></small>
                                        <p class="dis-price"><fmt:formatNumber value="200000" pattern="#,##0 đ"/></p>
                                    </div>
                                    <div class="product-links">
                                        <a href="#"><i class="fa fa-heart"></i></a>
                                        <a href="#"><i class="fa fa-shopping-cart"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="load-more">
            <button>Xem thêm</button>
        </div>
    </div>
</section>

<!-- ========================= SAN PHAM CUA BE TRAI  ========================= -->
<section class="section-name padding-y-sm my-4">
    <div class="container">
        <header class="section-heading">
            <a href="${pageContext.request.contextPath}/view/view-product/store.jsp"
               class="btn btn-outline-primary float-right">Xem tất cả</a>
            <h3 class="section-title">Thời Trang Bé Trai</h3>
        </header>

        <div class="nav-category" data-category="boy">
            <p class="tab" onclick="showTabBoy('ao-boy', 'boy')">Áo</p>
            <p class="tab" onclick="showTabBoy('quan-boy','boy')">Quần</p>
            <p class="tab" onclick="showTabBoy('giay-boy','boy')">Giày</p>
            <p class="tab" onclick="showTabBoy('do-bo-boy','boy')">Đồ bộ</p>
        </div>

        <div class="products-category" id="ao-boy">
            <div class="row">
                <c:forEach var="product" items="${products}" begin="1" end="8">
                    <div class="col-md-3 gap-3 mb-5">
                        <div class="product-card">
                            <div class="badge-custome">Hot</div>

                            <c:set var="imageUrl"
                                   value="${not empty product.productImages ? product.productImages[0].imageUrl : 'default.jpg'}"/>

                            <div class="product-tumb">
                                <img src="${imageUrl}" alt="${product.title}">
                            </div>
                            <div class="product-details">
                                <span class="product-catagory">${product.category.name}</span>
                                <h4>
                                    <a href="#">${product.title}</a>
                                </h4>
                                <div class="product-bottom-details">
                                    <div class="product-price">
                                        <small class="ori-price"><fmt:formatNumber value="${product.price}" pattern="#,##0 đ"/></small>
                                        <p class="dis-price"><fmt:formatNumber value="200000" pattern="#,##0 đ"/></p>
                                    </div>
                                    <div class="product-links">
                                        <a href="#"><i class="fa fa-heart"></i></a>
                                        <a href="#"><i class="fa fa-shopping-cart"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="products-category" id="quan-boy" style="display: none;">
            <div class="row">
                <c:forEach var="product" items="${products}" begin="1" end="8">
                    <div class="col-md-3 gap-3 mb-5">
                        <div class="product-card">
                            <div class="badge-custome">Hot</div>

                            <c:set var="imageUrl"
                                   value="${not empty product.productImages ? product.productImages[0].imageUrl : 'default.jpg'}"/>

                            <div class="product-tumb">
                                <img src="${imageUrl}" alt="${product.title}">
                            </div>
                            <div class="product-details">
                                <span class="product-catagory">${product.category.name}</span>
                                <h4>
                                    <a href="#">${product.title}</a>
                                </h4>
                                <div class="product-bottom-details">
                                    <div class="product-price">
                                        <small class="ori-price"><fmt:formatNumber value="${product.price}" pattern="#,##0 đ"/></small>
                                        <p class="dis-price"><fmt:formatNumber value="200000" pattern="#,##0 đ"/></p>
                                    </div>
                                    <div class="product-links">
                                        <a href="#"><i class="fa fa-heart"></i></a>
                                        <a href="#"><i class="fa fa-shopping-cart"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="products-category" id="giay-boy" style="display: none;">
            <div class="row">
                <c:forEach var="product" items="${products}" begin="1" end="8">
                    <div class="col-md-3 gap-3 mb-5">
                        <div class="product-card">
                            <div class="badge-custome">Hot</div>

                            <c:set var="imageUrl"
                                   value="${not empty product.productImages ? product.productImages[0].imageUrl : 'default.jpg'}"/>

                            <div class="product-tumb">
                                <img src="${imageUrl}" alt="${product.title}">
                            </div>
                            <div class="product-details">
                                <span class="product-catagory">${product.category.name}</span>
                                <h4>
                                    <a href="#">${product.title}</a>
                                </h4>
                                <div class="product-bottom-details">
                                    <div class="product-price">
                                        <small class="ori-price"><fmt:formatNumber value="${product.price}" pattern="#,##0 đ"/></small>
                                        <p class="dis-price"><fmt:formatNumber value="200000" pattern="#,##0 đ"/></p>
                                    </div>
                                    <div class="product-links">
                                        <a href="#"><i class="fa fa-heart"></i></a>
                                        <a href="#"><i class="fa fa-shopping-cart"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="products-category" id="do-bo-boy" style="display: none;">
            <div class="row">
                <c:forEach var="product" items="${products}" begin="1" end="8">
                    <div class="col-md-3 gap-3 mb-5">
                        <div class="product-card">
                            <div class="badge-custome">Hot</div>

                            <c:set var="imageUrl"
                                   value="${not empty product.productImages ? product.productImages[0].imageUrl : 'default.jpg'}"/>

                            <div class="product-tumb">
                                <img src="${imageUrl}" alt="${product.title}">
                            </div>
                            <div class="product-details">
                                <span class="product-catagory">${product.category.name}</span>
                                <h4>
                                    <a href="#">${product.title}</a>
                                </h4>
                                <div class="product-bottom-details">
                                    <div class="product-price">
                                        <small class="ori-price"><fmt:formatNumber value="${product.price}" pattern="#,##0 đ"/></small>
                                        <p class="dis-price"><fmt:formatNumber value="200000" pattern="#,##0 đ"/></p>
                                    </div>
                                    <div class="product-links">
                                        <a href="#"><i class="fa fa-heart"></i></a>
                                        <a href="#"><i class="fa fa-shopping-cart"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="load-more">
            <button>Xem thêm</button>
        </div>
    </div>
</section>

<!-- ========================= SAN PHAM CUA BE GAI  ========================= -->
<section class="section-name padding-y-sm my-4">
    <div class="container">
        <header class="section-heading">
            <a href="${pageContext.request.contextPath}/view/view-product/store.jsp"
               class="btn btn-outline-primary float-right">Xem tất cả</a>
            <h3 class="section-title">Thời Trang Bé Gái</h3>
        </header>

        <div class="nav-category" data-category="girl">
            <p class="tab" onclick="showTabBoy('ao-girl', 'girl')">Áo</p>
            <p class="tab" onclick="showTabBoy('quan-girl','girl')">Quần</p>
            <p class="tab" onclick="showTabBoy('vay-girl','girl')">Giày</p>
            <p class="tab" onclick="showTabBoy('do-bo-girl','girl')">Đồ bộ</p>
        </div>

        <div class="products-category" id="ao-girl">
            <div class="row">
                <c:forEach var="product" items="${products}" begin="1" end="8">
                    <div class="col-md-3 gap-3 mb-5">
                        <div class="product-card">
                            <div class="badge-custome">Hot</div>

                            <c:set var="imageUrl"
                                   value="${not empty product.productImages ? product.productImages[0].imageUrl : 'default.jpg'}"/>

                            <div class="product-tumb">
                                <img src="${imageUrl}" alt="${product.title}">
                            </div>
                            <div class="product-details">
                                <span class="product-catagory">${product.category.name}</span>
                                <h4>
                                    <a href="#">${product.title}</a>
                                </h4>
                                <div class="product-bottom-details">
                                    <div class="product-price">
                                        <small class="ori-price"><fmt:formatNumber value="${product.price}" pattern="#,##0 đ"/></small>
                                        <p class="dis-price"><fmt:formatNumber value="200000" pattern="#,##0 đ"/></p>
                                    </div>
                                    <div class="product-links">
                                        <a href="#"><i class="fa fa-heart"></i></a>
                                        <a href="#"><i class="fa fa-shopping-cart"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="products-category" id="quan-girl" style="display: none;">
            <div class="row">
                <c:forEach var="product" items="${products}" begin="1" end="8">
                    <div class="col-md-3 gap-3 mb-5">
                        <div class="product-card">
                            <div class="badge-custome">Hot</div>

                            <c:set var="imageUrl"
                                   value="${not empty product.productImages ? product.productImages[0].imageUrl : 'default.jpg'}"/>

                            <div class="product-tumb">
                                <img src="${imageUrl}" alt="${product.title}">
                            </div>
                            <div class="product-details">
                                <span class="product-catagory">${product.category.name}</span>
                                <h4>
                                    <a href="#">${product.title}</a>
                                </h4>
                                <div class="product-bottom-details">
                                    <div class="product-price">
                                        <small class="ori-price"><fmt:formatNumber value="${product.price}" pattern="#,##0 đ"/></small>
                                        <p class="dis-price"><fmt:formatNumber value="200000" pattern="#,##0 đ"/></p>
                                    </div>
                                    <div class="product-links">
                                        <a href="#"><i class="fa fa-heart"></i></a>
                                        <a href="#"><i class="fa fa-shopping-cart"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="products-category" id="vay-girl" style="display: none;">
            <div class="row">
                <c:forEach var="product" items="${products}" begin="1" end="8">
                    <div class="col-md-3 gap-3 mb-5">
                        <div class="product-card">
                            <div class="badge-custome">Hot</div>

                            <c:set var="imageUrl"
                                   value="${not empty product.productImages ? product.productImages[0].imageUrl : 'default.jpg'}"/>

                            <div class="product-tumb">
                                <img src="${imageUrl}" alt="${product.title}">
                            </div>
                            <div class="product-details">
                                <span class="product-catagory">${product.category.name}</span>
                                <h4>
                                    <a href="#">${product.title}</a>
                                </h4>
                                <div class="product-bottom-details">
                                    <div class="product-price">
                                        <small class="ori-price"><fmt:formatNumber value="${product.price}" pattern="#,##0 đ"/></small>
                                        <p class="dis-price"><fmt:formatNumber value="200000" pattern="#,##0 đ"/></p>
                                    </div>
                                    <div class="product-links">
                                        <a href="#"><i class="fa fa-heart"></i></a>
                                        <a href="#"><i class="fa fa-shopping-cart"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="products-category" id="do-bo-girl" style="display: none;">
            <div class="row">
                <c:forEach var="product" items="${products}" begin="1" end="8">
                    <div class="col-md-3 gap-3 mb-5">
                        <div class="product-card">
                            <div class="badge-custome">Hot</div>

                            <c:set var="imageUrl"
                                   value="${not empty product.productImages ? product.productImages[0].imageUrl : 'default.jpg'}"/>

                            <div class="product-tumb">
                                <img src="${imageUrl}" alt="${product.title}">
                            </div>
                            <div class="product-details">
                                <span class="product-catagory">${product.category.name}</span>
                                <h4>
                                    <a href="#">${product.title}</a>
                                </h4>
                                <div class="product-bottom-details">
                                    <div class="product-price">
                                        <small class="ori-price"><fmt:formatNumber value="${product.price}" pattern="#,##0 đ"/></small>
                                        <p class="dis-price"><fmt:formatNumber value="200000" pattern="#,##0 đ"/></p>
                                    </div>
                                    <div class="product-links">
                                        <a href="#"><i class="fa fa-heart"></i></a>
                                        <a href="#"><i class="fa fa-shopping-cart"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="load-more">
            <button>Xem thêm</button>
        </div>
    </div>
</section>

<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>
