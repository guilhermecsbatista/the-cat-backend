package com.dev.thecat.domain.breed.usecase.impl;

import com.dev.thecat.domain.breed.entity.BreedEntity;
import com.dev.thecat.domain.breed.provider.BreedGetAllProvider;
import com.dev.thecat.domain.breed.usecase.BreedGetAllUseCase;
import com.dev.thecat.domain.breed.usecase.exception.DataNotFoundException;
import java.util.List;
import javax.inject.Named;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;

@Log4j2
@Named
public class BreedGetAllUseCaseImpl implements BreedGetAllUseCase {

  private final BreedGetAllProvider breedGetAllProvider;

  public BreedGetAllUseCaseImpl(
      @Qualifier("breedPostgresGetProvider") final BreedGetAllProvider breedGetAllProvider) {
    this.breedGetAllProvider = breedGetAllProvider;
  }

  @Override
  public List<BreedEntity> execute() {
    log.info("Getting breed records");
    var breeds = this.breedGetAllProvider.getAll();

    if (breeds.isEmpty()) {
      log.warn("No breed record found");
      throw new DataNotFoundException("Breeds not found");
    }

    return breeds;
  }
}
