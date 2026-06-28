package com.ecom.gayathricart.controller;

import com.ecom.gayathricart.model.Product;
import com.ecom.gayathricart.model.dto.CatResponse;
import com.ecom.gayathricart.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class ProductController {
    private final ProductService  productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.allProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
        Product fetchedProduct =productService.findProductById(id).orElse(null);
        if(fetchedProduct != null){
            return new ResponseEntity<>(fetchedProduct, HttpStatus.OK);
        }
        HashMap<String,String> map = new HashMap<>();
        map.put("message", "Error while fetching product using ID -> "+ id);
        map.put("code", "404");
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Integer id) {
        Product fetchedProduct =productService.findProductById(id).orElse(null);
        HashMap<String,String> map = new HashMap<>();
        if(fetchedProduct != null){
            map.put("success", "Product Deleted ID : -> "+ id);
            productService.deleteByid(id);
            return new ResponseEntity<>(map, HttpStatus.OK);
        }
        map.put("message", "Error while Deleting the product using ID -> "+ id + " Please check weather the product is exists or not.....");
        map.put("code", "404");
        return  new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<?> saveProduct(@RequestPart Product product, MultipartFile image) {
        try{
            Product savedProduct = productService.saveProduct(product,image);
            return new ResponseEntity<>(savedProduct, HttpStatus.OK);
        }catch(Exception e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product/{id}/image")
    public ResponseEntity<?> getProductImage(@PathVariable Integer id) {
        Product fetchedProduct =productService.findProductById(id).orElse(null);
        HashMap<String,String> map = new HashMap<>();
        if(fetchedProduct != null){
            byte[] image = fetchedProduct.getImage();
            return  new ResponseEntity<>(image, HttpStatus.OK);
        }
        map.put("message", "Error while Fetching the image for the product ID -> "+ id + " Please check weather the product is exists or not.....");
        map.put("code", "404");
        return  new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id,
                                           @RequestPart Product product, MultipartFile image) {
//        Check weather the product exists or no...
        Product existingProduct = productService.findProductById(id).orElse(null); // DB -> Existing Data
        if(existingProduct == null){
            HashMap<String,Object> response = new HashMap<>();
            response.put("message", "Error while fetching product using ID -> "+ id);
            response.put("code", "404");
            response.put("timestamp", System.currentTimeMillis());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
//        Update the Product
        try {
            Product updatedProduct = productService.updateProduct(existingProduct,product,image);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/products/search")
    public ResponseEntity<?> getProductsByName(@RequestParam String keyword){
        List<Product> products = productService.findProductByKeyword(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products/all-categories")
    public ResponseEntity<?> getAllCategories(){
        List<CatResponse> categories = productService.findAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}