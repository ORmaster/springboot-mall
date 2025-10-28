package com.jennyliu.springbootmall.Dao.Imp;

import com.jennyliu.springbootmall.Constant.ProductCategory;
import com.jennyliu.springbootmall.Dao.ProductDao;
import com.jennyliu.springbootmall.Dao.ProductRowMapper;
import com.jennyliu.springbootmall.dto.ProductRequest;
import com.jennyliu.springbootmall.dto.QueryRequest;
import com.jennyliu.springbootmall.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.management.Query;
import java.math.BigInteger;
import java.util.Date;
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

    private String createProductSQL =
            "INSERT INTO " +
                    "product (product_name, category, image_url, price, stock, description, created_date, last_modified_date)" +
                    "VALUES (:product_name, :category, :image_url, :price, :stock, :description, :created_date, :last_modified_date)";

    private String updateProductSQL =
            "UPDATE product " +
                    "SET " +
                    "product_name = :product_name," +
                    "category= :category," +
                    "image_url=:image_url," +
                    "price=:price," +
                    "stock=:stock," +
                    "description=:description," +
                    "last_modified_date=:last_modified_date" +
                    " WHERE product_id = :product_id";

    private final String deleteProductSQL =
            "DELETE FROM product " +
                    "WHERE 1=1 " +
                    "AND  product_id = :product_id";


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

    @Override
    public Integer createProduct(ProductRequest productRequest) {

        Map<String, Object> params = new HashMap<>();
        params.put("product_name", productRequest.getProduct_name());
        params.put("category", productRequest.getCategory().name());
        params.put("image_url", productRequest.getImage_url());
        params.put("price", productRequest.getPrice());
        params.put("stock", productRequest.getStock());
        params.put("description", productRequest.getDescription());
        params.put("created_date", new Date());
        params.put("last_modified_date", new Date());

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(createProductSQL, new MapSqlParameterSource(params), keyHolder);

        Integer productId = keyHolder.getKey().intValue();

        return productId;
    }

    @Override
    public void updateProduct(Integer product_id, ProductRequest productRequest) {
        Map<String, Object> params = new HashMap<>();
        params.put("product_name", productRequest.getProduct_name());
        params.put("category", productRequest.getCategory().name());
        params.put("image_url", productRequest.getImage_url());
        params.put("price", productRequest.getPrice());
        params.put("stock", productRequest.getStock());
        params.put("description", productRequest.getDescription());
        params.put("last_modified_date", new Date());
        params.put("product_id", product_id);


        jdbcTemplate.update(updateProductSQL, new MapSqlParameterSource(params));


    }

    public void deleteProduct(Integer product_id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("product_id", product_id);

        jdbcTemplate.update(deleteProductSQL, parameterSource);
    }

    @Override
    public List<Product> getProducts(QueryRequest queryRequest) {

        String querySQL = "SELECT " +
                "product_id, " +
                "product_name, " +
                "category, " +
                "image_url, " +
                "price," +
                "stock, " +
                "description, " +
                "created_date, " +
                "last_modified_date " +
                "FROM product " +
                "WHERE 1=1 ";

        Map<String, Object> paramMap = new HashMap<>();

        if (queryRequest.getCategory() != null) {
            String categorySql = "";
            categorySql = "AND category = " + ":category ";
            paramMap.put("category",queryRequest.getCategory().name());

            querySQL = querySQL + categorySql;
        }

        if(queryRequest.getSearch() !=null){
            String searchSql="";
            searchSql="AND product_name LIKE " + ":search ";
            paramMap.put("search", "%" + queryRequest.getSearch() + "%");

            querySQL = querySQL + searchSql;
        }

        querySQL = querySQL + " ORDER BY " + queryRequest.getOrderBy();

        querySQL = querySQL + " " + queryRequest.getSort();

        List<Product> products = jdbcTemplate.query(querySQL, paramMap, productRowMapper);

        return products;
    }
}
