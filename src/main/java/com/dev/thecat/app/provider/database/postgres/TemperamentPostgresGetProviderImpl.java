package com.dev.thecat.app.provider.database.postgres;

import com.dev.thecat.app.provider.database.postgres.repository.TemperamentRepository;
import com.dev.thecat.domain.temperament.entity.TemperamentEntity;
import com.dev.thecat.domain.temperament.provider.TemperamentGetByIntegrationIdProvider;

import javax.inject.Named;

@Named("temperamentPostgresGetProvider")
public class TemperamentPostgresGetProviderImpl implements TemperamentGetByIntegrationIdProvider {

    private final TemperamentRepository temperamentRepository;

    public TemperamentPostgresGetProviderImpl(final TemperamentRepository temperamentRepository) {
        this.temperamentRepository = temperamentRepository;
    }

    @Override
    public TemperamentEntity getByIntegrationId(String integrationId) {
        var temperament = this.temperamentRepository.findByIntegrationId(integrationId);
        if (temperament.isEmpty()) {
            return null;
        }
        return temperament.get().toDomain();
    }
}
