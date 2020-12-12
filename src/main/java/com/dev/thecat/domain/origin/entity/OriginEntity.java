package com.dev.thecat.domain.origin.entity;

import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OriginEntity {
  private UUID id;
  private String integrationId;
  private String description;
  private Date createdAt;
}
