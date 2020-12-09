package com.dev.thecat.domain.breed.provider;

import com.dev.thecat.domain.breed.entity.BreedEntity;

public interface BreedGetByIntegrationIdProvider {
    BreedEntity getByIntegrationId(String integrationId);
}
