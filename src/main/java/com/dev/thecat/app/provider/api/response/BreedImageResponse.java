package com.dev.thecat.app.provider.api.response;

import com.dev.thecat.domain.image.entity.ImageEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BreedImageResponse {
    @Getter(onMethod_ = {@JsonProperty("id")})
    @Setter(onMethod_ = {@JsonProperty("id")})
    private String id;

    @Getter(onMethod_ = {@JsonProperty("url")})
    @Setter(onMethod_ = {@JsonProperty("url")})
    private String url;

    public ImageEntity toDomain() {
        return ImageEntity.builder()
                .integrationId(id)
                .url(url)
                .build();
    }
}
