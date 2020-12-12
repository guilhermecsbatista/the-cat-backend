package com.dev.thecat.app.entrypoint.worker;

import com.dev.thecat.domain.breed.usecase.BreedCreateContextDataUseCase;
import javax.inject.Named;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;

@Log4j2
@Named
public class CollectDataBreedContextCronWorker {

  private final BreedCreateContextDataUseCase breedCreateContextDataUseCase;

  public CollectDataBreedContextCronWorker(
      final BreedCreateContextDataUseCase breedCreateContextDataUseCase) {
    this.breedCreateContextDataUseCase = breedCreateContextDataUseCase;
  }

  @Scheduled(cron = "${application.schedule.cron}", zone = "America/Sao_Paulo")
  public void execute() {
    log.info("Start collecting breed data");
    this.breedCreateContextDataUseCase.execute();
    log.info("Finish collecting breed data");
  }
}
