package com.dev.thecat.domain.breed.usecase.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import com.dev.thecat.domain.breed.entity.seed.BreedEntitySeeder;
import com.dev.thecat.domain.breed.provider.BreedGetByOriginIdProvider;
import com.dev.thecat.domain.breed.usecase.exception.DataNotFoundException;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class BreedGetByOriginIdUseCaseImplTest {
  private BreedGetByOriginIdUseCaseImpl breedGetByOriginIdUseCase;

  @Mock
  private BreedGetByOriginIdProvider breedGetByOriginIdProvider;

  @Before
  public void setUp() {
    this.breedGetByOriginIdUseCase =
        new BreedGetByOriginIdUseCaseImpl(this.breedGetByOriginIdProvider);
  }

  @Test
  public void executeTest() {
    when(this.breedGetByOriginIdProvider.getByOriginId(any()))
        .thenReturn(Collections.singletonList(BreedEntitySeeder.getBreedEntity()));
    this.breedGetByOriginIdUseCase.execute(any());
  }

  @Test(expected = DataNotFoundException.class)
  public void executeDataNotFoundTest() {
    when(this.breedGetByOriginIdProvider.getByOriginId(any())).thenReturn(Collections.emptyList());
    this.breedGetByOriginIdUseCase.execute(any());
  }
}
