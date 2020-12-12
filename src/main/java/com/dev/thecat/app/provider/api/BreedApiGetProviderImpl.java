package com.dev.thecat.app.provider.api;

import com.dev.thecat.app.provider.api.response.BreedImageResponse;
import com.dev.thecat.app.provider.api.response.BreedResponse;
import com.dev.thecat.domain.breed.entity.BreedEntity;
import com.dev.thecat.domain.breed.provider.BreedGetAllProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Named("breedApiGetProvider")
public class BreedApiGetProviderImpl implements BreedGetAllProvider {

  @Value("${theCatApi.url.base}")
  private String urlBase;

  @Value("${theCatApi.endpoint.v1.breed.path}")
  private String endpointBreed;

  @Value("${theCatApi.endpoint.v1.breed.limit}")
  private String endpointBreedLimit;

  @Value("${theCatApi.endpoint.v1.image.path}")
  private String endpointImage;

  @Value("${theCatApi.token}")
  private String token;

  private final RestTemplate restTemplate;

  public BreedApiGetProviderImpl(final RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public List<BreedEntity> getAll() {
    List<BreedResponse> breedResponseList = new ArrayList<>();

    int page = 0;
    while (true) {
      var breedResponse = this.getBreedTheCatApi(endpointBreedLimit, page);
      if (Objects.requireNonNull(breedResponse.getBody()).isEmpty()) {
        break;
      }
      breedResponseList.addAll(breedResponse.getBody());
      page++;
    }

    for (int i = 0; i < breedResponseList.size(); i++) {
      var breedImageResponse = this.getBreedImageTheCatApi(breedResponseList.get(i).getId());
      var breedImageResponseBody = breedImageResponse.getBody();
      if (!Objects.requireNonNull(breedImageResponseBody).isEmpty()) {
        breedResponseList.get(i).setBreedImageResponse(breedImageResponseBody);
      }
    }

    return breedResponseList.stream().map(BreedResponse::toDomain).collect(Collectors.toList());

  }

  private ResponseEntity<List<BreedResponse>> getBreedTheCatApi(String limit, int page) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("x-api-key", token);
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    var url = urlBase + endpointBreed + "?limit=" + limit + "&page=" + page;

    try {
      return this.restTemplate.exchange(
          url,
          HttpMethod.GET, new HttpEntity(headers),
          new ParameterizedTypeReference<List<BreedResponse>>() {
          });
    } catch (HttpStatusCodeException ex) {
      throw ex;
    }
  }

  private ResponseEntity<List<BreedImageResponse>> getBreedImageTheCatApi(String breedId) {
    HttpHeaders headers = new HttpHeaders();
    headers.set("x-api-key", token);
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    var url = urlBase + endpointImage + "?breed_ids=" + breedId + "&include_breeds=false&limit=3";

    try {
      return this.restTemplate.exchange(
          url,
          HttpMethod.GET, new HttpEntity(headers),
          new ParameterizedTypeReference<List<BreedImageResponse>>() {
          });
    } catch (HttpStatusCodeException ex) {
      throw ex;
    }
  }
}
