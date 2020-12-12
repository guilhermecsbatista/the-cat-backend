package com.dev.thecat.app.provider.database.postgres;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import com.dev.thecat.app.provider.database.postgres.repository.BreedRepository;
import com.dev.thecat.domain.breed.entity.seed.BreedEntitySeeder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class BreedPostgresCreateProviderImplTest {
  private BreedPostgresCreateProviderImpl breedPostgresCreateProvider;

  @Mock
  private BreedRepository breedRepository;

  @Before
  public void setUp() {
    this.breedPostgresCreateProvider = new BreedPostgresCreateProviderImpl(this.breedRepository);
  }

  @Test
  public void createTest() {
    when(this.breedRepository.save(any())).thenReturn(null);
    this.breedPostgresCreateProvider.create(BreedEntitySeeder.getBreedEntity());
  }
}
