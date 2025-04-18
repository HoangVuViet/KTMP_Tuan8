package vn.edu.iuh.fit.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private LocalDateTime orderDate;
    private String status;
    private Double totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    // tiện ích để thêm chi tiết đơn hàng
    public void addDetail(OrderDetail detail) {
        detail.setOrder(this);
        this.orderDetails.add(detail);
    }
}

