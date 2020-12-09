package com.dev.thecat.app.provider.database.postgres;

import com.dev.thecat.app.provider.database.postgres.repository.OriginRepository;
import com.dev.thecat.app.provider.database.postgres.table.seed.BreedTableSeed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class OriginPostgresGetProviderImplTest {
    private OriginPostgresGetProviderImpl originPostgresGetProvider;

    @Mock
    private OriginRepository originRepository;

    @Before
    public void setUp() {
        this.originPostgresGetProvider = new OriginPostgresGetProviderImpl(this.originRepository);
    }

    @Test
    public void getByIntegrationIdTest() {
        when(this.originRepository.findByIntegrationId(any())).thenReturn(
                Optional.of(BreedTableSeed.getBreedTable().getOrigin()));
        this.originPostgresGetProvider.getByIntegrationId(any());
    }

    @Test
    public void getByIntegrationIdDataNotFoundTest() {
        when(this.originRepository.findByIntegrationId(any())).thenReturn(Optional.empty());
        this.originPostgresGetProvider.getByIntegrationId(any());
    }
}
