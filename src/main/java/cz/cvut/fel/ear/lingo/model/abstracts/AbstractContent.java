package cz.cvut.fel.ear.lingo.model.abstracts;

import com.fasterxml.jackson.annotation.JsonView;
import cz.cvut.fel.ear.lingo.model.User;
import cz.cvut.fel.ear.lingo.model.util.Views;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractContent extends AbstractClass {

    @ManyToOne
    @JsonView(Views.Public.class)
    private User creator;

}