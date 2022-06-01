package cz.cvut.fel.ear.lingo.content.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
@Table(name = "MNEMONIC_CONTENT")
public class MnemonicContentEntity extends AbstractContentEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    private String mnemonic;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MnemonicContentEntity that = (MnemonicContentEntity) o;
        return Objects.equals(mnemonic, that.mnemonic);
    }

    @Override
    public int hashCode() {
        return mnemonic != null ? mnemonic.hashCode() : 0;
    }

}