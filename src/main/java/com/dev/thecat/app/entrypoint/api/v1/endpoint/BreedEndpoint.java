package com.dev.thecat.app.entrypoint.api.v1.endpoint;

import com.dev.thecat.app.entrypoint.api.v1.response.BreedResponse;
import com.dev.thecat.domain.breed.usecase.BreedGetAllUseCase;
import com.dev.thecat.domain.breed.usecase.BreedGetByIdUseCase;
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
@RequestMapping(value = "v1/breeds", produces = {"application/json"})
public class BreedEndpoint {

  private final BreedGetAllUseCase breedGetAllUseCase;
  private final BreedGetByIdUseCase breedGetByIdUseCase;

  public BreedEndpoint(
      final BreedGetAllUseCase breedGetAllUseCase,
      final BreedGetByIdUseCase breedGetByIdUseCase) {
    this.breedGetAllUseCase = breedGetAllUseCase;
    this.breedGetByIdUseCase = breedGetByIdUseCase;
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<BreedResponse> getAll() {
    try {
      return this.breedGetAllUseCase.execute().stream().map(BreedResponse::fromDomain)
          .collect(Collectors.toList());
    } catch (DataNotFoundException ex) {
      return Collections.emptyList();
    }
  }

  @GetMapping(value = "{id}")
  @ResponseStatus(HttpStatus.OK)
  public BreedResponse getById(@PathVariable("id") @Valid UUID id) {
    try {
      return BreedResponse.fromDomain(this.breedGetByIdUseCase.execute(id));
    } catch (DataNotFoundException ex) {
      return BreedResponse.builder().build();
    }
  }
}
