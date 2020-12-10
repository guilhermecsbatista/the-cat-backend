package com.dev.thecat.app.entrypoint.api.v1.endpoint;

import com.dev.thecat.app.entrypoint.api.v1.response.BreedResponse;
import com.dev.thecat.domain.breed.usecase.BreedGetAllUseCase;
import com.dev.thecat.domain.breed.usecase.exception.DataNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "v1/breeds", produces = {"application/json"})
public class BreedEndpoint {
    private final BreedGetAllUseCase breedGetAllUseCase;

    public BreedEndpoint(final BreedGetAllUseCase breedGetAllUseCase) {
        this.breedGetAllUseCase = breedGetAllUseCase;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BreedResponse> getAll() {
        try {
            return this.breedGetAllUseCase.execute().stream().map(BreedResponse::fromDomain).collect(Collectors.toList());
        } catch (DataNotFoundException ex) {
            return Collections.emptyList();
        }
    }
}
