package com.dev.thecat.app.entrypoint.api.v1.endpoint;

import com.dev.thecat.domain.breed.entity.seed.BreedEntitySeeder;
import com.dev.thecat.domain.breed.usecase.BreedGetByOriginIdUseCase;
import com.dev.thecat.domain.breed.usecase.exception.DataNotFoundException;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class OriginEndpointTest {

    @Mock
    private BreedGetByOriginIdUseCase breedGetByOriginIdUseCase;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        final OriginEndpoint controller = new OriginEndpoint(this.breedGetByOriginIdUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @SneakyThrows
    public void getBreedByOriginIdTest() {
        when(breedGetByOriginIdUseCase.execute(any())).thenReturn(Collections.singletonList(BreedEntitySeeder.getBreedEntity()));
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/v1/origins/" + UUID.randomUUID() + "/breeds")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void getBreedByOriginIdDataNotFoundTest() {
        when(breedGetByOriginIdUseCase.execute(any())).thenThrow(new DataNotFoundException("Breed not found"));
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/v1/origins/" + UUID.randomUUID() + "/breeds")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
