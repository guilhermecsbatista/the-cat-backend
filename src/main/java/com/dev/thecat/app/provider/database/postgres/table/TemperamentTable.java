package com.dev.thecat.app.provider.database.postgres.table;

import com.dev.thecat.domain.temperament.entity.TemperamentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "temperament")
public class TemperamentTable {
    @Id
    private UUID id = UUID.randomUUID();

    @Column(name = "integration_id")
    private String integrationId;

    @Column
    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt = new Date();

    @Column(name = "update_at", nullable = false)
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt = new Date();

    @OneToMany(mappedBy="temperament", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<BreedTemperamentTable> breedsTemperamentTable;

    public TemperamentEntity toDomain() {
        return TemperamentEntity.builder()
                .id(id)
                .integrationId(integrationId)
                .description(description)
                .createdAt(createdAt)
                .build();
    }

    public TemperamentTable fromDomain(TemperamentEntity temperamentEntity) {
        return TemperamentTable.builder()
                .id(temperamentEntity.getId() == null ? id : temperamentEntity.getId())
                .integrationId(temperamentEntity.getIntegrationId())
                .description(temperamentEntity.getDescription())
                .createdAt(temperamentEntity.getCreatedAt() == null ? createdAt : temperamentEntity.getCreatedAt())
                .updateAt(updateAt)
                .build();
    }
}
