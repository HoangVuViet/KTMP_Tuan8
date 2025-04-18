package vn.edu.iuh.fit.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private Long customerId;
    private List<OrderDetailRequest> details;
}


