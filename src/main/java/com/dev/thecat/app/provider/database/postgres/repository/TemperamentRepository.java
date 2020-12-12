package com.dev.thecat.app.provider.database.postgres.repository;

import com.dev.thecat.app.provider.database.postgres.table.TemperamentTable;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperamentRepository extends JpaRepository<TemperamentTable, UUID> {
  Optional<TemperamentTable> findByIntegrationId(String integrationId);
}
