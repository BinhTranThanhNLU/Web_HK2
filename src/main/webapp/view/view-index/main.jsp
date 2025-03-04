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
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
</head>
<body>
<!-- ========================= SECTION MAIN ========================= -->
<section class="section-intro padding-y-sm">
    <div class="container">

        <div class="intro-banner-wrap">
            <img src="images/banners/1.jpg" class="img-fluid rounded">
        </div>

    </div> <!-- container //  -->
</section>
<!-- ========================= SECTION MAIN END// ========================= -->
<!-- ========================= SECTION  ========================= -->
<section class="section-name padding-y-sm">
    <div class="container">

        <header class="section-heading">
            <a href="./store.html" class="btn btn-outline-primary float-right">See all</a>
            <h3 class="section-title">Popular products</h3>
        </header><!-- sect-heading -->


        <div class="row">
            <div class="col-md-3">
                <div class="card card-product-grid">
                    <a href="./product-detail.html" class="img-wrap"> <img src="images/items/1.jpg"> </a>
                    <figcaption class="info-wrap">
                        <a href="./product-detail.html" class="title">Just another product name</a>
                        <div class="price mt-1">$179.00</div> <!-- price-wrap.// -->
                    </figcaption>
                </div>
            </div> <!-- col.// -->
            <div class="col-md-3">
                <div class="card card-product-grid">
                    <a href="./product-detail.html" class="img-wrap"> <img src="images/items/2.jpg"> </a>
                    <figcaption class="info-wrap">
                        <a href="./product-detail.html" class="title">Some item name here</a>
                        <div class="price mt-1">$280.00</div> <!-- price-wrap.// -->
                    </figcaption>
                </div>
            </div> <!-- col.// -->
            <div class="col-md-3">
                <div class="card card-product-grid">
                    <a href="./product-detail.html" class="img-wrap"> <img src="images/items/3.jpg"> </a>
                    <figcaption class="info-wrap">
                        <a href="./product-detail.html" class="title">Great product name here</a>
                        <div class="price mt-1">$56.00</div> <!-- price-wrap.// -->
                    </figcaption>
                </div>
            </div> <!-- col.// -->
            <div class="col-md-3">
                <div class="card card-product-grid">
                    <a href="./product-detail.html" class="img-wrap"> <img src="images/items/4.jpg"> </a>
                    <figcaption class="info-wrap">
                        <a href="./product-detail.html" class="title">Just another product name</a>
                        <div class="price mt-1">$179.00</div> <!-- price-wrap.// -->
                    </figcaption>
                </div>
            </div> <!-- col.// -->
            <div class="col-md-3">
                <div class="card card-product-grid">
                    <a href="./product-detail.html" class="img-wrap"> <img src="images/items/5.jpg"> </a>
                    <figcaption class="info-wrap">
                        <a href="./product-detail.html" class="title">Just another product name</a>
                        <div class="price mt-1">$179.00</div> <!-- price-wrap.// -->
                    </figcaption>
                </div>
            </div> <!-- col.// -->
            <div class="col-md-3">
                <div class="card card-product-grid">
                    <a href="./product-detail.html" class="img-wrap"> <img src="images/items/6.jpg"> </a>
                    <figcaption class="info-wrap">
                        <a href="./product-detail.html" class="title">Some item name here</a>
                        <div class="price mt-1">$280.00</div> <!-- price-wrap.// -->
                    </figcaption>
                </div>
            </div> <!-- col.// -->
            <div class="col-md-3">
                <div class="card card-product-grid">
                    <a href="./product-detail.html" class="img-wrap"> <img src="images/items/7.jpg"> </a>
                    <figcaption class="info-wrap">
                        <a href="./product-detail.html" class="title">Great product name here</a>
                        <div class="price mt-1">$56.00</div> <!-- price-wrap.// -->
                    </figcaption>
                </div>
            </div> <!-- col.// -->
            <div class="col-md-3">
                <div class="card card-product-grid">
                    <a href="./product-detail.html" class="img-wrap"> <img src="images/items/9.jpg"> </a>
                    <figcaption class="info-wrap">
                        <a href="./product-detail.html" class="title">Just another product name</a>
                        <div class="price mt-1">$179.00</div> <!-- price-wrap.// -->
                    </figcaption>
                </div>
            </div> <!-- col.// -->
        </div> <!-- row.// -->

    </div><!-- container // -->
</section>
<!-- ========================= SECTION  END// ========================= -->
</body>
</html>
