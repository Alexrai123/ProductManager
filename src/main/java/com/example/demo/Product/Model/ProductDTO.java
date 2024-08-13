package com.example.demo.Product.Model;

import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private String description;
    private Double price;

    public ProductDTO(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
    }

    public ProductDTO(String name, String description, Double price){
        this.name = name;
        this.description = description;
        this.price = price;
    }
}