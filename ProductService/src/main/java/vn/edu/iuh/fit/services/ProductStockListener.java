package vn.edu.iuh.fit.services;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import vn.edu.iuh.fit.dtos.ProductStockUpdateDto;
import vn.edu.iuh.fit.models.Product;
import vn.edu.iuh.fit.repositories.ProductRepository;

@Component
@RequiredArgsConstructor
public class ProductStockListener {

    private final ProductRepository productRepository;

    @RabbitListener(queues = "order.queue") // Cùng tên queue với OrderService
    public void handleStockUpdate(ProductStockUpdateDto dto) {
        Product product = productRepository.findById(dto.getProductId())
            .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        if (product.getStockQuantity() < dto.getQuantity()) {
            throw new RuntimeException("Không đủ hàng trong kho");
        }

        product.setStockQuantity(product.getStockQuantity() - dto.getQuantity());
        productRepository.save(product);
    }
}
