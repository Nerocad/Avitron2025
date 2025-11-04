package com.nerocad.spring.service;

import com.nerocad.spring.dao.ProductDao;
import com.nerocad.spring.dao.ProductDaoImpl;
import com.nerocad.spring.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductDaoImpl productDaoImpl;

    public ProductService(ProductDaoImpl productDaoImpl) {
        this.productDaoImpl = productDaoImpl;
    }

    @Transactional
    public void addProduct(Product product) {
        productDaoImpl.save(product);
    }
}
