package com.ecom.gayathricart.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String brand;
    private BigDecimal price;
    private String category;
    private Date releaseDate;
    private boolean available;
    private int stockQuantity;

    public Product(int id, String name, String description, String brand, String category, BigDecimal price, Date releaseDate, boolean available, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.releaseDate = releaseDate;
        this.available = available;
        this.stockQuantity = stockQuantity;
    }

    private String imageName;
    private String imageType;

    @Lob
    private byte[] image;
}
