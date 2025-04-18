package vn.edu.iuh.fit.dtos;

import lombok.Data;

@Data
public class ProductStockUpdateDto {
    private Long productId;
    private int quantity;

    public ProductStockUpdateDto(Long productId, int quantity) {
    }
}
