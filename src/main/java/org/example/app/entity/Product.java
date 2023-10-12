package org.example.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
public class Product {
    private long id;
    private String name;
    private double quota;
    private double price;


    public Product(String name, double quota, double price) {
        this.name = name;
        this.quota = quota;
        this.price = price;
    }

    public Product() {

    }
}
