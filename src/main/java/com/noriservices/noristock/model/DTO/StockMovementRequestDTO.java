package com.noriservices.noristock.model.DTO;

import com.noriservices.noristock.model.MovementType;

import java.util.UUID;

public record StockMovementRequestDTO(UUID productId,
                                      UUID sectorId,
                                      MovementType type,
                                      int quantity,
                                      String reason) {
}