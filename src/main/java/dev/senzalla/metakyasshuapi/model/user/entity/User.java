package dev.senzalla.metakyasshuapi.model.user.entity;

import dev.senzalla.metakyasshuapi.model.Goal;
import dev.senzalla.metakyasshuapi.model.collaborator.entity.Collaborator;
import dev.senzalla.metakyasshuapi.model.expense.entity.Expense;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tbl_user", schema = "db_metakyasshu")
public class User implements UserDetails, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pkUser", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkUser;

    @Size(max = 255)
    @NotNull
    @Column(name = "nameUser", nullable = false)
    private String nameUser;

    @Size(max = 255)
    @NotNull
    @Column(name = "emailUser", nullable = false)
    private String emailUser;

    @Size(max = 255)
    @NotNull
    @Column(name = "passwordUser", nullable = false)
    private String passwordUser;

    @Size(max = 11)
    @NotNull
    @Column(name = "cpfUser", nullable = false, length = 11)
    private String cpfUser;

    @Column(name = "imageUser")
    private byte[] imageUser;

    @NotNull
    @Column(name = "dateCreateUser", nullable = false)
    private LocalDate dateCreateUser;

    @Size(max = 20)
    @NotNull
    @Column(name = "keyUser", nullable = false, length = 20)
    private String keyUser;

    @NotNull
    @ColumnDefault("0")
    @Column(name = "confirmedUser", nullable = false)
    private boolean confirmedUser = false;

    @OneToMany(mappedBy = "userCollaborator")
    private Set<Collaborator> userCollaborator;

    @OneToMany(mappedBy = "userHost")
    private Set<Collaborator> userHosters;

    @OneToMany(mappedBy = "user")
    private Set<Expense> expenses;

    @OneToMany(mappedBy = "user")
    private Set<Goal> goals;

    @Transient
    private Set<Permission> authorities;

    @Transient
    private Integer numberExpenses;

    @Transient
    private Integer numberGoals;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return passwordUser;
    }

    @Override
    public String getUsername() {
        return nameUser;
    }

    @Override
    public boolean isEnabled() {
        return confirmedUser;
    }

    @PrePersist
    public void prePersist() {
        dateCreateUser = LocalDate.now();
    }
}
