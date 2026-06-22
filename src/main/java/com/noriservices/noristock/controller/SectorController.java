package com.noriservices.noristock.controller;

import com.noriservices.noristock.model.DTO.SectorRequestDTO;
import com.noriservices.noristock.model.DTO.SectorResponseDTO;
import com.noriservices.noristock.service.SectorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("sectors")
public class SectorController {

    @Autowired
    private SectorService sectorService;

    @GetMapping
    public ResponseEntity<List<SectorResponseDTO>> findAll() {
        return ResponseEntity.ok(sectorService.findAll());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SectorResponseDTO> create(@RequestBody @Valid SectorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sectorService.create(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SectorResponseDTO> update(@PathVariable UUID id, @RequestBody @Valid SectorRequestDTO dto) {
        return ResponseEntity.ok(sectorService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deactivate(@PathVariable UUID id) {
        sectorService.deactivate(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> activate(@PathVariable UUID id) {
        sectorService.activate(id);
        return ResponseEntity.noContent().build();
    }
}
