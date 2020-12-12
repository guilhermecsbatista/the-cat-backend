package com.dev.thecat.domain.breed.usecase;

import com.dev.thecat.domain.breed.entity.BreedEntity;
import java.util.List;
import java.util.UUID;

public interface BreedGetByOriginIdUseCase {
  List<BreedEntity> execute(UUID id);
}
