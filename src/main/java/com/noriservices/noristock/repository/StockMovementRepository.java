package com.noriservices.noristock.repository;

import com.noriservices.noristock.model.StockMovementModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StockMovementRepository extends JpaRepository<StockMovementModel, UUID> {

    List<StockMovementModel> findByProductId(UUID productId);

    List<StockMovementModel> findBySectorId(UUID sectorId);


    Optional<StockMovementModel> findByOrderId(UUID orderId);
}