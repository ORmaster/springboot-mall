package com.jennyliu.springbootmall.Controller;

import com.jennyliu.springbootmall.ProductService.Imp.ProductServiceImp;
import com.jennyliu.springbootmall.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class ProductController {

    @Autowired
    private ProductServiceImp productServiceImp;

    @GetMapping("/Product/{product_id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer product_id) {
        Product result = productServiceImp.getProductById(product_id);

        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }
}
