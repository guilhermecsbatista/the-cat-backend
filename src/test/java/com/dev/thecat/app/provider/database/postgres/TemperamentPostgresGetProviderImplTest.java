package com.dev.thecat.app.provider.database.postgres;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import com.dev.thecat.app.provider.database.postgres.repository.TemperamentRepository;
import com.dev.thecat.app.provider.database.postgres.table.seed.BreedTableSeed;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class TemperamentPostgresGetProviderImplTest {
  private TemperamentPostgresGetProviderImpl temperamentPostgresGetProvider;

  @Mock
  private TemperamentRepository temperamentRepository;

  @Before
  public void setUp() {
    this.temperamentPostgresGetProvider =
        new TemperamentPostgresGetProviderImpl(this.temperamentRepository);
  }

  @Test
  public void getByIntegrationIdTest() {
    when(this.temperamentRepository.findByIntegrationId(any())).thenReturn(
        Optional.of(BreedTableSeed.getBreedTable().getTemperaments().get(0).getTemperament()));
    this.temperamentPostgresGetProvider.getByIntegrationId(any());
  }

  @Test
  public void getByIntegrationIdDataNotFoundTest() {
    when(this.temperamentRepository.findByIntegrationId(any())).thenReturn(Optional.empty());
    this.temperamentPostgresGetProvider.getByIntegrationId(any());
  }
}
