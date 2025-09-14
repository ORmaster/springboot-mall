package com.jennyliu.springbootmall.ProductService.Imp;

import com.jennyliu.springbootmall.Dao.ProductDao;
import com.jennyliu.springbootmall.ProductService.ProductService;
import com.jennyliu.springbootmall.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    ProductDao productDao;

    public Product getProductById(Integer product_id) {
        return productDao.getProductById(product_id);
    }
}
