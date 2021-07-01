package ru.vtb.rama.swagerrest.model.base;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
@QueryEntity
@Data
public abstract class BaseEntity implements IEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_created")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "date_updated")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // todo: добавить автора
}
