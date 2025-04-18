package vn.edu.iuh.fit.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.dtos.*;
import vn.edu.iuh.fit.messaging.OrderMessageSender;
import vn.edu.iuh.fit.models.Order;
import vn.edu.iuh.fit.models.OrderDetail;
import vn.edu.iuh.fit.repositories.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final RabbitTemplate rabbitTemplate;  // ✅ Khai báo và inject

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routing-key}")
    private String routingKey;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMessageSender messageSender;

    public OrderService(RabbitTemplate rabbitTemplate, OrderRepository repo, OrderMessageSender sender) {
        this.rabbitTemplate = rabbitTemplate;
        this.orderRepository = repo;
        this.messageSender = sender;
    }

    public Order createOrder(OrderRequest req) {
        Order order = new Order();
        order.setCustomerId(req.getCustomerId());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");

        double total = 0;
        for (OrderDetailRequest d : req.getDetails()) {
            OrderDetail detail = new OrderDetail();
            detail.setProductId(d.getProductId());
            detail.setQuantity(d.getQuantity());
            detail.setUnitPrice(d.getUnitPrice());

            order.addDetail(detail);
            total += d.getQuantity() * d.getUnitPrice();
        }

        order.setTotalAmount(total);
        Order savedOrder = orderRepository.save(order);
        savedOrder.getOrderDetails().forEach(item -> {
            ProductStockUpdateDto message = new ProductStockUpdateDto(
                    item.getProductId(),
                    item.getQuantity()
            );
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
        });
        return savedOrder;
    }


    public Optional<Order> getOrder(Long id) {
        return orderRepository.findById(id);
    }

    public void cancelOrder(Long id) {
        orderRepository.findById(id).ifPresent(order -> {
            order.setStatus("CANCELLED");
            orderRepository.save(order);
        });
    }

    public OrderResponse toOrderResponse(Order order) {
        OrderResponse response = new OrderResponse();
        response.setId(order.getId());
        response.setCustomerId(order.getCustomerId());
        response.setOrderDate(order.getOrderDate());
        response.setStatus(order.getStatus());
        response.setTotalAmount(order.getTotalAmount());

        List<OrderDetailResponse> detailResponses = order.getOrderDetails().stream().map(detail -> {
            OrderDetailResponse d = new OrderDetailResponse();
            d.setProductId(detail.getProductId());
            d.setQuantity(detail.getQuantity());
            d.setUnitPrice(detail.getUnitPrice());
            return d;
        }).collect(Collectors.toList());

        response.setDetails(detailResponses);
        return response;
    }

}
