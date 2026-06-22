package com.noriservices.noristock.model.converter;

import com.noriservices.noristock.model.SectorModel;
import com.noriservices.noristock.model.DTO.SectorRequestDTO;
import com.noriservices.noristock.model.DTO.SectorResponseDTO;

public class SectorConverter {

    public SectorResponseDTO convertToResponseDTO(SectorModel sector) {
        return new SectorResponseDTO(
                sector.getId(),
                sector.getName(),
                sector.getDescription(),
                sector.getLocation(),
                sector.isActive(),
                sector.getCreatedAt(),
                sector.getUpdatedAt());
    }

    public SectorModel convertToEntity(SectorRequestDTO dto) {
        SectorModel sector = new SectorModel();
        sector.setName(dto.name());
        sector.setDescription(dto.description());
        sector.setLocation(dto.location());
        return sector;
    }
}