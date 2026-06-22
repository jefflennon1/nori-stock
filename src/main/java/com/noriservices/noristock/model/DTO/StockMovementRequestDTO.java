package com.noriservices.noristock.model.DTO;

import com.noriservices.noristock.model.MovementType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record StockMovementRequestDTO(
        @NotNull(message = "productId is required") UUID productId,
        @NotNull(message = "sectorId is required") UUID sectorId,
        @NotNull(message = "type is required") MovementType type,
        @Positive(message = "quantity must be greater than zero") int quantity,
        String reason) {
}