package com.dev.thecat.domain.breed.usecase.impl;

import com.dev.thecat.domain.breed.entity.BreedEntity;
import com.dev.thecat.domain.breed.provider.BreedGetByIdProvider;
import com.dev.thecat.domain.breed.usecase.BreedGetByIdUseCase;
import com.dev.thecat.domain.breed.usecase.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.inject.Named;
import java.util.UUID;

@Named
public class BreedGetByIdUseCaseImpl implements BreedGetByIdUseCase {

    private final BreedGetByIdProvider breedGetByIdProvider;

    public BreedGetByIdUseCaseImpl(
            @Qualifier("breedPostgresGetProvider") final BreedGetByIdProvider breedGetByIdProvider) {
        this.breedGetByIdProvider = breedGetByIdProvider;
    }

    @Override
    public BreedEntity execute(UUID id) {
        var breed = this.breedGetByIdProvider.getById(id);

        if (breed == null) {
            throw new DataNotFoundException("Breed not found");
        }

        return breed;
    }
}
