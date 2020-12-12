package com.dev.thecat.app.provider.api.response;

import com.dev.thecat.domain.breed.entity.BreedEntity;
import com.dev.thecat.domain.origin.entity.OriginEntity;
import com.dev.thecat.domain.temperament.entity.TemperamentEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BreedResponse {

  private String id;
  private String name;
  private String temperament;
  private String origin;
  private List<BreedImageResponse> breedImageResponse;

  public BreedEntity toDomain() {
    return BreedEntity.builder()
        .integrationId(id)
        .name(name)
        .origin(OriginEntity.builder()
            .integrationId(origin)
            .description(origin)
            .build())
        .temperaments(
            Stream.of(temperament
                .trim()
                .replace(" ", "")
                .split(",")).map(e -> TemperamentEntity.builder()
                .integrationId(e)
                .description(e)
                .build()).collect(Collectors.toList())
        )
        .images(breedImageResponse.stream().map(BreedImageResponse::toDomain)
            .collect(Collectors.toList()))
        .build();
  }
}
