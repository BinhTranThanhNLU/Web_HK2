package vn.edu.hcmuaf.st.web.service;

import vn.edu.hcmuaf.st.web.dao.OrderDao;
import vn.edu.hcmuaf.st.web.entity.Order;

public class OrderService {

    private final OrderDao orderDao;

    public OrderService() {
        this.orderDao = new OrderDao();
    }

    public int createOrder(Order order) {
        return orderDao.insertOrder(order);
    }
}
