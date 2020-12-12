package com.dev.thecat.app.provider.database.postgres.table;

import com.dev.thecat.domain.image.entity.ImageEntity;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "image")
public class ImageTable {
  @Id
  private UUID id = UUID.randomUUID();

  @Column(name = "integration_id")
  private String integrationId;

  @Column
  private String url;

  @Column(name = "created_at", nullable = false, updatable = false)
  @CreationTimestamp
  private Date createdAt = new Date();

  @Column(name = "update_at", nullable = false)
  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date updateAt = new Date();

  @OneToMany(mappedBy = "image", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,
      CascadeType.PERSIST})
  private List<BreedImageTable> breedsImageTable;

  public ImageEntity toDomain() {
    return ImageEntity.builder()
        .id(id)
        .integrationId(integrationId)
        .url(url)
        .build();
  }

  public ImageTable fromDomain(ImageEntity imageEntity) {
    return ImageTable.builder()
        .id(imageEntity.getId() == null ? id : imageEntity.getId())
        .integrationId(imageEntity.getIntegrationId())
        .url(imageEntity.getUrl())
        .createdAt(imageEntity.getCreatedAt() == null ? createdAt : imageEntity.getCreatedAt())
        .updateAt(updateAt)
        .build();
  }
}
