package com.dev.thecat.domain.breed.usecase.impl;

import com.dev.thecat.domain.breed.entity.BreedEntity;
import com.dev.thecat.domain.breed.provider.BreedGetByIdProvider;
import com.dev.thecat.domain.breed.usecase.BreedGetByIdUseCase;
import com.dev.thecat.domain.breed.usecase.exception.DataNotFoundException;
import java.util.UUID;
import javax.inject.Named;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;

@Log4j2
@Named
public class BreedGetByIdUseCaseImpl implements BreedGetByIdUseCase {

  private final BreedGetByIdProvider breedGetByIdProvider;

  public BreedGetByIdUseCaseImpl(
      @Qualifier("breedPostgresGetProvider") final BreedGetByIdProvider breedGetByIdProvider) {
    this.breedGetByIdProvider = breedGetByIdProvider;
  }

  @Override
  public BreedEntity execute(UUID id) {
    log.info("Getting breed by id: {}", id);
    var breed = this.breedGetByIdProvider.getById(id);

    if (breed == null) {
      log.warn("No breed record found");
      throw new DataNotFoundException("Breed not found");
    }

    return breed;
  }
}
