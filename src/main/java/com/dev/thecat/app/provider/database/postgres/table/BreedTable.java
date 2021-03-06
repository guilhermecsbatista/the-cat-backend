package com.dev.thecat.app.provider.database.postgres.table;

import com.dev.thecat.domain.breed.entity.BreedEntity;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "breed")
public class BreedTable {
  @Id
  private UUID id = UUID.randomUUID();

  @Column(name = "integration_id")
  private String integrationId;

  @Column
  private String name;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @JoinColumn(name = "origin_id", nullable = false)
  private OriginTable origin;

  @OneToMany(mappedBy = "breed", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,
      CascadeType.PERSIST})
  private List<BreedTemperamentTable> temperaments;

  @OneToMany(mappedBy = "breed", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,
      CascadeType.PERSIST})
  private List<BreedImageTable> images;

  @Column(name = "created_at", nullable = false, updatable = false)
  @CreationTimestamp
  private Date createdAt = new Date();

  @Column(name = "update_at", nullable = false)
  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  private Date updateAt = new Date();

  public BreedEntity toDomain() {
    return BreedEntity.builder()
        .id(id)
        .integrationId(integrationId)
        .name(name)
        .origin(origin.toDomain())
        .temperaments(
            temperaments.stream().map(BreedTemperamentTable::toDomain).collect(Collectors.toList()))
        .images(images.stream().map(BreedImageTable::toDomain).collect(Collectors.toList()))
        .build();
  }

  public BreedTable fromDomain(BreedEntity breedEntity) {
    return BreedTable.builder()
        .id(id)
        .integrationId(breedEntity.getIntegrationId())
        .name(breedEntity.getName())
        .origin(new OriginTable().fromDomain(breedEntity.getOrigin()))
        .images(
            breedEntity.getImages().stream().map(e -> new BreedImageTable().fromDomain(id, e))
                .collect(Collectors.toList()))
        .temperaments(
            breedEntity.getTemperaments().stream()
                .map(e -> new BreedTemperamentTable().fromDomain(id, e))
                .collect(Collectors.toList()))
        .createdAt(createdAt)
        .updateAt(updateAt)
        .build();
  }
}
