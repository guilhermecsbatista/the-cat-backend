package com.dev.thecat.app.entrypoint.worker;

import com.dev.thecat.domain.breed.usecase.BreedCreateContextDataUseCase;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

@Named
public class CollectDataBreedContextCronWorker {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final BreedCreateContextDataUseCase breedCreateContextDataUseCase;

  public CollectDataBreedContextCronWorker(
      final BreedCreateContextDataUseCase breedCreateContextDataUseCase) {
    this.breedCreateContextDataUseCase = breedCreateContextDataUseCase;
  }

  @Scheduled(cron = "${application.schedule.cron}", zone = "America/Sao_Paulo")
  public void execute() {
    logger.info("Start collecting breed data");
    this.breedCreateContextDataUseCase.execute();
    logger.info("Finish collecting breed data");
  }
}
