package com.dev.thecat.domain.breed.usecase.impl;

import static org.mockito.Mockito.when;


import com.dev.thecat.domain.breed.entity.seed.BreedEntitySeeder;
import com.dev.thecat.domain.breed.provider.BreedGetAllProvider;
import com.dev.thecat.domain.breed.usecase.exception.DataNotFoundException;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class BreedGetAllUseCaseImplTest {
  private BreedGetAllUseCaseImpl breedGetAllUseCase;

  @Mock
  private BreedGetAllProvider breedGetAllProvider;

  @Before
  public void setUp() {
    this.breedGetAllUseCase = new BreedGetAllUseCaseImpl(this.breedGetAllProvider);
  }

  @Test
  public void executeTest() {
    when(this.breedGetAllProvider.getAll())
        .thenReturn(Collections.singletonList(BreedEntitySeeder.getBreedEntity()));
    this.breedGetAllUseCase.execute();
  }

  @Test(expected = DataNotFoundException.class)
  public void executeDataNotFoundTest() {
    when(this.breedGetAllProvider.getAll()).thenReturn(Collections.emptyList());
    this.breedGetAllUseCase.execute();
  }
}
