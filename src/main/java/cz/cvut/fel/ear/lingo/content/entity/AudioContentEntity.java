package cz.cvut.fel.ear.lingo.content.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;
@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
@Table(name = "AUDIO_CONTENT")
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
