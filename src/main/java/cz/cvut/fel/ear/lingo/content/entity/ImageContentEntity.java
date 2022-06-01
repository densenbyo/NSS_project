package cz.cvut.fel.ear.lingo.content.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
@Table(name = "IMAGE_CONTENT")
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