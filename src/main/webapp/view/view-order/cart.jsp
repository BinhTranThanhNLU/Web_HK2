<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ include file="/view/view-index/header.jsp" %>

<html>
<section class="section-content padding-y bg">
    <div class="container">

        <!-- ============================ COMPONENT 1 ================================= -->

        <div class="row">
            <aside class="col-lg-9">
                <div class="card">
                    <table class="table table-borderless table-shopping-cart">
                        <thead class="text-muted">
                        <tr class="small text-uppercase">
                            <th scope="col">Sản phẩm</th>
                            <th scope="col">Màu</th>
                            <th scope="col">Kích thước</th>
                            <th scope="col">Số lượng</th>
                            <th scope="col">Giá</th>
                            <th scope="col" class="text-right"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${sessionScope.cart.cartItems.values()}">
                            <tr>
                                <td>
                                    <figure class="itemside align-items-center">
                                        <div class="aside">
                                            <img src="${item.imageUrl}" class="img-sm">
                                        </div>
                                        <figcaption class="info">
                                            <a href="#" class="title text-dark">${item.productTitle}</a>
<%--                                            <p class="text-muted small">Matrix: 25 Mpx <br> Brand: Canon</p>--%>
                                        </figcaption>
                                    </figure>
                                </td>
                                <!-- Dropdown chọn màu -->
                                <td>
                                    <select name="color" class="form-control form-select" style="width: fit-content">
                                        <c:set var="uniqueColors" value="" />
                                        <c:forEach var="color" items="${item.availableColors}">
                                            <c:if test="${not fn:contains(uniqueColors, color.color)}">
                                                <option value="${color.idColor}" ${color.idColor == item.color.idColor ? 'selected' : ''}>
                                                        ${color.color}
                                                </option>
                                                <c:set var="uniqueColors" value="${uniqueColors},${color.color}" />
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>

                                <!-- Dropdown chọn size -->
                                <td>
                                    <select name="size" class="form-control form-select w-0" style="width: fit-content">
                                        <c:set var="uniqueSizes" value="" />
                                        <c:forEach var="size" items="${item.availableSizes}">
                                            <c:if test="${not fn:contains(uniqueSizes, size.size)}">
                                                <option value="${size.idSize}" ${size.idSize == item.size.idSize ? 'selected' : ''}>
                                                        ${size.size}
                                                </option>
                                                <c:set var="uniqueSizes" value="${uniqueSizes},${size.size}" />
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>

                                <td>
                                    <!-- col.// -->
                                    <div class="col">
                                        <div class="input-group input-spinner">
                                            <div class="input-group-prepend">
                                                <button class="btn btn-light" type="button" id="button-plus"><i
                                                        class="fa fa-minus"></i></button>
                                            </div>
                                            <input type="text" class="form-control" value="1">
                                            <div class="input-group-append">
                                                <button class="btn btn-light" type="button" id="button-minus"><i
                                                        class="fa fa-plus"></i></button>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="price-wrap">
                                        <var class="price">
                                            <fmt:formatNumber value="${item.price}" pattern="#,##0 đ"/>
                                        </var>
                                    </div>
                                </td>
                                <td class="text-right">
                                    <form action="${pageContext.request.contextPath}/cart" method="post">
                                        <input type="hidden" name="action" value="remove"/>
                                        <input type="hidden" name="idVariant" value="${item.idVariant}"/>
                                        <button type="submit" class="btn btn-danger">Xóa</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
                </div> <!-- card.// -->

            </aside> <!-- col.// -->
            <aside class="col-lg-3">

                <div class="card">
                    <div class="card-body">
                        <dl class="dlist-align">
                            <dt>Tồng tiền:</dt>
                            <dd class="text-right">
                                <fmt:formatNumber value="${cart.totalPrice}" pattern="#,##0 đ"/>
                            </dd>
                        </dl>
                        <dl class="dlist-align">
                            <dt>Giảm:</dt>
                            <dd class="text-right"> 10000</dd>
                        </dl>
                        <dl class="dlist-align">
                            <dt>Tổng:</dt>
                            <dd class="text-right text-dark b">
                                <strong><fmt:formatNumber value="${cart.totalPrice}" pattern="#,##0 đ"/></strong>
                            </dd>
                        </dl>
                        <hr>
                        <p class="text-center mb-3">
                            <img src="./images/misc/payments.png" height="26">
                        </p>
                        <a href="./place-order.html" class="btn btn-primary btn-block"> Thanh toán </a>
                        <form action="${pageContext.request.contextPath}/cart" method="post">
                            <input type="hidden" name="action" value="continue">
                            <button type="submit" class="btn btn-light btn-block mt-4">Tiếp tục mua hàng</button>
                        </form>
<%--                        <a href="./store.html" class="btn btn-light btn-block">Tiếp tục mua hàng</a>--%>
                    </div>
                </div>

            </aside>


        </div>
        <!-- ============================ COMPONENT 1 END .// ================================= -->

    </div>
</section>
<%@ include file="/view/view-index/footer.jsp" %>
</html>
