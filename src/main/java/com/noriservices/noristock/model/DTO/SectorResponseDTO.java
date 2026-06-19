package com.noriservices.noristock.model.DTO;


import java.time.LocalDateTime;
import java.util.UUID;

public record SectorResponseDTO(UUID id,
                                String name,
                                String description,
                                String location,
                                boolean active,
                                LocalDateTime createdAt,
                                LocalDateTime updatedAt) {
}
