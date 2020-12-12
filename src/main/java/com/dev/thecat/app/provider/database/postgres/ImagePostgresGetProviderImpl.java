package com.dev.thecat.app.provider.database.postgres;

import com.dev.thecat.app.provider.database.postgres.repository.ImageRepository;
import com.dev.thecat.domain.image.entity.ImageEntity;
import com.dev.thecat.domain.image.provider.ImageGetByIntegrationIdProvider;
import javax.inject.Named;

@Named("imagePostgresGetProvider")
public class ImagePostgresGetProviderImpl implements ImageGetByIntegrationIdProvider {

  private final ImageRepository imageRepository;

  public ImagePostgresGetProviderImpl(final ImageRepository imageRepository) {
    this.imageRepository = imageRepository;
  }

  @Override
  public ImageEntity getByIntegrationId(String integrationId) {
    var image = this.imageRepository.findByIntegrationId(integrationId);
    if (image.isEmpty()) {
      return null;
    }
    return image.get().toDomain();
  }
}
