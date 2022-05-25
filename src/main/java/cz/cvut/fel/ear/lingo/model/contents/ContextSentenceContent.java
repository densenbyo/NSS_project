package cz.cvut.fel.ear.lingo.model.contents;

import cz.cvut.fel.ear.lingo.model.abstracts.AbstractContent;
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
public class ContextSentenceContent extends AbstractContent {

    @Basic(optional = false)
    @Column(nullable = false)
    private String sentence;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContextSentenceContent that = (ContextSentenceContent) o;
        return Objects.equals(sentence, that.sentence);
    }

    @Override
    public int hashCode() {
        return sentence != null ? sentence.hashCode() : 0;
    }

}