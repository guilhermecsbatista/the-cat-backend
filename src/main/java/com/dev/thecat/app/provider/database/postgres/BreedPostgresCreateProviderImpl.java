package com.dev.thecat.app.provider.database.postgres;

import com.dev.thecat.app.provider.database.postgres.repository.BreedRepository;
import com.dev.thecat.app.provider.database.postgres.table.BreedTable;
import com.dev.thecat.domain.breed.entity.BreedEntity;
import com.dev.thecat.domain.breed.provider.BreedCreateProvider;
import javax.inject.Named;

@Named("breedPostgresCreateProvider")
public class BreedPostgresCreateProviderImpl implements BreedCreateProvider {

  private final BreedRepository breedRepository;

  public BreedPostgresCreateProviderImpl(final BreedRepository breedRepository) {
    this.breedRepository = breedRepository;
  }

  @Override
  public void create(BreedEntity breed) {
    this.breedRepository.save(new BreedTable().fromDomain(breed));
  }
}
