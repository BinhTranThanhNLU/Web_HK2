<%--
  Created by IntelliJ IDEA.
  User: hdanh
  Date: 24/02/2025
  Time: 8:49 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/view/view-index/header.jsp" %>
<%@ include file="/view/view-index/footer.jsp" %>
<html>
<head>
    <title>Đăng Ký</title>
</head>
<body>
<!-- ========================= SECTION CONTENT ========================= -->
<section class="section-content padding-y">
  <!-- ============================ COMPONENT REGISTER   ================================= -->
  <!-- ============================ COMPONENT ĐĂNG KÝ ================================= -->
  <div class="card mx-auto" style="max-width:520px; margin-top:40px;">
    <article class="card-body">
      <header class="mb-4"><h4 class="card-title">Đăng ký</h4></header>
      <form>
        <div class="form-row">
          <div class="col form-group">
            <label>Họ</label>
            <input type="text" class="form-control" placeholder="">
          </div> <!-- form-group end.// -->
          <div class="col form-group">
            <label>Tên</label>
            <input type="text" class="form-control" placeholder="">
          </div> <!-- form-group end.// -->
        </div> <!-- form-row end.// -->
        <div class="form-group">
          <label>Email</label>
          <input type="email" class="form-control" placeholder="">
          <small class="form-text text-muted">Chúng tôi sẽ không chia sẻ email của bạn với bất kỳ ai.</small>
        </div> <!-- form-group end.// -->
        <div class="form-group">
          <label class="custom-control custom-radio custom-control-inline">
            <input class="custom-control-input" checked="" type="radio" name="gender" value="male">
            <span class="custom-control-label"> Nam </span>
          </label>
          <label class="custom-control custom-radio custom-control-inline">
            <input class="custom-control-input" type="radio" name="gender" value="female">
            <span class="custom-control-label"> Nữ </span>
          </label>
        </div> <!-- form-group end.// -->

        <div class="form-row">
          <div class="form-group col-md-6">
            <label>Tạo mật khẩu</label>
            <input class="form-control" type="password">
          </div> <!-- form-group end.// -->
          <div class="form-group col-md-6">
            <label>Nhập lại mật khẩu</label>
            <input class="form-control" type="password">
          </div> <!-- form-group end.// -->
        </div>
        <div class="form-group">
          <button type="submit" class="btn btn-primary btn-block"> Đăng ký </button>
        </div> <!-- form-group// -->
      </form>
    </article><!-- card-body.// -->
  </div> <!-- card .// -->
  <p class="text-center mt-4">Bạn đã có tài khoản? <a href="">Đăng nhập</a></p>
  <br><br>

  <!-- ============================ COMPONENT REGISTER  END.// ================================= -->
</section>
<!-- ========================= SECTION CONTENT END// ========================= -->
</body>
</html>
