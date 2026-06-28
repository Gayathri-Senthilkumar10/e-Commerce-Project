package com.ecom.gayathricart.service;

import com.ecom.gayathricart.model.Product;
import com.ecom.gayathricart.model.dto.CatResponse;
import com.ecom.gayathricart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<Product> allProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> findProductById(Integer id){
        return productRepository.findById(id);
    }


    public boolean deleteByid(Integer id) {
        productRepository.deleteById(id);
        return  true;
    }

    public Product saveProduct(Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImage(image.getBytes());
        return  productRepository.save(product);
    }

    public Product updateProduct(Product existingProduct,Product product, MultipartFile image) throws IOException {
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setReleaseDate(product.getReleaseDate());
        existingProduct.setAvailable(product.isAvailable());
        existingProduct.setStockQuantity(product.getStockQuantity());
        if(image!=null){
            existingProduct.setImageName(image.getOriginalFilename());
            existingProduct.setImageType(image.getContentType());
            existingProduct.setImage(image.getBytes());
        }
        return productRepository.save(existingProduct);
    }

    public List<Product> findProductByKeyword(String keyword) {
        List<Product> products = productRepository.findByKeyword(keyword);
        return products;
    }

    public List<CatResponse> findAllCategories() {
        List<CatResponse> categories = new ArrayList<>();
        List<String> allProductsCat = productRepository.findAllCategories();
        for (String cat : allProductsCat) {
            categories.add(
                    new CatResponse(
                            UUID.randomUUID(),
                            cat
                    )
            );
        }
        return categories;
    }
}
