package com.dev.thecat.domain.origin.provider;

import com.dev.thecat.domain.origin.entity.OriginEntity;

public interface OriginGetByIntegrationIdProvider {
  OriginEntity getByIntegrationId(String integrationId);
}
