package com.dev.thecat.app.provider.api.client;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


import com.dev.thecat.app.provider.api.constant.ImageCategoryConstant;
import com.dev.thecat.app.provider.api.response.BreedImageResponse;
import com.dev.thecat.app.provider.api.response.BreedResponse;
import com.dev.thecat.app.provider.api.response.seed.BreedResponseSeed;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

@RunWith(SpringJUnit4ClassRunner.class)
public class TheCatApiClientTest {
  private TheCatApiClient theCatApiClient;

  @Mock
  private RestTemplate restTemplate;

  static final ResponseEntity<List<BreedResponse>> breedResponseSuccess =
      new ResponseEntity<>(
          Arrays.asList(BreedResponseSeed.getBreedResponse(), BreedResponseSeed.getBreedResponse()),
          HttpStatus.OK);

  static final ResponseEntity<List<BreedResponse>> breedResponseSuccessEmpty =
      new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);

  static final ResponseEntity<List<BreedImageResponse>> imageResponseSuccess =
      new ResponseEntity<>(Collections
          .singletonList(BreedResponseSeed.getBreedResponse().getBreedImageResponse().get(0)),
          HttpStatus.OK);

  static final ResponseEntity<List<BreedImageResponse>> imageResponseSuccessEmpty =
      new ResponseEntity<>(Collections.emptyList(), HttpStatus.OK);

  @Before
  public void setUp() {
    this.theCatApiClient = new TheCatApiClient(this.restTemplate);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void getBreedTest() {
    when(this.restTemplate
        .exchange(any(String.class), any(HttpMethod.class), any(),
            eq(new ParameterizedTypeReference<List<BreedResponse>>() {
            })))
        .thenReturn(breedResponseSuccess, breedResponseSuccessEmpty);
    this.theCatApiClient.getBreed(0);
  }

  @Test
  public void getBreedErrorTest() {
    when(this.restTemplate
        .exchange(any(String.class), any(HttpMethod.class), any(),
            eq(new ParameterizedTypeReference<List<BreedResponse>>() {
            })))
        .thenThrow(new HttpStatusCodeException(HttpStatus.INTERNAL_SERVER_ERROR) {
        });
    this.theCatApiClient.getBreed(0);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void getImageTest() {
    when(this.restTemplate
        .exchange(any(String.class), any(HttpMethod.class), any(),
            eq(new ParameterizedTypeReference<List<BreedImageResponse>>() {
            })))
        .thenReturn(imageResponseSuccess, imageResponseSuccessEmpty);
    this.theCatApiClient.getBreedImage("test", ImageCategoryConstant.HAT);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void getImageCategoryNullTest() {
    when(this.restTemplate
        .exchange(any(String.class), any(HttpMethod.class), any(),
            eq(new ParameterizedTypeReference<List<BreedImageResponse>>() {
            })))
        .thenReturn(imageResponseSuccess, imageResponseSuccessEmpty);
    this.theCatApiClient.getBreedImage("test", ImageCategoryConstant.NONE);
  }

  @Test
  public void getImageErrorTest() {
    when(
        this.restTemplate
            .exchange(any(String.class), any(HttpMethod.class), any(),
                eq(new ParameterizedTypeReference<List<BreedImageResponse>>() {
                })))
        .thenThrow(new HttpStatusCodeException(HttpStatus.INTERNAL_SERVER_ERROR) {
        });
    this.theCatApiClient.getBreedImage("test", ImageCategoryConstant.SUNGLASSES);
  }
}
