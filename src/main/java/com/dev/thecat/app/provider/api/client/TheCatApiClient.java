package com.dev.thecat.app.provider.api.client;

import com.dev.thecat.app.provider.api.response.BreedImageResponse;
import com.dev.thecat.app.provider.api.response.BreedResponse;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Service
public class TheCatApiClient {
  @Value("${theCatApi.url.base}")
  private String urlBase;

  @Value("${theCatApi.endpoint.v1.breed.path}")
  private String endpointBreed;

  @Value("${theCatApi.endpoint.v1.breed.limit}")
  private String endpointBreedLimit;

  @Value("${theCatApi.endpoint.v1.image.path}")
  private String endpointImage;

  @Value("${theCatApi.endpoint.v1.image.limit}")
  private String endpointImageLimit;

  @Value("${theCatApi.token}")
  private String token;

  private final RestTemplate restTemplate;

  public TheCatApiClient(final RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Async("threadPool")
  public CompletableFuture<List<BreedResponse>> getBreed(int page) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("x-api-key", token);
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    var url = urlBase + endpointBreed + "?limit=" + endpointBreedLimit + "&page=" + page;

    try {
      var result = this.restTemplate.exchange(
          url,
          HttpMethod.GET, new HttpEntity(headers),
          new ParameterizedTypeReference<List<BreedResponse>>() {
          });
      return CompletableFuture.completedFuture(result.getBody());
    } catch (HttpStatusCodeException ex) {
      log.error("Request url: {} error: {}", url, ex.getMessage());
      return CompletableFuture.completedFuture(Collections.emptyList());
    }
  }

  @Async("threadPool")
  public CompletableFuture<List<BreedImageResponse>> getBreedImage(String breedId,
                                                                   String categoryId) {
    log.info("Getting image categoryId: {}", categoryId);
    HttpHeaders headers = new HttpHeaders();
    headers.set("x-api-key", token);
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    var url = urlBase + endpointImage + "?breed_ids=" + breedId + "&include_breeds=false&limit="
        + endpointImageLimit;

    if (categoryId != null) {
      url = url + "&category_ids=" + categoryId;
    }

    try {
      var result = this.restTemplate.exchange(
          url,
          HttpMethod.GET, new HttpEntity(headers),
          new ParameterizedTypeReference<List<BreedImageResponse>>() {
          });
      return CompletableFuture.completedFuture(result.getBody());
    } catch (HttpStatusCodeException ex) {
      log.error("Request url: {} error: {}", url, ex.getMessage());
      return CompletableFuture.completedFuture(Collections.emptyList());
    }
  }
}
