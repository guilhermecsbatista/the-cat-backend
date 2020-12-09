package com.dev.thecat.domain.temperament.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemperamentEntity {
    private UUID id;
    private String integrationId;
    private String description;
    private Date createdAt;
}
