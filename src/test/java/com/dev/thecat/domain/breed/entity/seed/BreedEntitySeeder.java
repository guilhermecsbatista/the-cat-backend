package com.dev.thecat.domain.breed.entity.seed;

import com.dev.thecat.domain.breed.entity.BreedEntity;
import com.dev.thecat.domain.image.entity.ImageEntity;
import com.dev.thecat.domain.origin.entity.OriginEntity;
import com.dev.thecat.domain.temperament.entity.TemperamentEntity;
import java.util.Arrays;
import java.util.UUID;

public class BreedEntitySeeder {
  public static BreedEntity getBreedEntity() {
    return BreedEntity.builder()
        .id(UUID.randomUUID())
        .integrationId("test")
        .images(
            Arrays.asList(
                ImageEntity.builder()
                    .id(UUID.randomUUID())
                    .integrationId("test")
                    .url("http://test").build()))
        .origin(OriginEntity.builder()
            .id(UUID.randomUUID())
            .integrationId("test")
            .description("test").build())
        .temperaments(Arrays.asList(TemperamentEntity.builder()
            .id(UUID.randomUUID())
            .integrationId("test")
            .description("test").build()))
        .build();
  }
}
