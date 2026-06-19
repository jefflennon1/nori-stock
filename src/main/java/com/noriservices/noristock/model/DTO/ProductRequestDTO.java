package com.noriservices.noristock.model.DTO;

import java.util.UUID;

public record ProductRequestDTO(String name,
                                UUID categoryId,
                                UUID sectorId,
                                String description,
                                String sku,
                                int quantity,
                                int minQuantity) {
}