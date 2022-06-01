package cz.cvut.fel.ear.lingo.user.entity;

import com.fasterxml.jackson.annotation.JsonView;
import cz.cvut.fel.ear.lingo.flashcard.entity.FlashcardEntity;
import cz.cvut.fel.ear.lingo.model.abstracts.AbstractClassEntity;
import cz.cvut.fel.ear.lingo.model.util.Views;
import cz.cvut.fel.ear.lingo.repo.entity.RepoEntity;
import cz.cvut.fel.ear.lingo.statistics.entity.StatisticEntity;
import cz.cvut.fel.ear.lingo.user.entity.enumeration.UserRole;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "POCKET_USER")
public class UserEntity extends AbstractClassEntity {

    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    @JsonView(Views.Public.class)
    private String username;

    @Basic(optional = false)
    @Column(nullable = false)
    @JsonView(Views.Public.class)
    private String password;

    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    @JsonView(Views.Public.class)
    private String mail;

    @Basic(optional = false)
    @Column(nullable = false)
    @JsonView(Views.Public.class)
    private Boolean isActive;

    @Basic(optional = false)
    @Column(nullable = false)
    @JsonView(Views.Public.class)
    private Boolean isRemoved;

    @Enumerated(EnumType.STRING)
    @JsonView(Views.Public.class)
    private UserRole role;

    @OneToOne(cascade = CascadeType.ALL)
    private RepoEntity repo;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonView(Views.Public.class)
    private StatisticEntity statistic;

    public void addFlashcard(FlashcardEntity flashcardEntity) {
        repo.addFlashcard(flashcardEntity);
        statistic.addFlashcard(flashcardEntity);
    }

    public void removeFlashcard(FlashcardEntity flashcardEntity) {
        repo.removeFlashcard(flashcardEntity);
        statistic.removeFlashcard(flashcardEntity);
    }

    public void encodePassword(PasswordEncoder encoder) {
        this.password = encoder.encode(password);
    }

//    public void erasePassword() {
//        this.password = null;
//    }
//
//    public boolean isAdmin() {
//        return role == UserRole.ADMIN;
//    }

    public boolean isUser() {
        return role == UserRole.USER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity user = (UserEntity) o;
        if (!Objects.equals(username, user.username)) return false;
        if (!Objects.equals(password, user.password)) return false;
        return Objects.equals(mail, user.mail);
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        return result;
    }
}