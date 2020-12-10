package com.dev.thecat.app.entrypoint.api.v1.endpoint;

import com.dev.thecat.domain.breed.entity.seed.BreedEntitySeeder;
import com.dev.thecat.domain.breed.usecase.BreedGetAllUseCase;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc(addFilters = false)
public class BreedEndpointTest {

    @Mock
    private BreedGetAllUseCase breedGetAllUseCase;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        final BreedEndpoint controller = new BreedEndpoint(this.breedGetAllUseCase);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @SneakyThrows
    public void getAllTest() {
        when(breedGetAllUseCase.execute()).thenReturn(Collections.singletonList(BreedEntitySeeder.getBreedEntity()));
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/v1/breeds")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void getAllDataNotFoundTest() {
        when(breedGetAllUseCase.execute()).thenThrow(new DataNotFoundException("Breed not found"));
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/v1/breeds")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
