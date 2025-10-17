package com.jennyliu.springbootmall.ProductService.Imp;

import com.jennyliu.springbootmall.Dao.ProductDao;
import com.jennyliu.springbootmall.ProductService.ProductService;
import com.jennyliu.springbootmall.dto.ProductRequest;
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

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        Integer productId = productDao.createProduct(productRequest);

        return productId;
    }

    @Override
    public void updateProduct(Integer product_id, ProductRequest productRequest) {
        productDao.updateProduct(product_id, productRequest);


    }
}
