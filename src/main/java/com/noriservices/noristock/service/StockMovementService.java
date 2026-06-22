package com.noriservices.noristock.service;

import com.noriservices.noristock.exception.InsufficientStockException;
import com.noriservices.noristock.exception.ResourceNotFoundException;
import com.noriservices.noristock.model.*;
import com.noriservices.noristock.model.DTO.StockMovementRequestDTO;
import com.noriservices.noristock.model.DTO.StockMovementResponseDTO;
import com.noriservices.noristock.model.converter.StockMovementConverter;
import com.noriservices.noristock.repository.StockMovementRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class StockMovementService {

    @Autowired
    private StockMovementRepository repository;

    @Autowired
    private ProductService productService;

    @Autowired
    private SectorService sectorService;

    @Autowired
    private UserService userService;

    public boolean isStockMovementPresent(UUID orderId){
        return repository.findByOrderId(orderId).isPresent();
    }

    public void save(StockMovementModel movement) {
        repository.save(movement);
    }

    public List<StockMovementResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(movement -> new StockMovementConverter().convertToResponseDTO(movement))
                .toList();
    }

    public Page<StockMovementResponseDTO> findAllPageable(Pageable pageable) {
        return repository.findAll(pageable)
                .map(movement -> new StockMovementConverter().convertToResponseDTO(movement));
    }

    public List<StockMovementResponseDTO> findByProduct(UUID productId) {
        return repository.findByProductId(productId).stream()
                .map(movement -> new StockMovementConverter().convertToResponseDTO(movement))
                .toList();
    }

    public List<StockMovementResponseDTO> findBySector(UUID sectorId) {
        return repository.findBySectorId(sectorId).stream()
                .map(movement -> new StockMovementConverter().convertToResponseDTO(movement))
                .toList();
    }

    public StockMovementResponseDTO findDTOById(UUID id) {
        StockMovementModel movement = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock movement not found: " + id));
        return new StockMovementConverter().convertToResponseDTO(movement);
    }

    @Transactional
    public StockMovementResponseDTO create(StockMovementRequestDTO dto, String username) {
        ProductModel product = productService.findById(dto.productId());
        SectorModel sector = sectorService.findEntityById(dto.sectorId());
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new ResourceNotFoundException("User not found: " + username);
        }

        applyQuantityChange(product, dto.type(), dto.quantity());
        productService.save(product);

        StockMovementModel movement = new StockMovementModel();
        movement.setProduct(product);
        movement.setSector(sector);
        movement.setUser(user);
        movement.setType(dto.type());
        movement.setQuantity(dto.quantity());
        movement.setReason(dto.reason());
        repository.save(movement);

        return new StockMovementConverter().convertToResponseDTO(movement);
    }

    private void applyQuantityChange(ProductModel product, @NotNull(message = "type is required") MovementType type, int quantity) {
        switch (type) {
            case INBOUND -> product.setQuantity(product.getQuantity() + quantity);
            case OUTBOUND, ADJUSTMENT -> {
                int updated = product.getQuantity() - quantity;
                if (updated < 0) {
                    throw new InsufficientStockException(
                            "Insufficient stock for product " + product.getId() +
                                    ": current=" + product.getQuantity() + ", requested=" + quantity);
                }
                product.setQuantity(updated);
            }
        }
    }
}
