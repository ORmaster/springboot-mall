package com.jennyliu.springbootmall.Dao.Imp;

import com.jennyliu.springbootmall.Dao.ProductDao;
import com.jennyliu.springbootmall.Dao.ProductRowMapper;
import com.jennyliu.springbootmall.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDaoImp implements ProductDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    ProductRowMapper productRowMapper;

    private String queryProductSQL =
            "SELECT " +
                    "product_id, " +
                    "product_name, " +
                    "category, " +
                    "image_url, " +
                    "price, stock, " +
                    "description, " +
                    "created_date, " +
                    "last_modified_date " +
                    "FROM product " +
                    "WHERE product_id = :product_id";

    @Override
    public Product getProductById(Integer product_id) {

        Map<String, Object> parames = new HashMap<>();
        parames.put("product_id", product_id);

        List<Product> result = jdbcTemplate.query(queryProductSQL, parames, productRowMapper);

        if (!CollectionUtils.isEmpty(result)) {
            return result.get(0);
        }

        return null;

    }
}
