package com.dev.thecat.app.provider.database.postgres.table.seed;

import com.dev.thecat.app.provider.database.postgres.table.BreedImageTable;
import com.dev.thecat.app.provider.database.postgres.table.BreedTable;
import com.dev.thecat.app.provider.database.postgres.table.BreedTemperamentTable;
import com.dev.thecat.app.provider.database.postgres.table.ImageTable;
import com.dev.thecat.app.provider.database.postgres.table.OriginTable;
import com.dev.thecat.app.provider.database.postgres.table.TemperamentTable;
import java.util.Arrays;
import java.util.UUID;

public class BreedTableSeed {
  public static BreedTable getBreedTable() {
    return BreedTable.builder()
        .id(UUID.randomUUID())
        .integrationId("test")
        .name("test")
        .images(Arrays.asList(BreedImageTable.builder()
            .id(UUID.randomUUID())
            .breed(BreedTable.builder()
                .id(UUID.randomUUID())
                .integrationId("test")
                .build())
            .image(ImageTable.builder()
                .id(UUID.randomUUID())
                .integrationId("test")
                .url("test")
                .build())
            .build()))
        .temperaments(Arrays.asList(BreedTemperamentTable.builder()
            .id(UUID.randomUUID())
            .breed(BreedTable.builder()
                .id(UUID.randomUUID())
                .integrationId("test")
                .name("test")
                .images(Arrays.asList(BreedImageTable.builder()
                    .id(UUID.randomUUID())
                    .breed(BreedTable.builder()
                        .id(UUID.randomUUID())
                        .integrationId("test")
                        .build())
                    .image(ImageTable.builder()
                        .id(UUID.randomUUID())
                        .integrationId("test")
                        .url("test")
                        .build())
                    .build()))
                .temperaments(Arrays.asList(BreedTemperamentTable.builder()
                    .id(UUID.randomUUID())
                    .breed(BreedTable.builder()
                        .id(UUID.randomUUID())
                        .integrationId("test")
                        .build())
                    .temperament(TemperamentTable.builder()
                        .id(UUID.randomUUID())
                        .integrationId("test")
                        .description("test")
                        .build())
                    .build()))
                .origin(OriginTable.builder()
                    .id(UUID.randomUUID())
                    .integrationId("test")
                    .description("test")
                    .build())
                .build())
            .temperament(TemperamentTable.builder()
                .id(UUID.randomUUID())
                .integrationId("test")
                .description("test")
                .build())
            .build()))
        .origin(OriginTable.builder()
            .id(UUID.randomUUID())
            .integrationId("test")
            .description("test")
            .build())
        .build();
  }
}
