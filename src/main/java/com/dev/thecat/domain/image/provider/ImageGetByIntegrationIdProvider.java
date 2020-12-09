package com.dev.thecat.domain.image.provider;

import com.dev.thecat.domain.image.entity.ImageEntity;

public interface ImageGetByIntegrationIdProvider {
    ImageEntity getByIntegrationId(String integrationId);
}
