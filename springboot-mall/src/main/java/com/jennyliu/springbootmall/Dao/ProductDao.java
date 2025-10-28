package com.jennyliu.springbootmall.Dao;

import com.jennyliu.springbootmall.Constant.ProductCategory;
import com.jennyliu.springbootmall.dto.ProductRequest;
import com.jennyliu.springbootmall.dto.QueryRequest;
import com.jennyliu.springbootmall.model.Product;

import java.util.List;

public interface ProductDao {
    Product getProductById(Integer product_id);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer product_id,ProductRequest productRequest);

    void deleteProduct(Integer product_id);

    List<Product> getProducts(QueryRequest queryRequest);
}
