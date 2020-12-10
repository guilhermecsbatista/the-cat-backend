package com.dev.thecat.domain.breed.usecase.impl;

import com.dev.thecat.domain.breed.provider.BreedCreateProvider;
import com.dev.thecat.domain.breed.provider.BreedGetAllProvider;
import com.dev.thecat.domain.breed.provider.BreedGetByIntegrationIdProvider;
import com.dev.thecat.domain.breed.usecase.BreedCreateContextDataUseCase;
import com.dev.thecat.domain.image.provider.ImageGetByIntegrationIdProvider;
import com.dev.thecat.domain.origin.provider.OriginGetByIntegrationIdProvider;
import com.dev.thecat.domain.temperament.provider.TemperamentGetByIntegrationIdProvider;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.inject.Named;

@Named
public class BreedCreateContextDataUseCaseImpl implements BreedCreateContextDataUseCase {

    private final BreedGetAllProvider breedGetAllProvider;
    private final BreedGetByIntegrationIdProvider breedGetByIntegrationIdProvider;
    private final OriginGetByIntegrationIdProvider originGetByIntegrationIdProvider;
    private final ImageGetByIntegrationIdProvider imageGetByIntegrationIdProvider;
    private final TemperamentGetByIntegrationIdProvider temperamentGetByIntegrationIdProvider;
    private final BreedCreateProvider breedCreateProvider;

    public BreedCreateContextDataUseCaseImpl(
            @Qualifier("breedApiGetProvider") final BreedGetAllProvider breedGetAllProvider,
            @Qualifier("breedPostgresGetProvider") final BreedGetByIntegrationIdProvider breedGetByIntegrationIdProvider,
            @Qualifier("originPostgresGetProvider") final OriginGetByIntegrationIdProvider originGetByIntegrationIdProvider,
            @Qualifier("imagePostgresGetProvider") final ImageGetByIntegrationIdProvider imageGetByIntegrationIdProvider,
            @Qualifier("temperamentPostgresGetProvider") final TemperamentGetByIntegrationIdProvider temperamentGetByIntegrationIdProvider,
            @Qualifier("breedPostgresCreateProvider") final BreedCreateProvider breedCreateProvider) {
        this.breedGetAllProvider = breedGetAllProvider;
        this.breedGetByIntegrationIdProvider = breedGetByIntegrationIdProvider;
        this.originGetByIntegrationIdProvider = originGetByIntegrationIdProvider;
        this.imageGetByIntegrationIdProvider = imageGetByIntegrationIdProvider;
        this.temperamentGetByIntegrationIdProvider = temperamentGetByIntegrationIdProvider;
        this.breedCreateProvider = breedCreateProvider;
    }

    @Override
    public void execute() {
        var breedEntityList = this.breedGetAllProvider.getAll();

        for (int i = 0; i < breedEntityList.size(); i++) {
            var breedEntity = breedEntityList.get(i);
            var breedExist = this.breedGetByIntegrationIdProvider.getByIntegrationId(breedEntity.getIntegrationId());

            if (breedExist == null) {
                var originExist = this.originGetByIntegrationIdProvider.getByIntegrationId(
                        breedEntity.getOrigin().getIntegrationId());
                if (originExist != null) {
                    breedEntityList.get(i).setOrigin(originExist);
                }

                for (int j = 0; j < breedEntityList.get(i).getImages().size(); j++) {
                    var image = breedEntityList.get(i).getImages().get(j);
                    var imageExist = this.imageGetByIntegrationIdProvider.getByIntegrationId(image.getIntegrationId());
                    if (imageExist != null) {
                        breedEntityList.get(i).getImages().set(j, imageExist);
                    }
                }

                for (int k = 0; k < breedEntityList.get(i).getTemperaments().size(); k++) {
                    var temperament = breedEntityList.get(i).getTemperaments().get(k);
                    var temperamentExist = this.temperamentGetByIntegrationIdProvider.getByIntegrationId(temperament.getIntegrationId());
                    if (temperamentExist != null) {
                        breedEntityList.get(i).getTemperaments().set(k, temperamentExist);
                    }
                }

                this.breedCreateProvider.create(breedEntityList.get(i));
            }
        }
    }
}
