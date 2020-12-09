package com.dev.thecat.app.provider.database.postgres.table;

import com.dev.thecat.domain.origin.entity.OriginEntity;
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
@Table(name = "origin")
public class OriginTable {
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

    @OneToMany(mappedBy="origin", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<BreedTable> breeds;

    public OriginEntity toDomain() {
        return OriginEntity.builder()
                .id(id)
                .integrationId(integrationId)
                .description(description)
                .createdAt(createdAt)
                .build();
    }

    public OriginTable fromDomain(OriginEntity originEntity) {
        return OriginTable.builder()
                .id(originEntity.getId() == null ? id : originEntity.getId())
                .integrationId(originEntity.getIntegrationId())
                .description(originEntity.getDescription())
                .createdAt(originEntity.getCreatedAt() == null ? createdAt : originEntity.getCreatedAt())
                .updateAt(updateAt)
                .build();
    }
}
