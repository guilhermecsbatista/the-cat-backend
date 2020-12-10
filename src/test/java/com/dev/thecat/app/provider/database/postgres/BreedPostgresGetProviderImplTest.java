package com.dev.thecat.app.provider.database.postgres;

import com.dev.thecat.app.provider.database.postgres.repository.BreedRepository;
import com.dev.thecat.app.provider.database.postgres.repository.BreedTemperamentRepository;
import com.dev.thecat.app.provider.database.postgres.table.seed.BreedTableSeed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class BreedPostgresGetProviderImplTest {
    private BreedPostgresGetProviderImpl breedPostgresGetProvider;

    @Mock
    private BreedRepository breedRepository;

    @Mock
    private BreedTemperamentRepository breedTemperamentRepository;

    @Before
    public void setUp() {
        this.breedPostgresGetProvider = new BreedPostgresGetProviderImpl(this.breedRepository, this.breedTemperamentRepository);
    }

    @Test
    public void getAllTest() {
        when(this.breedRepository.findAll()).thenReturn(Collections.singletonList(BreedTableSeed.getBreedTable()));
        this.breedPostgresGetProvider.getAll();
    }

    @Test
    public void getByIdTest() {
        when(this.breedRepository.findById(any())).thenReturn(Optional.of(BreedTableSeed.getBreedTable()));
        this.breedPostgresGetProvider.getById(any());
    }

    @Test
    public void getByIdDataNotFoundTest() {
        when(this.breedRepository.findById(any())).thenReturn(Optional.empty());
        this.breedPostgresGetProvider.getById(any());
    }

    @Test
    public void getByTemperamentIdTest() {
        when(this.breedTemperamentRepository.findByTemperamentId(any()))
                .thenReturn(Collections.singletonList(BreedTableSeed.getBreedTable().getTemperaments().get(0)));
        this.breedPostgresGetProvider.getByTemperamentId(any());
    }

    @Test
    public void getByOriginIdTest() {
        when(this.breedRepository.findById(any())).thenReturn(Optional.of(BreedTableSeed.getBreedTable()));
        this.breedPostgresGetProvider.getByOriginId(any());
    }

    @Test
    public void getByOriginIdDataNotFoundTest() {
        when(this.breedRepository.findById(any())).thenReturn(Optional.empty());
        this.breedPostgresGetProvider.getByOriginId(any());
    }

    @Test
    public void getByIntegrationIdTest() {
        when(this.breedRepository.findByIntegrationId(any())).thenReturn(Optional.of(BreedTableSeed.getBreedTable()));
        this.breedPostgresGetProvider.getByIntegrationId(any());
    }

    @Test
    public void getByIntegrationIdDataNotFoundTest() {
        when(this.breedRepository.findByIntegrationId(any())).thenReturn(Optional.empty());
        this.breedPostgresGetProvider.getByIntegrationId(any());
    }
}
