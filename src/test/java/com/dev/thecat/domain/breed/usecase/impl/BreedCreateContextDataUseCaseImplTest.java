package com.dev.thecat.domain.breed.usecase.impl;

import com.dev.thecat.domain.breed.entity.seed.BreedEntitySeeder;
import com.dev.thecat.domain.breed.provider.BreedCreateProvider;
import com.dev.thecat.domain.breed.provider.BreedGetAllProvider;
import com.dev.thecat.domain.breed.provider.BreedGetByIntegrationIdProvider;
import com.dev.thecat.domain.image.provider.ImageGetByIntegrationIdProvider;
import com.dev.thecat.domain.origin.provider.OriginGetByIntegrationIdProvider;
import com.dev.thecat.domain.temperament.provider.TemperamentGetByIntegrationIdProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class BreedCreateContextDataUseCaseImplTest {
    private BreedCreateContextDataUseCaseImpl breedCreateContextDataUseCase;

    @Mock
    private BreedGetAllProvider breedGetAllProvider;

    @Mock
    private BreedGetByIntegrationIdProvider breedGetByIntegrationIdProvider;

    @Mock
    private OriginGetByIntegrationIdProvider originGetByIntegrationIdProvider;

    @Mock
    private ImageGetByIntegrationIdProvider imageGetByIntegrationIdProvider;

    @Mock
    private TemperamentGetByIntegrationIdProvider temperamentGetByIntegrationIdProvider;

    @Mock
    private BreedCreateProvider breedCreateProvider;

    @Before
    public void setUp() {
        this.breedCreateContextDataUseCase = new BreedCreateContextDataUseCaseImpl(
                this.breedGetAllProvider,
                this.breedGetByIntegrationIdProvider,
                this.originGetByIntegrationIdProvider,
                this.imageGetByIntegrationIdProvider,
                this.temperamentGetByIntegrationIdProvider,
                this.breedCreateProvider
        );
    }

    @Test
    public void executeAlreadySavedTest() {
        when(this.breedGetAllProvider.getAll()).thenReturn(Collections.singletonList(BreedEntitySeeder.getBreedEntity()));
        when(this.breedGetByIntegrationIdProvider
                        .getByIntegrationId(anyString())).thenReturn(BreedEntitySeeder.getBreedEntity());
        this.breedCreateContextDataUseCase.execute();
    }

    @Test
    public void executeTest() {
        when(this.breedGetAllProvider.getAll()).thenReturn(Collections.singletonList(BreedEntitySeeder.getBreedEntity()));

        when(this.breedGetByIntegrationIdProvider
                        .getByIntegrationId(anyString())).thenReturn(null);
        when(this.originGetByIntegrationIdProvider
                        .getByIntegrationId(anyString())).thenReturn(null);
        when(this.imageGetByIntegrationIdProvider
                        .getByIntegrationId(anyString())).thenReturn(null);
        when(this.temperamentGetByIntegrationIdProvider
                        .getByIntegrationId(anyString())).thenReturn(null);
        Mockito.doNothing().when(this.breedCreateProvider).create(Mockito.any());
        this.breedCreateContextDataUseCase.execute();
    }

    @Test
    public void executeOriginImageTemperamentAlreadySavedTest() {
        when(this.breedGetAllProvider.getAll()).thenReturn(Collections.singletonList(BreedEntitySeeder.getBreedEntity()));

        when(this.breedGetByIntegrationIdProvider
                        .getByIntegrationId(anyString())).thenReturn(null);
        when(this.originGetByIntegrationIdProvider
                        .getByIntegrationId(anyString())).thenReturn(BreedEntitySeeder.getBreedEntity().getOrigin());
        when(this.imageGetByIntegrationIdProvider
                        .getByIntegrationId(anyString())).thenReturn(BreedEntitySeeder.getBreedEntity().getImages().get(0));
        when(this.temperamentGetByIntegrationIdProvider
                        .getByIntegrationId(anyString())).thenReturn(BreedEntitySeeder.getBreedEntity().getTemperaments().get(0));
        Mockito.doNothing().when(this.breedCreateProvider).create(Mockito.any());
        this.breedCreateContextDataUseCase.execute();
    }
}
