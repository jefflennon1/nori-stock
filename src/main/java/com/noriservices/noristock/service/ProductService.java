package com.noriservices.noristock.service;


import com.noriservices.noristock.model.ProductModel;
import com.noriservices.noristock.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public ProductModel findById(UUID id){
        return repository.findById(id).orElseThrow();
    }

    public ProductModel save(ProductModel entity){
        return repository.save(entity);
    }
}
