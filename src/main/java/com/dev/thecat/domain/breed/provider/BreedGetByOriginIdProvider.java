package com.dev.thecat.domain.breed.provider;

import com.dev.thecat.domain.breed.entity.BreedEntity;
import java.util.List;
import java.util.UUID;

public interface BreedGetByOriginIdProvider {
  List<BreedEntity> getByOriginId(UUID id);
}
