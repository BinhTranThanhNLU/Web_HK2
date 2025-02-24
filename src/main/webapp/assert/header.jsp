
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<section class="header-main ">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-lg col-sm col-md col-6 flex-grow-0">
                <div class="category-wrap dropdown d-inline-block float-right">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-bars"></i> Kiểm Tra Đơn Hàng
                    </button>
                </div> <!-- category-wrap.// -->
            </div> <!-- col.// -->
            <a href="./store.html" class="btn btn-outline-primary">Liên Hệ</a>
            <div class="col-lg  col-md-6 col-sm-12 col">
                <form action="#" class="search">
                    <div class="input-group w-100">
                        <input type="text" class="form-control" style="width:60%;" placeholder="Search">
                        <div class="input-group-append">
                            <button class="btn btn-primary" type="submit">
                                <i class="fa fa-search"></i>
                            </button>
                        </div>
                    </div>
                </form> <!-- search-wrap .end// -->
            </div> <!-- col.// -->
            <div class="col-lg-3 col-sm-6 col-8 order-2 order-lg-3">
                <div class="d-flex justify-content-end mb-3 mb-lg-0">
                    <div class="widget-header">
                        <small class="title text-muted">Chào mừng quý khách!</small>
                        <div>
                            <a href="./signin.html">Đăng nhập</a> <span class="dark-transp"> | </span>
                            <a href="./register.html">Đăng ký</a>
                        </div>
                    </div>
                    <a href="./cart.html" class="widget-header pl-3 ml-3">
                        <div class="icon icon-sm rounded-circle border"><i class="fa fa-shopping-cart"></i></div>
                        <span class="badge badge-pill badge-danger notify">0</span>
                    </a>
                </div> <!-- widgets-wrap.// -->
            </div> <!-- col.// -->
        </div> <!-- row.// -->
    </div> <!-- container.// -->
</section> <!-- header-main .// -->

<!-- section-header.// -->
<header class="section-header">
    <section class="header-main border-bottom">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-lg-2 col-md-3 col-6">
                    <a href="./" class="brand-wrap"> <img class="logo" src="./images/logo.png">
                    </a>
                    <!-- brand-wrap.// -->
                </div>
                <div class="col-lg col-sm col-md col-6 flex-grow-0"></div>
                <!-- col.// -->

                <div class="col-lg  col-md-6 col-sm-12 col">
                    <nav class="navbar navbar-expand-lg navbar-light bg-while">
                        <div class="container">
                            <!-- Nút Toggle cho Mobile -->
                            <button class="navbar-toggler" type="button" data-toggle="collapse"
                                    data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false"
                                    aria-label="Toggle navigation">
                                <span class="navbar-toggler-icon"></span>
                            </button>

                            <!-- Nội dung Navbar -->
                            <div class=" " id="navbarNav">
                                <ul class="navbar-nav mx-auto">
                                    <li class="nav-item dropdown"><a class="nav-link text-dark" href="#"
                                                                     id="boyDropdown" role="button" data-toggle="dropdown"
                                                                     aria-haspopup="true" aria-expanded="false" style="font-size: 20px;">
                                        Trang Chủ </a></li>

                                    <!-- Bé Trai -->
                                    <li class="nav-item dropdown"><a class="nav-link  dropdown-toggle text-dark"
                                                                     href="#" id="boyDropdown" role="button" data-toggle="dropdown"
                                                                     aria-haspopup="true" aria-expanded="false" style="font-size: 20px;"> Bé
                                        Trai </a>
                                        <div class="dropdown-menu" aria-labelledby="boyDropdown">
                                            <a class="dropdown-item" href="#">Giày dép</a> <a class="dropdown-item"
                                                                                              href="#">Áo</a> <a class="dropdown-item" href="#">Quần</a>
                                        </div>
                                    </li>

                                    <!-- Bé Gái -->
                                    <li class="nav-item dropdown"><a class="nav-link dropdown-toggle text-dark"
                                                                     href="#" id="girlDropdown" role="button" data-toggle="dropdown"
                                                                     aria-haspopup="true" aria-expanded="false" style="font-size: 20px;"> Bé
                                        Gái </a>
                                        <div class="dropdown-menu" aria-labelledby="girlDropdown">
                                            <a class="dropdown-item" href="#">Giày dép</a> <a class="dropdown-item"
                                                                                              href="#">Áo</a> <a class="dropdown-item" href="#">Quần</a>
                                        </div>
                                    </li>
                                    <li class="nav-item dropdown"><a class="nav-link dropdown-toggle text-dark"
                                                                     href="#" id="boyDropdown" role="button" data-toggle="dropdown"
                                                                     aria-haspopup="true" aria-expanded="false" style="font-size: 20px;">
                                        Giày </a>
                                        <div class="dropdown-menu" aria-labelledby="boyDropdown">
                                            <a class="dropdown-item" href="#">Giày dép</a> <a class="dropdown-item"
                                                                                              href="#">Áo</a> <a class="dropdown-item" href="#">Quần</a>
                                        </div>
                                    </li>
                                    <li class="nav-item dropdown"><a class="nav-link dropdown-toggle text-dark"
                                                                     href="#" id="boyDropdown" role="button" data-toggle="dropdown"
                                                                     aria-haspopup="true" aria-expanded="false" style="font-size: 20px;"> Dép
                                    </a>
                                        <div class="dropdown-menu" aria-labelledby="boyDropdown">
                                            <a class="dropdown-item" href="#">Giày dép</a> <a class="dropdown-item"
                                                                                              href="#">Áo</a> <a class="dropdown-item" href="#">Quần</a>
                                        </div>
                                    </li>

                                </ul>

                            </div>

                        </div>
                        <!-- col.// -->
                </div>
                </nav>
            </div>
            <!-- col.// -->

            <!-- col.// -->
        </div>
        <!-- row.// -->
        </div>
        <!-- container.// -->
    </section>
    <!-- header-main .// -->
</header>
<!-- section-header.// -->
