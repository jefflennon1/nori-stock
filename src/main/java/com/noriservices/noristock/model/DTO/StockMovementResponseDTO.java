package com.noriservices.noristock.model.DTO;

import com.noriservices.noristock.model.MovementType;
import java.time.LocalDateTime;
import java.util.UUID;

public record StockMovementResponseDTO(UUID id,
                                       ProductResponseDTO product,
                                       SectorResponseDTO sector,
                                       ResponseUserDTO user,
                                       MovementType type,
                                       int quantity,
                                       String reason,
                                       UUID orderId,
                                       LocalDateTime createdAt) {
}

