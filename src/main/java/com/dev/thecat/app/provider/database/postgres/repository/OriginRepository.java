package com.dev.thecat.app.provider.database.postgres.repository;

import com.dev.thecat.app.provider.database.postgres.table.OriginTable;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OriginRepository extends JpaRepository<OriginTable, UUID> {
  Optional<OriginTable> findByIntegrationId(String integrationId);
}
