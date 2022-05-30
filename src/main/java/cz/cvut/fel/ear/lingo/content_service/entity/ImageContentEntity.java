package cz.cvut.fel.ear.lingo.content_service.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Objects;

@Data
@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class ImageContentEntity extends AbstractContentEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    private String sourceURI;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageContentEntity that = (ImageContentEntity) o;
        return Objects.equals(sourceURI, that.sourceURI);
    }

    @Override
    public int hashCode() {
        return sourceURI != null ? sourceURI.hashCode() : 0;
    }

}