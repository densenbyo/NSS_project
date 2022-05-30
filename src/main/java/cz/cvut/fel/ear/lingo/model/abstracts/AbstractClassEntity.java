package cz.cvut.fel.ear.lingo.model.abstracts;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractClassEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

}
