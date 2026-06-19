package com.noriservices.noristock.service;

import com.noriservices.noristock.model.StockMovementModel;
import com.noriservices.noristock.repository.StockMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StockMovementService {

    @Autowired
    private StockMovementRepository repository;

    public boolean isStockMovementPresent(UUID orderId){
        return repository.findByOrderId(orderId).isPresent();
    }

    public void save(StockMovementModel movement) {
        repository.save(movement);
    }
}
