package com.dev.thecat.domain.breed.usecase.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import com.dev.thecat.domain.breed.entity.seed.BreedEntitySeeder;
import com.dev.thecat.domain.breed.provider.BreedGetByIdProvider;
import com.dev.thecat.domain.breed.usecase.exception.DataNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class BreedGetByIdUseCaseImplTest {
  private BreedGetByIdUseCaseImpl breedGetByIdUseCase;

  @Mock
  private BreedGetByIdProvider breedGetByIdProvider;

  @Before
  public void setUp() {
    this.breedGetByIdUseCase = new BreedGetByIdUseCaseImpl(this.breedGetByIdProvider);
  }

  @Test
  public void executeTest() {
    when(this.breedGetByIdProvider.getById(any())).thenReturn(BreedEntitySeeder.getBreedEntity());
    this.breedGetByIdUseCase.execute(any());
  }

  @Test(expected = DataNotFoundException.class)
  public void executeDataNotFoundTest() {
    when(this.breedGetByIdProvider.getById(any())).thenReturn(null);
    this.breedGetByIdUseCase.execute(any());
  }
}
