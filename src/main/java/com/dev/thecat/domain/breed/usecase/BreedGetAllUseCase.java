package com.dev.thecat.domain.breed.usecase;

import com.dev.thecat.domain.breed.entity.BreedEntity;
import java.util.List;

public interface BreedGetAllUseCase {
  List<BreedEntity> execute();
}
