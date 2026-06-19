package com.noriservices.noristock.model.DTO;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponseDTO(UUID id,
                                 String name,
                                 CategoryResponseDTO category,
                                 SectorResponseDTO sector,
                                 String description,
                                 String sku,
                                 int quantity,
                                 int minQuantity,
                                 boolean active,
                                 LocalDateTime createdAt,
                                 LocalDateTime updatedAt) {
}
