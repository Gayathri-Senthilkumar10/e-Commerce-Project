package com.ecom.gayathricart.service;

import com.ecom.gayathricart.model.Product;
import com.ecom.gayathricart.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProjectRepository projectRepository;

    public List<Product> allProducts() {
        return projectRepository.findAll();
    }
}
