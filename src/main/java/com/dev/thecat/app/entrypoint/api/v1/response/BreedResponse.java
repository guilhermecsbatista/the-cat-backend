package com.dev.thecat.app.entrypoint.api.v1.response;

import com.dev.thecat.domain.breed.entity.BreedEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BreedResponse {
  private UUID id;
  private IntegrationResponse integration;
  private String name;
  private OriginResponse origin;
  private List<TemperamentResponse> temperaments;
  private List<ImageResponse> images;

  public static BreedResponse fromDomain(final BreedEntity breed) {
    return BreedResponse.builder()
        .id(breed.getId())
        .integration(IntegrationResponse.builder().id(breed.getIntegrationId()).build())
        .name(breed.getName())
        .origin(OriginResponse.fromDomain(breed.getOrigin()))
        .temperaments(breed.getTemperaments().stream().map(TemperamentResponse::fromDomain)
            .collect(Collectors.toList()))
        .images(
            breed.getImages().stream().map(ImageResponse::fromDomain).collect(Collectors.toList()))
        .build();
  }
}
