<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100..900;1,100..900&display=swap"
          rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="../../css/both-nav.css">
    <link rel="stylesheet" href="../../css/add.css">

    <title>Thêm sản phẩm</title>
</head>
<body>

<!-- Side Nav -->
<nav class="navbar-left" id="navbar-left">
    <div class="sidebar-header">
        <div class="user-profile">
            <div class="sidebar-title">
                <a href="../../admin.html" class="brand-logo-mini"><img src="../../image/logo.png" alt="logo"
                                                                        width="125"></a>
                <h2>Litte Whale</h2>
            </div>
            <img class="user-avatar" src="../../image/avatar-admin.png" alt="User Image" width="100">
            <div class="user-info">
                <p class="user-name">AdminName</p>
                <p class="user-greeting">Chào mừng bạn trở lại</p>
            </div>
        </div>
    </div>
    <hr>
    <ul class="sidebar-menu">
        <li class="menu-item ">
            <a class="menu-link" href="../../admin.html">
                <span class="menu-icon"><i class="fa fa-tachometer-alt"></i></span>
                <span class="menu-title">Bảng điều khiển</span>
            </a>
        </li>
        <li class="menu-item">
            <a class="menu-link" href="../admin-user/manage-user.html">
                <span class="menu-icon"><i class="fa fa-users"></i></span>
                <span class="menu-title">Quản lý khách hàng</span>
            </a>
        </li>
        <li class="menu-item">
            <a class="menu-link" href="../admin-product/manage-product.html">
                <span class="menu-icon"><i class="fa fa-box"></i></span>
                <span class="menu-title">Quản lý sản phẩm</span>
            </a>
        </li>
        <li class="menu-item">
            <a class="menu-link" href="../admin-order/manage-order.html">
                <span class="menu-icon"><i class="fa fa-tasks"></i></span>
                <span class="menu-title">Quản lý đơn hàng</span>
            </a>
        </li>
        <li class="menu-item">
            <a class="menu-link" href="../admin-coupon/manage-coupon.html">
                <span class="menu-icon"><i class="fa-solid fa-ticket"></i></span>
                <span class="menu-title">Quản lý mã giảm giá</span>
            </a>
        </li>
        <li class="menu-item">
            <a class="menu-link" href="../admin-category/manage-category.html">
                <span class="menu-icon"><i class="fa fa-chart-bar"></i></span>
                <span class="menu-title">Quản lý danh mục</span>
            </a>
        </li>
    </ul>
</nav>

<div class="container">
    <!-- Navbar -->
    <nav class="navbar">
        <div class="navbar-menu">
            <button class="navbar-toggler" id="navbar-toggle">
                <span class="navbar-icon">&#9776;</span>
            </button>
            <div class="search">
                <input type="text" placeholder="Search products">
            </div>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a href="../admin-product/add-product.html" class="nav-link"><i class="fa-solid fa-plus"></i>Thêm
                        sản phẩm mới</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link"><i class="fa-solid fa-message"></i> Tin Nhắn <span
                            class="badge">0</span></a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link"><i class="fa-solid fa-bell"></i> Thông Báo <span
                            class="badge">0</span></a>
                </li>
            </ul>
            <div class="profile-container">
                <a href="#" class="profile-link">
                    <img src="https://anhcute.net/wp-content/uploads/2024/09/Hinh-anh-chibi-Spiderman-sieu-dang-yeu.jpg"
                         alt="Profile" class="profile-pic">
                    <span class="profile-name">AdminName</span>
                </a>
            </div>
        </div>
    </nav>

    <!-- Form -->
    <section class="form-add">
        <div class="breadcrumb">
            <a href="../admin-product/manage-product.html">Quản lý sản phẩm</a> / <a
                href="../admin-product/add-product.html">Thêm sẩn phẩm</a>
        </div>
        <div class="container-form">
            <div class="container-layout">
                <h2>Thêm sản phẩm</h2>
                <form class="add-form">
                    <div class="form-group">
                        <label for="product-id">ID Sản Phẩm</label>
                        <input type="text" id="product-id" name="product-id" placeholder="Nhập ID sản phẩm"/>
                    </div>

                    <div class="form-group">
                        <label for="product-name">Tên Sản Phẩm</label>
                        <input type="text" id="product-name" name="product-name" placeholder="Nhập tên sản phẩm"/>
                    </div>

                    <div class="form-group">
                        <label for="description">Mô Tả Sản Phẩm</label>
                        <textarea id="description" name="description" placeholder="Nhập mô tả sản phẩm"></textarea>
                    </div>

                    <div class="form-group">
                        <label for="category">Danh Mục</label>
                        <select id="category" name="category">
                            <option value="">Chọn danh mục</option>
                            <option value="clothes">Quần áo</option>
                            <option value="shoes">Giày dép</option>
                            <option value="accessories">Phụ kiện</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="color">Màu Sắc</label>
                        <select id="color" name="color">
                            <option value="">Chọn màu sắc</option>
                            <option value="red">Đỏ</option>
                            <option value="blue">Xanh dương</option>
                            <option value="black">Đen</option>
                            <option value="white">Trắng</option>
                            <option value="yellow">Vàng</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="size">Kích Thước</label>
                        <select id="size" name="size">
                            <option value="">Chọn kích thước</option>
                            <option value="S">S</option>
                            <option value="M">M</option>
                            <option value="L">L</option>
                            <option value="XL">XL</option>
                            <option value="XXL">XXL</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="stock">Số Lượng Tồn Kho</label>
                        <input type="number" id="stock" name="stock" placeholder="Nhập số lượng" min="0"/>
                    </div>

                    <div class="form-group">
                        <label for="price">Giá Bán</label>
                        <input type="text" id="price" name="price" placeholder="Nhập giá sản phẩm"/>
                    </div>

                    <div class="form-group">
                        <label for="product-image">Hình Ảnh</label>
                        <div class="file-input">
                            <button type="button">
                                <i class="fas fa-upload"></i> Chọn ảnh
                            </button>
                            <input type="file" id="product-image" name="product-image"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="status">Trạng Thái</label>
                        <select id="status" name="status">
                            <option value="available">Còn hàng</option>
                            <option value="out-of-stock">Hết hàng</option>
                        </select>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="save-button">Lưu lại</button>
                        <button type="button" class="cancel-button">Hủy bỏ</button>
                    </div>
                </form>

            </div>
        </div>

    </section>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../../js/admin.js"></script>
<script src="https://cdn.ckeditor.com/4.22.1/standard/ckeditor.js"></script>
<script>
    CKEDITOR.replace('description');
</script>
</body>
</html>
