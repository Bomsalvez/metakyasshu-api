package dev.senzalla.metakyasshuapi.model.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tbl_user", schema = "db_metakyasshu")
public class User implements UserDetails {
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

    @Transient
    private Set<Permission> authorities;

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
