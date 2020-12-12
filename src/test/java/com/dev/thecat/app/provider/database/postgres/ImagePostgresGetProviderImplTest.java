package com.dev.thecat.app.provider.database.postgres;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import com.dev.thecat.app.provider.database.postgres.repository.ImageRepository;
import com.dev.thecat.app.provider.database.postgres.table.seed.BreedTableSeed;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ImagePostgresGetProviderImplTest {
  private ImagePostgresGetProviderImpl imagePostgresGetProvider;

  @Mock
  private ImageRepository imageRepository;

  @Before
  public void setUp() {
    this.imagePostgresGetProvider = new ImagePostgresGetProviderImpl(this.imageRepository);
  }

  @Test
  public void getByIntegrationIdTest() {
    when(this.imageRepository.findByIntegrationId(any())).thenReturn(
        Optional.of(BreedTableSeed.getBreedTable().getImages().get(0).getImage()));
    this.imagePostgresGetProvider.getByIntegrationId(any());
  }

  @Test
  public void getByIntegrationIdDataNotFoundTest() {
    when(this.imageRepository.findByIntegrationId(any())).thenReturn(Optional.empty());
    this.imagePostgresGetProvider.getByIntegrationId(any());
  }
}
