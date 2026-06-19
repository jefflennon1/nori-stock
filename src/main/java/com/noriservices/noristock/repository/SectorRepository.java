package com.noriservices.noristock.repository;

import com.noriservices.noristock.model.SectorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SectorRepository extends JpaRepository<SectorModel, UUID> {
}

