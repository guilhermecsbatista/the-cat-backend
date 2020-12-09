package com.dev.thecat.domain.temperament.provider;

import com.dev.thecat.domain.temperament.entity.TemperamentEntity;

public interface TemperamentGetByIntegrationIdProvider {
    TemperamentEntity getByIntegrationId(String integrationId);
}
