package com.jennyliu.springbootmall.Dao;

import com.jennyliu.springbootmall.model.Product;

public interface ProductDao {
    Product getProductById(Integer product_id);
}
