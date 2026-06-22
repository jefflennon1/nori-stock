package com.noriservices.noristock.model.DTO;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;


public record ProductRequestDTO(
        @NotBlank(message = "name is required") String name,
        @NotNull(message = "categoryId is required") UUID categoryId,
        @NotNull(message = "sectorId is required") UUID sectorId,
        String description,
        String sku,
        @PositiveOrZero(message = "quantity must be zero or positive") int quantity,
        @PositiveOrZero(message = "minQuantity must be zero or positive") int minQuantity) {
}