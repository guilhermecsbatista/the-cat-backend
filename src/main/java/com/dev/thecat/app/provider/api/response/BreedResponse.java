package com.dev.thecat.app.provider.api.response;

import com.dev.thecat.domain.breed.entity.BreedEntity;
import com.dev.thecat.domain.origin.entity.OriginEntity;
import com.dev.thecat.domain.temperament.entity.TemperamentEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BreedResponse {
    @Getter(onMethod_ = {@JsonProperty("id")})
    @Setter(onMethod_ = {@JsonProperty("id")})
    private String id;

    @Getter(onMethod_ = {@JsonProperty("name")})
    @Setter(onMethod_ = {@JsonProperty("name")})
    private String name;

    @Getter(onMethod_ = {@JsonProperty("temperament")})
    @Setter(onMethod_ = {@JsonProperty("temperament")})
    private String temperament;

    @Getter(onMethod_ = {@JsonProperty("origin")})
    @Setter(onMethod_ = {@JsonProperty("origin")})
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
                .images(breedImageResponse.stream().map(BreedImageResponse::toDomain).collect(Collectors.toList()))
                .build();
    }
}
