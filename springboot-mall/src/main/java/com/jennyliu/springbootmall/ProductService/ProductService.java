package com.jennyliu.springbootmall.ProductService;

import com.jennyliu.springbootmall.dto.ProductRequest;
import com.jennyliu.springbootmall.model.Product;

public interface ProductService {
    Product getProductById(Integer product_id);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer product_id, ProductRequest productRequest);
}
