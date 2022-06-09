package cz.cvut.fel.ear.lingo.model.contents;

import cz.cvut.fel.ear.lingo.model.abstracts.AbstractContent;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Objects;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
@Getter
@Setter
public class ImageContent extends AbstractContent {

    @Basic(optional = false)
    @Column(nullable = false)
    private String sourceURI;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageContent that = (ImageContent) o;
        return Objects.equals(sourceURI, that.sourceURI);
    }

    @Override
    public int hashCode() {
        return sourceURI != null ? sourceURI.hashCode() : 0;
    }

}