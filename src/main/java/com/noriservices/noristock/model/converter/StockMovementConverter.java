package com.noriservices.noristock.model.converter;

import com.noriservices.noristock.model.StockMovementModel;
import com.noriservices.noristock.model.DTO.StockMovementResponseDTO;

public class StockMovementConverter {

    public StockMovementResponseDTO convertToResponseDTO(StockMovementModel movement) {
        return new StockMovementResponseDTO(
                movement.getId(),
                new ProductConverter().convertToResponseDTO(movement.getProduct()),
                new SectorConverter().convertToResponseDTO(movement.getSector()),
                new UserConverter().convertToResponseUserDTO(movement.getUser()),
                movement.getType(),
                movement.getQuantity(),
                movement.getReason(),
                movement.getOrderId(),
                movement.getCreatedAt());
    }
}