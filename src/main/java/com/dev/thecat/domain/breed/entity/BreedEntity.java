package com.dev.thecat.domain.breed.entity;

import com.dev.thecat.domain.image.entity.ImageEntity;
import com.dev.thecat.domain.origin.entity.OriginEntity;
import com.dev.thecat.domain.temperament.entity.TemperamentEntity;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BreedEntity {
  private UUID id;
  private String integrationId;
  private String name;
  private OriginEntity origin;
  private List<TemperamentEntity> temperaments;
  private List<ImageEntity> images;
  private Date createdAt;
}
