package com.dev.thecat.app.provider.database.postgres;

import com.dev.thecat.app.provider.database.postgres.repository.BreedRepository;
import com.dev.thecat.app.provider.database.postgres.repository.BreedTemperamentRepository;
import com.dev.thecat.app.provider.database.postgres.table.BreedTable;
import com.dev.thecat.domain.breed.entity.BreedEntity;
import com.dev.thecat.domain.breed.provider.BreedGetAllProvider;
import com.dev.thecat.domain.breed.provider.BreedGetByIdProvider;
import com.dev.thecat.domain.breed.provider.BreedGetByIntegrationIdProvider;
import com.dev.thecat.domain.breed.provider.BreedGetByOriginIdProvider;
import com.dev.thecat.domain.breed.provider.BreedGetByTemperamentIdProvider;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.inject.Named;

@Named("breedPostgresGetProvider")
public class BreedPostgresGetProviderImpl implements
    BreedGetAllProvider,
    BreedGetByIdProvider,
    BreedGetByTemperamentIdProvider,
    BreedGetByOriginIdProvider,
    BreedGetByIntegrationIdProvider {

  private final BreedRepository breedRepository;
  private final BreedTemperamentRepository breedTemperamentRepository;

  public BreedPostgresGetProviderImpl(
      final BreedRepository breedRepository,
      final BreedTemperamentRepository breedTemperamentRepository) {
    this.breedRepository = breedRepository;
    this.breedTemperamentRepository = breedTemperamentRepository;
  }

  @Override
  public List<BreedEntity> getAll() {
    return this.breedRepository.findAll().stream().map(BreedTable::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public BreedEntity getById(UUID id) {
    var breed = this.breedRepository.findById(id);
    if (breed.isEmpty()) {
      return null;
    }
    return breed.get().toDomain();
  }

  @Override
  public BreedEntity getByIntegrationId(String integrationId) {
    var breed = this.breedRepository.findByIntegrationId(integrationId);
    if (breed.isEmpty()) {
      return null;
    }
    return breed.get().toDomain();
  }

  @Override
  public List<BreedEntity> getByOriginId(UUID id) {
    return this.breedRepository.findByOriginId(id).stream().map(BreedTable::toDomain)
        .collect(Collectors.toList());
  }

  @Override
  public List<BreedEntity> getByTemperamentId(UUID id) {
    return this.breedTemperamentRepository.findByTemperamentId(id).stream()
        .map(e -> e.getBreed().toDomain()).collect(Collectors.toList());
  }
}
