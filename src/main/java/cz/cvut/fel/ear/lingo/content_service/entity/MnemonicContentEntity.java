package cz.cvut.fel.ear.lingo.content_service.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.util.Objects;

@Data
@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
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