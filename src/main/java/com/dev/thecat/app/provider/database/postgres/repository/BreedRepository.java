package com.dev.thecat.app.provider.database.postgres.repository;

import com.dev.thecat.app.provider.database.postgres.table.BreedTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BreedRepository extends JpaRepository<BreedTable, UUID> {

    Optional<BreedTable> findByIntegrationId(String integrationId);

    List<BreedTable> findByOriginId(UUID originUuid);
}