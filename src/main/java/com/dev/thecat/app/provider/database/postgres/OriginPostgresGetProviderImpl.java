package com.dev.thecat.app.provider.database.postgres;

import com.dev.thecat.app.provider.database.postgres.repository.OriginRepository;
import com.dev.thecat.domain.origin.entity.OriginEntity;
import com.dev.thecat.domain.origin.provider.OriginGetByIntegrationIdProvider;
import javax.inject.Named;

@Named("originPostgresGetProvider")
public class OriginPostgresGetProviderImpl implements OriginGetByIntegrationIdProvider {

  private final OriginRepository originRepository;

  public OriginPostgresGetProviderImpl(final OriginRepository originRepository) {
    this.originRepository = originRepository;
  }

  @Override
  public OriginEntity getByIntegrationId(String integrationId) {
    var origin = this.originRepository.findByIntegrationId(integrationId);
    if (origin.isEmpty()) {
      return null;
    }
    return origin.get().toDomain();
  }
}
