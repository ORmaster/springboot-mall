package com.jennyliu.springbootmall.Controller;

import com.jennyliu.springbootmall.Constant.ProductCategory;
import com.jennyliu.springbootmall.ProductService.ProductService;
import com.jennyliu.springbootmall.dto.ProductRequest;
import com.jennyliu.springbootmall.dto.QueryRequest;
import com.jennyliu.springbootmall.model.Product;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import java.util.List;

import static org.springframework.web.servlet.function.ServerResponse.status;

@RestController()
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/Product/{product_id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer product_id) {
        Product result = productService.getProductById(product_id);

        if (result == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
    }

    @PostMapping("/Product")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        Integer productId = productService.createProduct(productRequest);

        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/Product/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId, @RequestBody ProductRequest productRequest) {

        Product product = productService.getProductById(productId);

        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        productService.updateProduct(productId, productRequest);

        Product result = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("Product/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
        productService.deleteProduct(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("Products")
    public ResponseEntity<List<Product>> getProducts(
            // 查詢功能
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,
            // 排序功能
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "DESC") String sort) {

        QueryRequest queryRequest = new QueryRequest();
        queryRequest.setCategory(category);
        queryRequest.setSearch(search);
        queryRequest.setOrderBy(orderBy);
        queryRequest.setSort(sort);

        List<Product> products = productService.getProducts(queryRequest);

        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

}
