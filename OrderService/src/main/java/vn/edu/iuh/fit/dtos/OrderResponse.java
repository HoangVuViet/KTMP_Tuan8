package vn.edu.iuh.fit.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private Long customerId;
    private LocalDateTime orderDate;
    private String status;
    private Double totalAmount;
    private List<OrderDetailResponse> details;

}
