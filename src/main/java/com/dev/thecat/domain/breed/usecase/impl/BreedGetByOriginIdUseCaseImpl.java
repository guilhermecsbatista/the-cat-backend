package com.dev.thecat.domain.breed.usecase.impl;

import com.dev.thecat.domain.breed.entity.BreedEntity;
import com.dev.thecat.domain.breed.provider.BreedGetByOriginIdProvider;
import com.dev.thecat.domain.breed.usecase.BreedGetByOriginIdUseCase;
import com.dev.thecat.domain.breed.usecase.exception.DataNotFoundException;
import java.util.List;
import java.util.UUID;
import javax.inject.Named;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;

@Log4j2
@Named
public class BreedGetByOriginIdUseCaseImpl implements BreedGetByOriginIdUseCase {

  private final BreedGetByOriginIdProvider breedGetByOriginIdProvider;

  public BreedGetByOriginIdUseCaseImpl(
      @Qualifier("breedPostgresGetProvider")
      final BreedGetByOriginIdProvider breedGetByOriginIdProvider) {
    this.breedGetByOriginIdProvider = breedGetByOriginIdProvider;
  }

  @Override
  public List<BreedEntity> execute(UUID id) {
    log.info("Getting breed by origin id: {}", id);
    var breeds = this.breedGetByOriginIdProvider.getByOriginId(id);

    if (breeds.isEmpty()) {
      log.warn("No breed record found");
      throw new DataNotFoundException("Breed not found");
    }

    return breeds;
  }
}
