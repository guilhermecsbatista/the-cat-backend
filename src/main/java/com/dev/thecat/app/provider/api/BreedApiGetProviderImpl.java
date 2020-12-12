package com.dev.thecat.app.provider.api;

import com.dev.thecat.app.provider.api.client.TheCatApiClient;
import com.dev.thecat.app.provider.api.constant.ImageCategoryConstant;
import com.dev.thecat.app.provider.api.response.BreedImageResponse;
import com.dev.thecat.app.provider.api.response.BreedResponse;
import com.dev.thecat.domain.breed.entity.BreedEntity;
import com.dev.thecat.domain.breed.provider.BreedGetAllProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import javax.inject.Named;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Named("breedApiGetProvider")
public class BreedApiGetProviderImpl implements BreedGetAllProvider {

  private final TheCatApiClient theCatApiClient;

  public BreedApiGetProviderImpl(
      final TheCatApiClient theCatApiClient
  ) {
    this.theCatApiClient = theCatApiClient;
  }

  @Override
  public List<BreedEntity> getAll() {
    List<BreedResponse> breedResponseList = new ArrayList<>();

    int page = 0;
    while (true) {
      CompletableFuture<List<BreedResponse>> breedResponse = this.theCatApiClient.getBreed(page);

      try {
        if (Objects.requireNonNull(breedResponse.get()).isEmpty()) {
          break;
        }
        breedResponseList.addAll(breedResponse.get());
      } catch (Throwable e) {
        log.error(e.getMessage());
      }
      page++;
    }

    for (int i = 0; i < breedResponseList.size(); i++) {
      List<BreedImageResponse> images = new ArrayList<>();

      CompletableFuture<List<BreedImageResponse>> imagesCategoryNone = this.theCatApiClient
          .getBreedImage(breedResponseList.get(i).getId(), ImageCategoryConstant.NONE);

      CompletableFuture<List<BreedImageResponse>> imagesCategoryHats = this.theCatApiClient
          .getBreedImage(breedResponseList.get(i).getId(), ImageCategoryConstant.HAT);

      CompletableFuture<List<BreedImageResponse>> imagesCategorySunglasses = this.theCatApiClient
          .getBreedImage(breedResponseList.get(i).getId(), ImageCategoryConstant.SUNGLASSES);

      try {
        images.addAll(imagesCategoryNone.get());
        images.addAll(imagesCategoryHats.get());
        images.addAll(imagesCategorySunglasses.get());
      } catch (Throwable e) {
        log.error(e.getMessage());
      }

      breedResponseList.get(i).setBreedImageResponse(images);
    }

    return breedResponseList.stream().map(BreedResponse::toDomain).collect(Collectors.toList());

  }
}
