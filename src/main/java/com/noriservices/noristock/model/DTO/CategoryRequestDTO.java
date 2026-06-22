package com.noriservices.noristock.model.DTO;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(@NotBlank(message = "name is required") String name, String description) {
}
