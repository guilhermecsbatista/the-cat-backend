package com.dev.thecat.domain.breed.provider;

import com.dev.thecat.domain.breed.entity.BreedEntity;

import java.util.UUID;

public interface BreedGetByIdProvider {
    BreedEntity getById(UUID id);
}
