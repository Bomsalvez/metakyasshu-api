package dev.senzalla.metakyasshuapi.model;

import dev.senzalla.metakyasshuapi.model.types.AccessLevel;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tbl_collaborator", schema = "db_metakyasshu")
public class Collaborator {
    @Id
    @Column(name = "pkCollaborator", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkCollaborator;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "accessLevelCollaborator", nullable = false)
    private AccessLevel accessLevel;

    @Size(max = 20)
    @NotNull
    @Column(name = "codeCollaborator", nullable = false, length = 20)
    private String codeCollaborator;

    @Column(name = "acceptDateCollaborator")
    private LocalDate acceptDateCollaborator;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fkUserCollaborator", nullable = false)
    private User userCollaborator;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fkUserHost", nullable = false)
    private User userHost;

}