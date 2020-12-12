package com.dev.thecat.domain.breed.usecase.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import com.dev.thecat.domain.breed.entity.seed.BreedEntitySeeder;
import com.dev.thecat.domain.breed.provider.BreedGetByTemperamentIdProvider;
import com.dev.thecat.domain.breed.usecase.exception.DataNotFoundException;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class BreedGetByTemperamentIdUseCaseImplTest {
  private BreedGetByTemperamentIdUseCaseImpl breedGetByTemperamentIdUseCase;

  @Mock
  private BreedGetByTemperamentIdProvider breedGetByTemperamentIdProvider;

  @Before
  public void setUp() {
    this.breedGetByTemperamentIdUseCase =
        new BreedGetByTemperamentIdUseCaseImpl(this.breedGetByTemperamentIdProvider);
  }

  @Test
  public void executeTest() {
    when(this.breedGetByTemperamentIdProvider.getByTemperamentId(any()))
        .thenReturn(Collections.singletonList(BreedEntitySeeder.getBreedEntity()));
    this.breedGetByTemperamentIdUseCase.execute(any());
  }

  @Test(expected = DataNotFoundException.class)
  public void executeDataNotFoundTest() {
    when(this.breedGetByTemperamentIdProvider.getByTemperamentId(any()))
        .thenReturn(Collections.emptyList());
    this.breedGetByTemperamentIdUseCase.execute(any());
  }
}
