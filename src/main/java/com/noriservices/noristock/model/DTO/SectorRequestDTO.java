package com.noriservices.noristock.model.DTO;

import jakarta.validation.constraints.NotBlank;

public record SectorRequestDTO(@NotBlank(message = "name is required") String name, String description, String location) {
}
