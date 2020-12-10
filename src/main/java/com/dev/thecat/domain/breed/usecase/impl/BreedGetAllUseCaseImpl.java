package com.dev.thecat.domain.breed.usecase.impl;

import com.dev.thecat.domain.breed.entity.BreedEntity;
import com.dev.thecat.domain.breed.provider.BreedGetAllProvider;
import com.dev.thecat.domain.breed.usecase.BreedGetAllUseCase;
import com.dev.thecat.domain.breed.usecase.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.inject.Named;
import java.util.List;

@Named
public class BreedGetAllUseCaseImpl implements BreedGetAllUseCase {

    private final BreedGetAllProvider breedGetAllProvider;

    public BreedGetAllUseCaseImpl(
            @Qualifier("breedPostgresGetProvider") final BreedGetAllProvider breedGetAllProvider) {
        this.breedGetAllProvider = breedGetAllProvider;
    }

    @Override
    public List<BreedEntity> execute() {
        var breeds = this.breedGetAllProvider.getAll();

        if (breeds.isEmpty()) {
            throw new DataNotFoundException("Breeds not found");
        }

        return breeds;
    }
}
