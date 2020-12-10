package com.dev.thecat.domain.breed.usecase;

import com.dev.thecat.domain.breed.entity.BreedEntity;

import java.util.UUID;

public interface BreedGetByIdUseCase {
    BreedEntity execute(UUID id);
}
