package com.dev.thecat.domain.image.entity;

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
public class ImageEntity {
    private UUID id;
    private String integrationId;
    private String url;
    private Date createdAt;
}
