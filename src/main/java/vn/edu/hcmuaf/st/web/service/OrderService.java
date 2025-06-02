package vn.edu.hcmuaf.st.web.service;

import vn.edu.hcmuaf.st.web.dao.OrderDao;
import vn.edu.hcmuaf.st.web.entity.Order;

import java.util.List;

public class OrderService {

    private final OrderDao orderDao;

    public OrderService() {
        this.orderDao = new OrderDao();
    }

    public int createOrder(Order order) {
        return orderDao.insertOrder(order);
    }

    public Order getOrderById(int id) {
        return orderDao.getOrderById(id);
    }

    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    public void updateOrderStatus(int idOrder, String status) {
        orderDao.updateStatus(idOrder, status);
    }

    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        Order order = orderService.getOrderById(5);
        System.out.println(order);
    }
}
