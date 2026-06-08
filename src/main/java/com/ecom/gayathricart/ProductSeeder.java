package com.ecom.gayathricart;

import com.ecom.gayathricart.model.Product;
import com.ecom.gayathricart.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ProductSeeder implements CommandLineRunner {

    ProjectRepository projectRepository;
    @Autowired
    public ProductSeeder(ProjectRepository projectRepository) {}

    @Override
    public void run(String... args) throws Exception {
        if (projectRepository.count() == 0) {
            List<Product> dummyData = new ArrayList<Product>();
                List.of(
                        new Product(
                                0,
                                "iPhone 15",
                                "Apple smartphone with A16 Bionic chip",
                                "Apple",
                                "Electronics",
                                new BigDecimal("79999.00"),
                                new Date(),
                                true,
                                50
                        ),

                        new Product(
                                0,
                                "Galaxy S24",
                                "Samsung flagship smartphone",
                                "Samsung",
                                "Electronics",
                                new BigDecimal("74999.00"),
                                new Date(),
                                true,
                                40
                        ),

                        new Product(
                                0,
                                "MacBook Air M3",
                                "Lightweight laptop with Apple M3 chip",
                                "Apple",
                                "Laptops",
                                new BigDecimal("114999.00"),
                                new Date(),
                                true,
                                20
                        ),

                        new Product(
                                0,
                                "Sony WH-1000XM5",
                                "Noise-cancelling wireless headphones",
                                "Sony",
                                "Accessories",
                                new BigDecimal("29999.00"),
                                new Date(),
                                true,
                                75
                        ),

                        new Product(
                                0,
                                "Dell XPS 13",
                                "Premium ultrabook laptop",
                                "Dell",
                                "Laptops",
                                new BigDecimal("99999.00"),
                                new Date(),
                                false,
                                0
                        )

                );

            projectRepository.saveAll(dummyData);
            System.out.println("Dummy data saved");

        }else  {
            System.out.println("Project in DB");
        }
    }

    }