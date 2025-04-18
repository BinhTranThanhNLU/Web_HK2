<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ include file="/view/view-index/header.jsp" %>


<body>
<div class="container" style="margin-top: 50px;">
    <center><i class="fas fa-check-circle" style="font-size: 72px;margin-bottom: 20px;color: #28A745;"></i></center>
    <h2 class="text-center">Thanh toán thành công</h2>
    <br>
    <div class="text-center">
        <a href="{% url 'store' %}" class="btn btn-success">Tiếp tục mua sắm</a>
    </div>
</div>

<div class="container" style="margin: 0 auto;width: 50%;padding: 50px;background: #f1f1f1;margin-top: 50px;margin-bottom: 50px;">

    <div style="color: red">
        ${requestScope.message}
    </div>

    <div class="row invoice row-printable">
        <div class="col-md-12">
            <!-- col-lg-12 start here -->
            <div class="panel panel-default plain" id="dash_0">
                <!-- Start .panel -->
                <div class="panel-body p30">
                    <div class="row">
                        <!-- Start .row -->
                        <div class="col-lg-6">
                            <!-- col-lg-6 start here -->
                            <div class="invoice-logo"><img src="./images/logo.png" alt="Invoice logo" style="max-height: 40px;"></div>
                        </div>
                        <!-- col-lg-6 end here -->
                        <div class="col-lg-6">
                            <!-- col-lg-6 start here -->
                            <div class="invoice-from">
                                <ul class="list-unstyled text-right">
                                    <li><strong>Hóa đơn cho</strong></li>
                                    <li>Trần Thanh Bình</li>
                                    <li>Roupark 37</li>
                                    <li>New York, NY, 2014</li>
                                    <li>Việt Nam</li>
                                </ul>
                            </div>
                        </div>
                        <!-- col-lg-6 end here -->
                        <div class="col-lg-12">
                            <!-- col-lg-12 start here -->
                            <div class="invoice-details mt25">
                                <div class="well">
                                    <ul class="list-unstyled mb0">
                                        <li><strong>Hóa đơn</strong> #ID</li>
                                        <li><strong>Giao dịch</strong> #COD</li>
                                        <li><strong>Ngày:</strong> 30/04/2025</li>
                                        <li><strong>Trạng thái:</strong> đã thanh toán</li>
                                    </ul>
                                </div>
                            </div>

                            <div class="invoice-items">
                                <div class="table-responsive" style="overflow: hidden; outline: none;" tabindex="0">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th class="per70 text-center">Mô tả</th>
                                            <th class="per5 text-center">Số lượng</th>
                                            <th class="per25 text-center">Tổng</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>1024MB Cloud 2.0 Server - elisium.dynamic.com (12/04/2014 - 01/03/2015)</td>
                                            <td class="text-center">1</td>
                                            <td class="text-center">
                                                <span>
                                                    <fmt:formatNumber value="100000" pattern="#,##0 đ"/>
                                                </span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Logo design</td>
                                            <td class="text-center">1</td>
                                            <td class="text-center">
                                                <span>
                                                    <fmt:formatNumber value="100000" pattern="#,##0 đ"/>
                                                </span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Backup - 1024MB Cloud 2.0 Server - elisium.dynamic.com</td>
                                            <td class="text-center">12</td>
                                            <td class="text-center">
                                                <span>
                                                    <fmt:formatNumber value="100000" pattern="#,##0 đ"/>
                                                </span>
                                            </td>
                                        </tr>
                                        </tbody>
                                        <tfoot>
                                        <tr>
                                            <th colspan="2" class="text-right">Tổng tiền:</th>
                                            <th class="text-center">
                                                <span>
                                                    <fmt:formatNumber value="100000" pattern="#,##0 đ"/>
                                                </span>
                                            </th>
                                        </tr>
                                        <tr>
                                            <th colspan="2" class="text-right">Giảm:</th>
                                            <th class="text-center">
                                                <span>
                                                    <fmt:formatNumber value="100000" pattern="#,##0 đ"/>
                                                </span>
                                            </th>
                                        </tr>

                                        <tr>
                                            <th colspan="2" class="text-right">Tổng thanh toán:</th>
                                            <th class="text-center">
                                                <span>
                                                    <fmt:formatNumber value="100000" pattern="#,##0 đ"/>
                                                </span>
                                            </th>
                                        </tr>
                                        </tfoot>
                                    </table>
                                </div>
                            </div>
                            <div class="invoice-footer mt25">
                                <p class="text-center">Cảm ơn vì đã mua sản phẩm của chúng tôi!</p>
                            </div>
                        </div>
                        <!-- col-lg-12 end here -->
                    </div>
                    <!-- End .row -->
                </div>
            </div>
            <!-- End .panel -->
        </div>
        <!-- col-lg-12 end here -->
    </div>
</div>


<%@ include file="/view/view-index/footer.jsp" %>
</body>
</html>
