package com.example.demo.Product.commandhandlers;

import com.example.demo.Command;
import com.example.demo.Exceptions.ProductNotFoundException;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Model.UpdateProductCommand;
import com.example.demo.Product.ProductRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateProductCommandHandler implements Command<UpdateProductCommand, ResponseEntity> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<ResponseEntity> execute(UpdateProductCommand command) {
        Optional<Product> optionalProduct = productRepository.findById(command.getId());
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException();
        }

        Product product = command.getProduct();
        validateProduct(product);
        product.setId(command.getId());
        productRepository.save(product);
        return ResponseEntity.ok().build();
    }

    private void validateProduct(Product product){
        if (StringUtils.isBlank(product.getName())) {
            throw new RuntimeException("Product name cannot be empty");
        }
        if (StringUtils.isBlank(product.getDescription())) {
            throw new RuntimeException("Description cannot be empty");
        }
        if (product.getPrice() <= 0.0) {
            throw new RuntimeException("Price cannot be negative or null");
        }
        if (product.getQuantity() <= 0) {
            throw new RuntimeException("Quantity cannot be negative or null");
        }
    }
}
