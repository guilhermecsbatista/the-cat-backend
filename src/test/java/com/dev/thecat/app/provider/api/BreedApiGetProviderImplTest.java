package com.dev.thecat.app.provider.api;

import com.dev.thecat.app.provider.api.response.BreedImageResponse;
import com.dev.thecat.app.provider.api.response.BreedResponse;
import com.dev.thecat.app.provider.api.response.seed.BreedResponseSeed;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class BreedApiGetProviderImplTest {
    private BreedApiGetProviderImpl breedApiGetProvider;

    @Mock
    private RestTemplate restTemplate;

    static final ResponseEntity<List<BreedResponse>> breedResponseSuccess =
            new ResponseEntity<>(Arrays.asList(BreedResponseSeed.getBreedResponse(), BreedResponseSeed.getBreedResponse()), HttpStatus.OK);

    static final ResponseEntity<List<BreedResponse>> breedResponseSuccessEmpty =
            new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);

    static final ResponseEntity<List<BreedImageResponse>> imageResponseSuccess =
            new ResponseEntity<>(Collections.singletonList(BreedResponseSeed.getBreedResponse().getBreedImageResponse().get(0)), HttpStatus.OK);

    static final ResponseEntity<List<BreedImageResponse>> imageResponseSuccessEmpty =
            new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);

    @Before
    public void setUp() {
        this.breedApiGetProvider = new BreedApiGetProviderImpl(this.restTemplate);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void getAllTest() {
        when(this.restTemplate
                        .exchange(any(String.class), any(HttpMethod.class), any(),
                                eq(new ParameterizedTypeReference<List<BreedResponse>>() {
                                })))
                .thenReturn(breedResponseSuccess, breedResponseSuccessEmpty);
        when(this.restTemplate
                        .exchange(any(String.class), any(HttpMethod.class), any(),
                                eq(new ParameterizedTypeReference<List<BreedImageResponse>>() {
                                })))
                .thenReturn(imageResponseSuccess, imageResponseSuccessEmpty);
        this.breedApiGetProvider.getAll();
    }

    @SuppressWarnings("unchecked")
    @Test(expected = HttpStatusCodeException.class)
    public void getAllImageErrorTest() {
        when(
                this.restTemplate
                        .exchange(any(String.class), any(HttpMethod.class), any(),
                                eq(new ParameterizedTypeReference<List<BreedResponse>>() {
                                })))
                .thenReturn(breedResponseSuccess, breedResponseSuccessEmpty);
        when(
                this.restTemplate
                        .exchange(any(String.class), any(HttpMethod.class), any(),
                                eq(new ParameterizedTypeReference<List<BreedImageResponse>>() {
                                })))
                .thenThrow(new HttpStatusCodeException(HttpStatus.INTERNAL_SERVER_ERROR) {
                });
        this.breedApiGetProvider.getAll();
    }

    @Test(expected = HttpStatusCodeException.class)
    public void getAllErrorTest() {
        when(
                this.restTemplate
                        .exchange(any(String.class), any(HttpMethod.class), any(),
                                eq(new ParameterizedTypeReference<List<BreedResponse>>() {
                                })))
                .thenThrow(new HttpStatusCodeException(HttpStatus.INTERNAL_SERVER_ERROR) {
                });
        this.breedApiGetProvider.getAll();
    }

}
