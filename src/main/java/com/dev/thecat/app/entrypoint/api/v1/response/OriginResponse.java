package com.dev.thecat.app.entrypoint.api.v1.response;

import com.dev.thecat.domain.origin.entity.OriginEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OriginResponse {
    private UUID id;
    private IntegrationResponse integration;
    private String description;

    public static OriginResponse fromDomain(final OriginEntity origin) {
        return OriginResponse.builder()
                .id(origin.getId())
                .integration(IntegrationResponse.builder().id(origin.getIntegrationId()).build())
                .description(origin.getDescription())
                .build();
    }
}
