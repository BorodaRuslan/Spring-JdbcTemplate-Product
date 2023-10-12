package org.example.app.service.impl;

import org.example.app.entity.Product;
import org.example.app.exceptions.ProductDataException;
import org.example.app.repository.impl.ProductRepositoryImpl;
import org.example.app.service.BaseService;
import org.example.app.utils.Constans;
import org.example.app.utils.IdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service("productService")
public class ProductServiceImpl implements BaseService<Product> {
    @Autowired
    Product product;
    @Autowired
    ProductRepositoryImpl repoIml;

    Map<String, String> errors = new HashMap<>();

    @Override
    public String create(Product product) {
        validatorData(product);
        if (!errors.isEmpty()){
            try {
                throw new ProductDataException("Check inputs", errors);
            } catch (ProductDataException e){
                return e.getErrors(errors);
            }
        }
        if (repoIml.create(product)) {
            return Constans.DATA_INSERT_MSG;
        } else {
            return Constans.SMTH_WRONG_MSG;
        }
    }

    @Override
    public String getAll() {
        Optional<List<Product>> options = repoIml.getAll();
        if (options.isPresent()){
            AtomicInteger count = new AtomicInteger(0);
            StringBuilder stringBuilder = new StringBuilder();

            List<Product> list = options.get();
            list.forEach(product1 -> {
                stringBuilder.append(count.incrementAndGet())
                        .append(") ")
                        .append(product1.toString());
            });
            return stringBuilder.toString();
        } else return Constans.DATA_ABSENT_MSG;
    }

    @Override
    public String getById(String id) {
        validateId(id);
        if (!errors.isEmpty()){
            try {
                throw new ProductDataException("Check inputs", errors);
            } catch (ProductDataException e){
                return e.getErrors(errors);
            }
        }
        Optional<Product> optional = repoIml.getById((long) Integer.parseInt(id));
        if (optional.isEmpty()) {
            return Constans.DATA_ABSENT_MSG;
        } else {
            Product product = optional.get();
            return product.toString();
        }
    }

    @Override
    public String update(Product product) {
        validatorData(product);
        validateId(String.valueOf(product.getId()));
        if (!errors.isEmpty()){
            try {
                throw new ProductDataException("Check inputs", errors);
            } catch (ProductDataException e){
                return e.getErrors(errors);
            }
        }
        if (repoIml.update(product)){
            return Constans.DATA_UPDATE_MSG;
        } else {
            return Constans.SMTH_WRONG_MSG;
        }
    }

    @Override
    public String delete(String id) {
        validateId(id);
        if (!errors.isEmpty()){
            try {
                throw new ProductDataException("Check inputs", errors);
            } catch (ProductDataException e){
                return e.getErrors(errors);
            }
        }
        product.setId(Long.parseLong(id));
        if (repoIml.delete(product)){
            return Constans.DATA_DELETE_MSG;
        } else {
            return Constans.SMTH_WRONG_MSG;
        }
    }


    private void validatorData(Product product){
        if (product.getName().isEmpty())
            errors.put("name", Constans.INPUT_REQ_MSG);
        if (product.getQuota() < 0)
            errors.put("quota", Constans.INCORRECT_VALUE_MSG);
        if (product.getPrice() < 0)
            errors.put("price", Constans.INCORRECT_VALUE_MSG);

    }

    private void validateId(String id){
        if (IdValidator.isIdValid(id))
            errors.put("id", Constans.ID_ERR_MSG);

    }
}
