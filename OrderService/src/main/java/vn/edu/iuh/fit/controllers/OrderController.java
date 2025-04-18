package vn.edu.iuh.fit.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.dtos.OrderRequest;
import vn.edu.iuh.fit.dtos.OrderResponse;
import vn.edu.iuh.fit.models.Order;
import vn.edu.iuh.fit.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService service) {
        this.orderService = service;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
//        return orderService.getOrder(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
        return orderService.getOrder(id)
                .map(order -> ResponseEntity.ok(orderService.toOrderResponse(order)))
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
        return ResponseEntity.ok().build();
    }
}
