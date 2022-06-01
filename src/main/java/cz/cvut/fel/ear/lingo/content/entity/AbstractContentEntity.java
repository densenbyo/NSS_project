package cz.cvut.fel.ear.lingo.content.entity;

import com.fasterxml.jackson.annotation.JsonView;
import cz.cvut.fel.ear.lingo.model.abstracts.AbstractClassEntity;
import cz.cvut.fel.ear.lingo.model.util.Views;
import cz.cvut.fel.ear.lingo.user.entity.UserEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "ABSTRACT_CONTENT")
public abstract class AbstractContentEntity extends AbstractClassEntity {

    @ManyToOne
    @JsonView(Views.Public.class)
    private UserEntity creator;

}