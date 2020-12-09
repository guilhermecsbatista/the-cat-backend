package com.dev.thecat.app.provider.api.response.seed;

import com.dev.thecat.app.provider.api.response.BreedImageResponse;
import com.dev.thecat.app.provider.api.response.BreedResponse;

import java.util.Arrays;

public class BreedResponseSeed {
    public static BreedResponse getBreedResponse() {
        return BreedResponse.builder()
                .id("test")
                .name("test")
                .origin("test")
                .temperament("test, test1")
                .breedImageResponse(Arrays.asList(BreedImageResponse.builder()
                        .id("test")
                        .url("test")
                        .build(), BreedImageResponse.builder()
                        .id("test")
                        .url("test")
                        .build()))
                .build();
    }
}
