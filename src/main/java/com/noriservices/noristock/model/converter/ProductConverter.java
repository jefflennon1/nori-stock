package com.noriservices.noristock.model.converter;

import com.noriservices.noristock.model.ProductModel;
import com.noriservices.noristock.model.DTO.ProductRequestDTO;
import com.noriservices.noristock.model.DTO.ProductResponseDTO;

public class ProductConverter {

    public ProductResponseDTO convertToResponseDTO(ProductModel product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                new CategoryConverter().convertToResponseDTO(product.getCategory()),
                new SectorConverter().convertToResponseDTO(product.getSector()),
                product.getDescription(),
                product.getSku(),
                product.getQuantity(),
                product.getMinQuantity(),
                product.isActive(),
                product.getCreatedAt(),
                product.getUpdatedAt());
    }

    public ProductModel convertToEntity(ProductRequestDTO dto) {
        ProductModel product = new ProductModel();
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setSku(dto.sku());
        product.setQuantity(dto.quantity());
        product.setMinQuantity(dto.minQuantity());
        return product;
    }
}
