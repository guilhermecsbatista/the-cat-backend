package com.dev.thecat.app.provider.database.postgres.repository;

import com.dev.thecat.app.provider.database.postgres.table.BreedTemperamentTable;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreedTemperamentRepository extends JpaRepository<BreedTemperamentTable, UUID> {
  List<BreedTemperamentTable> findByTemperamentId(UUID temperamentId);
}
