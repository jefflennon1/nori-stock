package com.noriservices.noristock.kafka.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrderConfirmedEventDTO(UUID orderId,
                                     UUID buyerId,
                                     List<OrderItemEventDTO> items,
                                     LocalDateTime confirmedAt) {
}

