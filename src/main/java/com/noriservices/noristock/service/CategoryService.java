package com.noriservices.noristock.service;


import com.noriservices.noristock.exception.ResourceNotFoundException;
import com.noriservices.noristock.model.CategoryModel;
import com.noriservices.noristock.model.DTO.CategoryRequestDTO;
import com.noriservices.noristock.model.DTO.CategoryResponseDTO;
import com.noriservices.noristock.model.converter.CategoryConverter;
import com.noriservices.noristock.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public CategoryModel findEntityById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + id));
    }

    public List<CategoryResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(category -> new CategoryConverter().convertToResponseDTO(category))
                .toList();
    }

    public CategoryResponseDTO create(CategoryRequestDTO dto) {
        CategoryModel category = new CategoryConverter().convertToEntity(dto);
        repository.save(category);
        return new CategoryConverter().convertToResponseDTO(category);
    }

    public CategoryResponseDTO update(UUID id, CategoryRequestDTO dto) {
        CategoryModel category = findEntityById(id);
        category.setName(dto.name());
        category.setDescription(dto.description());
        repository.save(category);
        return new CategoryConverter().convertToResponseDTO(category);
    }

    public void deactivate(UUID id) {
        CategoryModel category = findEntityById(id);
        category.setActive(false);
        repository.save(category);
    }

    public void activate(UUID id) {
        CategoryModel category = findEntityById(id);
        category.setActive(true);
        repository.save(category);
    }
}