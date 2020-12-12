package com.dev.thecat.app.provider.database.postgres.repository;

import com.dev.thecat.app.provider.database.postgres.table.ImageTable;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageTable, UUID> {
  Optional<ImageTable> findByIntegrationId(String integrationId);
}
