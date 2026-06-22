package com.noriservices.noristock.service;


import com.noriservices.noristock.exception.ResourceNotFoundException;
import com.noriservices.noristock.model.CategoryModel;
import com.noriservices.noristock.model.DTO.ProductRequestDTO;
import com.noriservices.noristock.model.DTO.ProductResponseDTO;
import com.noriservices.noristock.model.ProductModel;
import com.noriservices.noristock.model.SectorModel;
import com.noriservices.noristock.model.converter.ProductConverter;
import com.noriservices.noristock.repository.ProductRepository;
import com.noriservices.noristock.repository.StockMovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SectorService sectorService;

    public ProductModel findById(UUID id){
        return repository.findById(id).orElseThrow(()->new ResourceNotFoundException("Product not found: " + id));
    }

    public void save(ProductModel entity){
        repository.save(entity);
    }

    public List<ProductResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(product -> new ProductConverter().convertToResponseDTO(product))
                .toList();
    }

    public Page<ProductResponseDTO> findAllPageable(Pageable pageable) {
        return repository.findAll(pageable)
                .map(product -> new ProductConverter().convertToResponseDTO(product));
    }

    public ProductResponseDTO create(ProductRequestDTO dto) {
        CategoryModel category = categoryService.findEntityById(dto.categoryId());
        SectorModel sector = sectorService.findEntityById(dto.sectorId());

        ProductModel product = new ProductConverter().convertToEntity(dto);
        product.setCategory(category);
        product.setSector(sector);

        repository.save(product);
        return new ProductConverter().convertToResponseDTO(product);
    }

    public ProductResponseDTO update(UUID id, ProductRequestDTO dto) {
        ProductModel product = findById(id);
        CategoryModel category = categoryService.findEntityById(dto.categoryId());
        SectorModel sector = sectorService.findEntityById(dto.sectorId());

        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setSku(dto.sku());
        product.setQuantity(dto.quantity());
        product.setMinQuantity(dto.minQuantity());
        product.setCategory(category);
        product.setSector(sector);

        repository.save(product);
        return new ProductConverter().convertToResponseDTO(product);
    }

    public void deactivate(UUID id) {
        ProductModel product = findById(id);
        product.setActive(false);
        repository.save(product);
    }

    public void activate(UUID id) {
        ProductModel product = findById(id);
        product.setActive(true);
        repository.save(product);
    }

    public ProductResponseDTO findDTOById(UUID id) {
       ProductModel entity = findById(id);
       return new ProductConverter().convertToResponseDTO(entity);
    }
}
