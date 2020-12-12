package com.dev.thecat.app.entrypoint.api.v1.endpoint;

import com.dev.thecat.app.entrypoint.api.v1.response.BreedResponse;
import com.dev.thecat.domain.breed.usecase.BreedGetByOriginIdUseCase;
import com.dev.thecat.domain.breed.usecase.exception.DataNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "v1/origins", produces = {"application/json"})
public class OriginEndpoint {
  private final BreedGetByOriginIdUseCase breedGetByOriginIdUseCase;

  public OriginEndpoint(final BreedGetByOriginIdUseCase breedGetByOriginIdUseCase) {
    this.breedGetByOriginIdUseCase = breedGetByOriginIdUseCase;
  }

  @GetMapping(value = "{id}/breeds")
  @ResponseStatus(HttpStatus.OK)
  public List<BreedResponse> getBreedByOriginId(@PathVariable("id") @Valid UUID id) {
    try {
      return this.breedGetByOriginIdUseCase.execute(id).stream().map(BreedResponse::fromDomain)
          .collect(Collectors.toList());
    } catch (DataNotFoundException ex) {
      return Collections.emptyList();
    }
  }
}
