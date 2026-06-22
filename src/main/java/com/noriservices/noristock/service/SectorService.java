package com.noriservices.noristock.service;

import com.noriservices.noristock.exception.ResourceNotFoundException;
import com.noriservices.noristock.model.SectorModel;
import com.noriservices.noristock.model.DTO.SectorRequestDTO;
import com.noriservices.noristock.model.DTO.SectorResponseDTO;
import com.noriservices.noristock.model.converter.SectorConverter;
import com.noriservices.noristock.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SectorService {

    @Autowired
    private SectorRepository repository;

    public SectorModel findEntityById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sector not found: " + id));
    }

    public List<SectorResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(sector -> new SectorConverter().convertToResponseDTO(sector))
                .toList();
    }

    public SectorResponseDTO create(SectorRequestDTO dto) {
        SectorModel sector = new SectorConverter().convertToEntity(dto);
        repository.save(sector);
        return new SectorConverter().convertToResponseDTO(sector);
    }

    public SectorResponseDTO update(UUID id, SectorRequestDTO dto) {
        SectorModel sector = findEntityById(id);
        sector.setName(dto.name());
        sector.setDescription(dto.description());
        sector.setLocation(dto.location());
        repository.save(sector);
        return new SectorConverter().convertToResponseDTO(sector);
    }

    public void deactivate(UUID id) {
        SectorModel sector = findEntityById(id);
        sector.setActive(false);
        repository.save(sector);
    }

    public void activate(UUID id) {
        SectorModel sector = findEntityById(id);
        sector.setActive(true);
        repository.save(sector);
    }
}
