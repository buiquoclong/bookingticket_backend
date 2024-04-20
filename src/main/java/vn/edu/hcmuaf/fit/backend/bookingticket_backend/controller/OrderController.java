package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.City;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.Order;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("api/order")
@CrossOrigin("http://localhost:3000")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Get all Order
    @GetMapping
    public List<Order> getAllOrders(){return orderService.getAllOrder();}

    // Create a new Order
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        return new ResponseEntity<>(orderService.saveOrder(order), HttpStatus.CREATED);
    }

    // Get Order bay id
    @GetMapping("{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable ("id") int id){
        return new ResponseEntity<>(orderService.getOrderByID(id), HttpStatus.OK);
    }

    // Update Order by id
    @PutMapping("{id}")
    public ResponseEntity<Order> updateOrderById(@PathVariable ("id") int id, @RequestBody Order order){
        return new ResponseEntity<>(orderService.updateOrderByID(order, id), HttpStatus.OK);
    }

    // Delete city by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable ("id") int id){
        orderService.deleteOrderByID(id);
        return new ResponseEntity<>("Order " + id + " is deleted successfully", HttpStatus.OK);
    }
}
