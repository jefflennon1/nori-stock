package com.noriservices.noristock.model.converter;

import com.noriservices.noristock.model.CategoryModel;
import com.noriservices.noristock.model.DTO.CategoryRequestDTO;
import com.noriservices.noristock.model.DTO.CategoryResponseDTO;

public class CategoryConverter {

    public CategoryResponseDTO convertToResponseDTO(CategoryModel category) {
        return new CategoryResponseDTO(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.isActive(),
                category.getCreatedAt(),
                category.getUpdatedAt());
    }

    public CategoryModel convertToEntity(CategoryRequestDTO dto) {
        CategoryModel category = new CategoryModel();
        category.setName(dto.name());
        category.setDescription(dto.description());
        return category;
    }
}