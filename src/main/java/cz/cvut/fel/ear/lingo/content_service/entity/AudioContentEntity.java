package cz.cvut.fel.ear.lingo.content_service.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Objects;
@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class AudioContentEntity extends AbstractContentEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    private String sourceURI;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AudioContentEntity content = (AudioContentEntity) o;
        return Objects.equals(sourceURI, content.sourceURI);
    }

    @Override
    public int hashCode() {
        return sourceURI != null ? sourceURI.hashCode() : 0;
    }

}
