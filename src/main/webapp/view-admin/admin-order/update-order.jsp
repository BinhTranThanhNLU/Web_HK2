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
    <title>Cập nhật đơn hàng</title>
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
            <a href="./manage-order.html">Quản lý đơn hàng</a> / <a href="./update-order.html">Cập nhật</a>
        </div>
        <div class="container-form">
            <div class="container-layout">
                <h2>Cập nhật đơn hàng</h2>
                <form class="add-form">
                    <div class="form-group">
                        <label for="order-id">ID Đơn Hàng</label>
                        <input type="text" id="order-id" name="order-id" value="#DH12837" readonly/>
                    </div>

                    <div class="form-group">
                        <label for="username">Tài khoản</label>
                        <input type="text" id="username" name="username" value="nguyenA" readonly/>
                    </div>

                    <div class="form-group">
                        <label for="name">Họ và Tên</label>
                        <input type="text" id="name" name="name" value="Nguyễn Văn A" readonly/>
                    </div>

                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="text" id="email" name="email" value="nguyenvana@example.com" readonly/>
                    </div>

                    <div class="form-group">
                        <label for="phone">Số điện thoại</label>
                        <input type="text" id="phone" name="phone" value="0901234567"/>
                    </div>

                    <div class="form-group">
                        <label for="address">Địa chỉ giao hàng</label>
                        <input type="text" id="address" name="address" value="123 Đường ABC, Quận 1, TP.HCM"/>
                    </div>

                    <div class="form-group">
                        <label for="total-price">Tổng Tiền</label>
                        <input type="text" id="total-price" name="total-price" value="5,000,000 VND" readonly/>
                    </div>

                    <div class="form-group">
                        <label for="order-status">Trạng Thái Đơn Hàng</label>
                        <select id="order-status" name="order-status">
                            <option value="pending">Chờ xác nhận</option>
                            <option value="processing">Đang giao</option>
                            <option value="completed">Hoàn thành</option>
                            <option value="cancelled">Đã hủy</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="order-date">Ngày Đặt Hàng</label>
                        <input type="date" id="order-date" name="order-date" value="2025-02-23" readonly/>
                    </div>

                    <div class="form-group">
                        <label for="delivery-date">Ngày Giao Dự Kiến</label>
                        <input type="date" id="delivery-date" name="delivery-date"/>
                    </div>

                    <div class="form-group">
                        <label for="payment-method">Phương Thức Thanh Toán</label>
                        <select id="payment-method" name="payment-method">
                            <option value="cod">Thanh toán khi nhận hàng (COD)</option>
                            <option value="bank-transfer">Chuyển khoản</option>
                            <option value="e-wallet">Ví điện tử</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="order-notes">Ghi Chú Đơn Hàng</label>
                        <textarea id="order-notes" name="order-notes" placeholder="Nhập ghi chú nếu có"></textarea>
                    </div>

                    <div class="form-group">
                        <label for="order-table">Danh Sách Sản Phẩm</label>
                        <table class="table-order-items" id="order-table">
                            <thead>
                            <tr>
                                <th>Tên Sản Phẩm</th>
                                <th>Số Lượng</th>
                                <th>Giá</th>
                                <th>Tổng</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>Áo Thun Nam</td>
                                <td>2</td>
                                <td>250,000 VND</td>
                                <td>500,000 VND</td>
                            </tr>
                            <tr>
                                <td>Quần Jeans</td>
                                <td>1</td>
                                <td>700,000 VND</td>
                                <td>700,000 VND</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="save-button">Cập Nhật</button>
                        <button type="button" class="cancel-button">Hủy bỏ</button>
                    </div>
                </form>

            </div>
        </div>
    </section>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../../js/admin.js"></script>
<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.min.js"></script>
<script>
    $(document).ready(function () {
        $('#order-table').DataTable({
            "paging": true,         // Bật phân trang
            "searching": true,      // Bật tìm kiếm
            "ordering": true,       // Bật sắp xếp cột
            "info": true,           // Hiển thị thông tin tổng số dòng
            "language": {           // Tùy chỉnh ngôn ngữ
                "search": "Tìm kiếm:",
                "lengthMenu": "Hiển thị _MENU_ dòng",
                "info": "Hiển thị _START_ đến _END_ của _TOTAL_",
                "paginate": {
                    "first": "Đầu",
                    "last": "Cuối",
                    "next": "Tiếp",
                    "previous": "Trước"
                }
            }
        });
    });
</script>
<script src="https://cdn.ckeditor.com/4.22.1/standard/ckeditor.js"></script>
<script>
    CKEDITOR.replace('order-notes');
</script>
</body>
</html>
