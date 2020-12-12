package com.dev.thecat.app.provider.database.postgres.repository;

import com.dev.thecat.app.provider.database.postgres.table.BreedTable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreedRepository extends JpaRepository<BreedTable, UUID> {

  Optional<BreedTable> findByIntegrationId(String integrationId);

  List<BreedTable> findByOriginId(UUID originUuid);
}
