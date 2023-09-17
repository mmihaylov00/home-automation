package com.homeAutomation.extension.database.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.*;
import org.hibernate.proxy.HibernateProxyHelper;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@MappedSuperclass
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonType.class)
})
@SuperBuilder
public abstract class BaseEntity<ID extends Serializable> implements Serializable, DeletableEntity {
    @Transient
    public abstract ID getId();

    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    @Builder.Default
    private OffsetDateTime createdDate = OffsetDateTime.now();

    @UpdateTimestamp
    @Column(name = "modified_date")
    @Builder.Default
    private OffsetDateTime modifiedDate = OffsetDateTime.now();

    @Builder.Default
    @Column(columnDefinition = "boolean default false")
    private boolean deleted = false;

    @Override
    public void delete() {
        this.deleted = true;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        else if (o == null) return false;

        Class<?> otherClass = HibernateProxyHelper.getClassWithoutInitializingProxy(o);
        if (this.getClass() != otherClass) {
            return false;
        }

        BaseEntity<?> that = (BaseEntity<?>) o;
        return this.getId() != null ? this.getId().equals(that.getId()) : super.equals(o);
    }

    public int hashCode() {
        return this.getId() != null ? this.getId().hashCode() : 0;
    }
}
