package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Order;

import java.util.List;

public interface OrderService {
    Order saveOrder(Order order);
    List<Order> getAllOrder();
    Order getOrderByID(int id);
    Order updateOrderByID(Order order, int id);
    void deleteOrderByID(int id);
}
