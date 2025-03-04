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
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Trang chủ</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/card-product.css">
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
            <a href="./store.html" class="btn btn-outline-primary float-right">Xem tất cả</a>
            <h3 class="section-title">Đồ Bé Trai</h3>
        </header><!-- sect-heading -->


        <div class="row">
            <div class="col-md-3">
                <div class="product-card">
                    <div class="badge-custome">Hot</div>
                    <div class="product-tumb">
                        <img src="https://i.imgur.com/xdbHo4E.png" alt="">
                    </div>
                    <div class="product-details">
                        <span class="product-catagory">Women,bag</span>
                        <h4><a href="">Women leather bag</a></h4>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Vero, possimus nostrum!</p>
                        <div class="product-bottom-details">
                            <div class="product-price"><small>$96.00</small>$230.99</div>
                            <div class="product-links">
                                <a href=""><i class="fa fa-heart"></i></a>
                                <a href=""><i class="fa fa-shopping-cart"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- col.// -->
            <div class="col-md-3">
                <div class="product-card">
                    <div class="badge-custome">Hot</div>
                    <div class="product-tumb">
                        <img src="https://i.imgur.com/xdbHo4E.png" alt="">
                    </div>
                    <div class="product-details">
                        <span class="product-catagory">Women,bag</span>
                        <h4><a href="">Women leather bag</a></h4>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Vero, possimus nostrum!</p>
                        <div class="product-bottom-details">
                            <div class="product-price"><small>$96.00</small>$230.99</div>
                            <div class="product-links">
                                <a href=""><i class="fa fa-heart"></i></a>
                                <a href=""><i class="fa fa-shopping-cart"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- col.// -->
            <div class="col-md-3">
                <div class="product-card">
                    <div class="badge-custome">Hot</div>
                    <div class="product-tumb">
                        <img src="https://i.imgur.com/xdbHo4E.png" alt="">
                    </div>
                    <div class="product-details">
                        <span class="product-catagory">Women,bag</span>
                        <h4><a href="">Women leather bag</a></h4>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Vero, possimus nostrum!</p>
                        <div class="product-bottom-details">
                            <div class="product-price"><small>$96.00</small>$230.99</div>
                            <div class="product-links">
                                <a href=""><i class="fa fa-heart"></i></a>
                                <a href=""><i class="fa fa-shopping-cart"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- col.// -->
            <div class="col-md-3">
                <div class="product-card">
                    <div class="badge-custome">Hot</div>
                    <div class="product-tumb">
                        <img src="https://i.imgur.com/xdbHo4E.png" alt="">
                    </div>
                    <div class="product-details">
                        <span class="product-catagory">Women,bag</span>
                        <h4><a href="">Women leather bag</a></h4>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Vero, possimus nostrum!</p>
                        <div class="product-bottom-details">
                            <div class="product-price"><small>$96.00</small>$230.99</div>
                            <div class="product-links">
                                <a href=""><i class="fa fa-heart"></i></a>
                                <a href=""><i class="fa fa-shopping-cart"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- col.// -->
            <div class="col-md-3">
                <div class="product-card">
                    <div class="badge-custome">Hot</div>
                    <div class="product-tumb">
                        <img src="https://i.imgur.com/xdbHo4E.png" alt="">
                    </div>
                    <div class="product-details">
                        <span class="product-catagory">Women,bag</span>
                        <h4><a href="">Women leather bag</a></h4>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Vero, possimus nostrum!</p>
                        <div class="product-bottom-details">
                            <div class="product-price"><small>$96.00</small>$230.99</div>
                            <div class="product-links">
                                <a href=""><i class="fa fa-heart"></i></a>
                                <a href=""><i class="fa fa-shopping-cart"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- col.// -->
            <div class="col-md-3">
                <div class="product-card">
                    <div class="badge-custome">Hot</div>
                    <div class="product-tumb">
                        <img src="https://i.imgur.com/xdbHo4E.png" alt="">
                    </div>
                    <div class="product-details">
                        <span class="product-catagory">Women,bag</span>
                        <h4><a href="">Women leather bag</a></h4>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Vero, possimus nostrum!</p>
                        <div class="product-bottom-details">
                            <div class="product-price"><small>$96.00</small>$230.99</div>
                            <div class="product-links">
                                <a href=""><i class="fa fa-heart"></i></a>
                                <a href=""><i class="fa fa-shopping-cart"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- col.// -->
            <div class="col-md-3">
                <div class="product-card">
                    <div class="badge-custome">Hot</div>
                    <div class="product-tumb">
                        <img src="https://i.imgur.com/xdbHo4E.png" alt="">
                    </div>
                    <div class="product-details">
                        <span class="product-catagory">Women,bag</span>
                        <h4><a href="">Women leather bag</a></h4>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Vero, possimus nostrum!</p>
                        <div class="product-bottom-details">
                            <div class="product-price"><small>$96.00</small>$230.99</div>
                            <div class="product-links">
                                <a href=""><i class="fa fa-heart"></i></a>
                                <a href=""><i class="fa fa-shopping-cart"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- col.// -->
            <div class="col-md-3">
                <div class="product-card">
                    <div class="badge-custome">Hot</div>
                    <div class="product-tumb">
                        <img src="https://i.imgur.com/xdbHo4E.png" alt="">
                    </div>
                    <div class="product-details">
                        <span class="product-catagory">Women,bag</span>
                        <h4><a href="">Women leather bag</a></h4>
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Vero, possimus nostrum!</p>
                        <div class="product-bottom-details">
                            <div class="product-price"><small>$96.00</small>$230.99</div>
                            <div class="product-links">
                                <a href=""><i class="fa fa-heart"></i></a>
                                <a href=""><i class="fa fa-shopping-cart"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div> <!-- col.// -->

        </div> <!-- row.// -->

    </div><!-- container // -->
</section>
<!-- ========================= SECTION  END// ========================= -->
</body>
</html>
