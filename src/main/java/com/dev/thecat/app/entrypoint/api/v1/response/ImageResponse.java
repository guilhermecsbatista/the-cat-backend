package com.dev.thecat.app.entrypoint.api.v1.response;

import com.dev.thecat.domain.image.entity.ImageEntity;
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
public class ImageResponse {
    private UUID id;
    private IntegrationResponse integration;
    private String url;

    public static ImageResponse fromDomain(final ImageEntity image) {
        return ImageResponse.builder()
                .id(image.getId())
                .integration(IntegrationResponse.builder().id(image.getIntegrationId()).build())
                .url(image.getUrl())
                .build();
    }
}
