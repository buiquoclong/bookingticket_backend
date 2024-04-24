package vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.impl;

import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.dto.OrderDTO;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.exception.ResourceNotFoundException;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Order;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.OrderRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.repository.UserRepository;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.OrderService;

import java.util.List;

@Service
public class OrderSeviceImpl implements OrderService {
     private OrderRepository orderRepository;
     private UserRepository userRepository;

//    public OrderSeviceImpl(OrderRepository orderRepository) {
//        this.orderRepository = orderRepository;
//    }

    public OrderSeviceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

        @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }


    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderByID(int id) {
        return orderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("City", "Id", id));
    }

    @Override
    public Order updateOrderByID(Order order, int id) {
        return null;
    }

    @Override
    public void deleteOrderByID(int id) {
         orderRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("City", "Id", id));
         orderRepository.deleteById(id);
    }
}
