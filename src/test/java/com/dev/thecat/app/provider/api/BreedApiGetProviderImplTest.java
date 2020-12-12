package com.dev.thecat.app.provider.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;


import com.dev.thecat.app.provider.api.client.TheCatApiClient;
import com.dev.thecat.app.provider.api.response.seed.BreedResponseSeed;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class BreedApiGetProviderImplTest {
  private BreedApiGetProviderImpl breedApiGetProvider;

  @Mock
  private TheCatApiClient theCatApiClient;

  @Before
  public void setUp() {
    this.breedApiGetProvider = new BreedApiGetProviderImpl(this.theCatApiClient);
  }

  @SuppressWarnings("unchecked")
  @Test
  public void getAllTest() {

    when(this.theCatApiClient.getBreed(any(Integer.class))).thenReturn(CompletableFuture
            .completedFuture(Arrays
                .asList(BreedResponseSeed.getBreedResponse(), BreedResponseSeed.getBreedResponse())),
        null,
        CompletableFuture
            .completedFuture(Collections.emptyList()));

    when(this.theCatApiClient.getBreedImage(anyString(), isNull()
    )).thenReturn(
        CompletableFuture
            .completedFuture(BreedResponseSeed.getBreedResponse().getBreedImageResponse()));

    when(this.theCatApiClient.getBreedImage(anyString(), anyString())).thenReturn(
        CompletableFuture
            .completedFuture(BreedResponseSeed.getBreedResponse().getBreedImageResponse()),
        CompletableFuture
            .completedFuture(BreedResponseSeed.getBreedResponse().getBreedImageResponse()),
        CompletableFuture
            .completedFuture(BreedResponseSeed.getBreedResponse().getBreedImageResponse()), null);

    this.breedApiGetProvider.getAll();
  }

}
