package vn.edu.hcmuaf.fit.backend.bookingticket_backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.OrderDetail;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.OrderDetailService;

import java.util.List;

@RestController
@RequestMapping("api/orderdetail")
@CrossOrigin("http://localhost:3000")
public class OrderDetailController {
    private OrderDetailService orderDetailService;

    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    //Get all OrderDetail
    @GetMapping
    public List<OrderDetail> getAllOrderDetails(){return orderDetailService.getAllOrderDetail();}

    // Create a new OrderDetail
    @PostMapping
    public ResponseEntity<OrderDetail> createOrderDetail(@RequestBody OrderDetail orderDetail){
        return  new ResponseEntity<>(orderDetailService.saveOrderDetail(orderDetail), HttpStatus.CREATED);
    }

    // Get OrderDetail by id
    @GetMapping("{id}")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable ("id") String id){
        return new ResponseEntity<>(orderDetailService.getOrderDetailByID(id), HttpStatus.OK);
    }

    // Update OrderDetail by id
    @PutMapping("{id}")
    public ResponseEntity<OrderDetail> updateOrderDetailById(@PathVariable ("id") String id, @RequestBody OrderDetail orderDetail){
        return new ResponseEntity<>(orderDetailService.updateOrderDetailByID(orderDetail, id), HttpStatus.OK);
    }

    //Delete OrderDetail by id
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteOrderDetailById(@PathVariable ("id") String id){
        orderDetailService.deleteOrderDetailByID(id);
        return new ResponseEntity<>("OrderDetail " + id + " is deleted successfully", HttpStatus.OK);
    }
}
