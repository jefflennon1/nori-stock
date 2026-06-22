package com.noriservices.noristock.controller;

import com.noriservices.noristock.model.DTO.StockMovementRequestDTO;
import com.noriservices.noristock.model.DTO.StockMovementResponseDTO;
import com.noriservices.noristock.service.StockMovementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("stock-movements")
public class StockMovementController {

    @Autowired
    private StockMovementService stockMovementService;

    @GetMapping
    public ResponseEntity<Page<StockMovementResponseDTO>> findAll(
            @PageableDefault(size = 10, sort = "createdAt") Pageable pageable) {
        return ResponseEntity.ok(stockMovementService.findAllPageable(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockMovementResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(stockMovementService.findDTOById(id));
    }

    @GetMapping("/by-product/{productId}")
    public ResponseEntity<List<StockMovementResponseDTO>> findByProduct(@PathVariable UUID productId) {
        return ResponseEntity.ok(stockMovementService.findByProduct(productId));
    }

    @GetMapping("/by-sector/{sectorId}")
    public ResponseEntity<List<StockMovementResponseDTO>> findBySector(@PathVariable UUID sectorId) {
        return ResponseEntity.ok(stockMovementService.findBySector(sectorId));
    }

    @PostMapping
    public ResponseEntity<StockMovementResponseDTO> create(@RequestBody @Valid StockMovementRequestDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ResponseEntity.status(HttpStatus.CREATED).body(stockMovementService.create(dto, username));
    }
}
