package com.dev.thecat.app.provider.database.postgres.table;

import com.dev.thecat.domain.image.entity.ImageEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "breed_image")
public class BreedImageTable {
    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @JoinColumn(name = "breed_id", nullable = false)
    private BreedTable breed;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @JoinColumn(name = "image_id", nullable = false)
    private ImageTable image;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt = new Date();

    @Column(name = "update_at", nullable = false)
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt = new Date();

    public ImageEntity toDomain() {
        return ImageEntity.builder()
                .id(image.getId())
                .integrationId(image.getIntegrationId())
                .url(image.getUrl())
                .build();
    }

    public BreedImageTable fromDomain(UUID breedId, ImageEntity imageEntity) {
        return BreedImageTable.builder()
                .id(id)
                .breed(BreedTable.builder().id(breedId).build())
                .image(new ImageTable().fromDomain(imageEntity))
                .createdAt(createdAt)
                .updateAt(updateAt)
                .build();
    }
}
