package com.dev.thecat.app.entrypoint.api.v1.endpoint;

import com.dev.thecat.app.entrypoint.api.v1.response.BreedResponse;
import com.dev.thecat.domain.breed.usecase.BreedGetByTemperamentIdUseCase;
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
@RequestMapping(value = "v1/temperaments", produces = {"application/json"})
public class TemperamentEndpoint {

  private final BreedGetByTemperamentIdUseCase breedGetByTemperamentIdUseCase;

  public TemperamentEndpoint(final BreedGetByTemperamentIdUseCase breedGetByTemperamentIdUseCase) {
    this.breedGetByTemperamentIdUseCase = breedGetByTemperamentIdUseCase;
  }

  @GetMapping(value = "{id}/breeds")
  @ResponseStatus(HttpStatus.OK)
  public List<BreedResponse> getBreedByTemperamentId(@PathVariable("id") @Valid UUID id) {
    try {
      return this.breedGetByTemperamentIdUseCase.execute(id).stream().map(BreedResponse::fromDomain)
          .collect(Collectors.toList());
    } catch (DataNotFoundException ex) {
      return Collections.emptyList();
    }
  }
}
