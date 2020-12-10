package com.dev.thecat.app.provider.api.response;

import com.dev.thecat.domain.image.entity.ImageEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BreedImageResponse {
    private String id;
    private String url;

    public ImageEntity toDomain() {
        return ImageEntity.builder()
                .integrationId(id)
                .url(url)
                .build();
    }
}
