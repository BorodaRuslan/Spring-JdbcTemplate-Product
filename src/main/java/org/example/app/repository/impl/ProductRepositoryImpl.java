package org.example.app.repository.impl;

import org.example.app.entity.Product;
import org.example.app.entity.ProductMapper;
import org.example.app.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository("productRepository")
public class ProductRepositoryImpl implements BaseRepository<Product> {

    /*
    NamedParameterJdbcTemplate - это один из классов, предоставляемых
    Spring Framework для упрощения доступа к базам данных с использованием
    JDBC (Java Database Connectivity).
    Этот класс представляет собой усовершенствованную версию JdbcTemplate,
    который позволяет выполнять SQL-запросы, используя именованные параметры вместо позиционных параметров.
    Это делает код более читаемым и облегчает поддержку SQL-запросов.

    */
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
   public ProductRepositoryImpl(DataSource dataSource){
       namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
   }


    @Override
    public boolean create(Product product) {
        String sql = "INSERT INTO products (name, quota, price) " +
                "VALUES (:name, :quota, :price)";
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", product.getName())
                .addValue("quota", product.getQuota())
                .addValue("price", product.getPrice());

        return namedParameterJdbcTemplate.update(sql, parameterSource) > 0;

    }

    @Override
    public Optional<List<Product>> getAll() {
        String sql= "SELECT * FROM products";
        Optional<List<Product>> optional;

        try {
            optional = Optional.of(namedParameterJdbcTemplate.query(sql, new ProductMapper()));
        } catch (Exception e){
            optional = Optional.empty();
        }
        return optional;
    }

    @Override
    public Optional<Product> getById(Long id) {
        String sql = "SELECT * FROM products WHERE id = :id LIMIT 1";
        SqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        Optional<Product> optional;

        try {
            optional = Optional.ofNullable(namedParameterJdbcTemplate
                    .queryForObject(sql, parameterSource, Product.class));
        } catch (Exception e){
            optional = Optional.empty();
        }
        return optional;
    }



    @Override
    public boolean update(Product product) {
        Optional<Product> optional = getById(product.getId());
        if (optional.isEmpty()) {
            return false;
        } else {
            String sql = "UPDATE products SET name = :name, quota = :quota, " +
                    "price = :price WHERE id = :id";
            SqlParameterSource parameterSource = new MapSqlParameterSource()
                    .addValue("name", product.getName())
                    .addValue("quota", product.getQuota())
                    .addValue("price", product.getPrice());
            return namedParameterJdbcTemplate.update(sql, parameterSource) > 0;
        }
    }

    @Override
    public boolean delete(Product product) {
        Optional<Product> optional = getById(product.getId());
        if (optional.isEmpty()){
            return false;
        } else {
            String sql = "DELETE FROM products WHERE id = :id";
            SqlParameterSource parameterSource = new MapSqlParameterSource("id", product.getId());
            return namedParameterJdbcTemplate.update(sql, parameterSource) > 0;
        }
    }
}
