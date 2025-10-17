package com.jennyliu.springbootmall.Dao;

import com.jennyliu.springbootmall.dto.ProductRequest;
import com.jennyliu.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer product_id);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer product_id,ProductRequest productRequest);
}
