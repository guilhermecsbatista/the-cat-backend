package com.dev.thecat.domain.breed.usecase.impl;

import com.dev.thecat.domain.breed.entity.BreedEntity;
import com.dev.thecat.domain.breed.provider.BreedGetByTemperamentIdProvider;
import com.dev.thecat.domain.breed.usecase.BreedGetByTemperamentIdUseCase;
import com.dev.thecat.domain.breed.usecase.exception.DataNotFoundException;
import java.util.List;
import java.util.UUID;
import javax.inject.Named;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;

@Log4j2
@Named
public class BreedGetByTemperamentIdUseCaseImpl implements BreedGetByTemperamentIdUseCase {

  private final BreedGetByTemperamentIdProvider breedGetByTemperamentIdProvider;

  public BreedGetByTemperamentIdUseCaseImpl(
      @Qualifier("breedPostgresGetProvider")
      final BreedGetByTemperamentIdProvider breedGetByTemperamentIdProvider) {
    this.breedGetByTemperamentIdProvider = breedGetByTemperamentIdProvider;
  }

  @Override
  public List<BreedEntity> execute(UUID id) {
    log.info("Getting breed by temperament id: {}", id);
    var breeds = this.breedGetByTemperamentIdProvider.getByTemperamentId(id);

    if (breeds.isEmpty()) {
      log.warn("No breed record found");
      throw new DataNotFoundException("Breed not found");
    }

    return breeds;
  }
}
