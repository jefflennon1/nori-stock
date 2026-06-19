package com.noriservices.noristock.kafka.dto;

import java.util.UUID;

public record OrderItemEventDTO(UUID productId, int quantity) {
}
