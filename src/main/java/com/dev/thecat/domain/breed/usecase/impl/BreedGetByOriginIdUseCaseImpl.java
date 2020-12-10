package com.dev.thecat.domain.breed.usecase.impl;

import com.dev.thecat.domain.breed.entity.BreedEntity;
import com.dev.thecat.domain.breed.provider.BreedGetByOriginIdProvider;
import com.dev.thecat.domain.breed.usecase.BreedGetByOriginIdUseCase;
import com.dev.thecat.domain.breed.usecase.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.inject.Named;
import java.util.List;
import java.util.UUID;

@Named
public class BreedGetByOriginIdUseCaseImpl implements BreedGetByOriginIdUseCase {

    private final BreedGetByOriginIdProvider breedGetByOriginIdProvider;

    public BreedGetByOriginIdUseCaseImpl(
            @Qualifier("breedPostgresGetProvider") final BreedGetByOriginIdProvider breedGetByOriginIdProvider) {
        this.breedGetByOriginIdProvider = breedGetByOriginIdProvider;
    }

    @Override
    public List<BreedEntity> execute(UUID id) {
        var breeds = this.breedGetByOriginIdProvider.getByOriginId(id);

        if (breeds.isEmpty()) {
            throw new DataNotFoundException("Breed not found");
        }

        return breeds;
    }
}
