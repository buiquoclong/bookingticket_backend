package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service;

import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    OrderDetail saveOrderDetail(OrderDetail orderDetail);
    List<OrderDetail> getAllOrderDetail();
    OrderDetail getOrderDetailByID(String id);
    OrderDetail updateOrderDetailByID(OrderDetail orderDetail, String id);
    void deleteOrderDetailByID(String id);
}
