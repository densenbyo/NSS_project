package cz.cvut.fel.ear.lingo.content.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
@Table(name = "CONTEXT_SENTENCE_CONTENT")
public class ContextSentenceContentEntity extends AbstractContentEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    private String sentence;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContextSentenceContentEntity that = (ContextSentenceContentEntity) o;
        return Objects.equals(sentence, that.sentence);
    }

    @Override
    public int hashCode() {
        return sentence != null ? sentence.hashCode() : 0;
    }

}