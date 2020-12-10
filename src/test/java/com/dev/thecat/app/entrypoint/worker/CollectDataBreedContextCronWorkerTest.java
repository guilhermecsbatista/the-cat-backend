package com.dev.thecat.app.entrypoint.worker;

import com.dev.thecat.domain.breed.usecase.BreedCreateContextDataUseCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.doNothing;

@RunWith(SpringJUnit4ClassRunner.class)
public class CollectDataBreedContextCronWorkerTest {
    private CollectDataBreedContextCronWorker collectDataBreedContextCronWorker;

    @Mock
    private BreedCreateContextDataUseCase breedCreateContextDataUseCase;

    @Before
    public void setUp() {
        this.collectDataBreedContextCronWorker = new CollectDataBreedContextCronWorker(this.breedCreateContextDataUseCase);
    }

    @Test
    public void executeOk() {
        doNothing().when(this.breedCreateContextDataUseCase).execute();
        this.collectDataBreedContextCronWorker.execute();
    }
}
