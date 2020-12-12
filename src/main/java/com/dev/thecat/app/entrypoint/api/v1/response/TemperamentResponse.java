package com.dev.thecat.app.entrypoint.api.v1.response;

import com.dev.thecat.domain.temperament.entity.TemperamentEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TemperamentResponse {
  private UUID id;
  private IntegrationResponse integration;
  private String description;

  public static TemperamentResponse fromDomain(final TemperamentEntity temperament) {
    return TemperamentResponse.builder()
        .id(temperament.getId())
        .integration(IntegrationResponse.builder().id(temperament.getIntegrationId()).build())
        .description(temperament.getDescription())
        .build();
  }
}
