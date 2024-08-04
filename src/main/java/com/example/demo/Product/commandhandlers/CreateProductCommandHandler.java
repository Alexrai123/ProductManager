package com.example.demo.Product.commandhandlers;

import ch.qos.logback.core.util.StringUtil;
import com.example.demo.Command;
import com.example.demo.Exceptions.ProductNotValidException;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.ProductRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateProductCommandHandler implements Command<Product, ResponseEntity> {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ResponseEntity execute(Product product) {
        validateProduct(product);
        productRepository.save(product);
        return ResponseEntity.ok().build();
    }

    private void validateProduct(Product product){
        if (StringUtils.isBlank(product.getName())) {
            throw new ProductNotValidException("Product name cannot be empty");
        }
        if (StringUtils.isBlank(product.getDescription())) {
            throw new ProductNotValidException("Description cannot be empty");
        }
        if (product.getPrice() <= 0.0) {
            throw new ProductNotValidException("Price cannot be negative or null");
        }
        if (product.getQuantity() <= 0) {
            throw new ProductNotValidException("Quantity cannot be negative or null");
        }
    }

}
