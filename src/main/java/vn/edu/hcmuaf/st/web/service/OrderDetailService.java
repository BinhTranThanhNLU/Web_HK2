package vn.edu.hcmuaf.st.web.service;

import vn.edu.hcmuaf.st.web.dao.OrderDetailDao;
import vn.edu.hcmuaf.st.web.entity.OrderDetail;

public class OrderDetailService {

    private final OrderDetailDao orderDetailDao;

    public OrderDetailService() {
        this.orderDetailDao = new OrderDetailDao();
    }

    public void createOrderDetail(OrderDetail orderDetail) {
        orderDetailDao.insert(orderDetail);
    }

}
