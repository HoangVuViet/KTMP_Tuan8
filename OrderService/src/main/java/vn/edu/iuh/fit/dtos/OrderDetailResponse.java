package vn.edu.iuh.fit.dtos;

import lombok.Data;

@Data
public class OrderDetailResponse {
    private Long productId;
    private Integer quantity;
    private Double unitPrice;

}
