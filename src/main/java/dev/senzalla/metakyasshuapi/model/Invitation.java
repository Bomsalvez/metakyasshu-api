package dev.senzalla.metakyasshuapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tbl_invitation", schema = "db_metakyasshu")
public class Invitation {
    @Id
    @Column(name = "pkInvitation", nullable = false)
    private Long pkInvitation;

    @Size(max = 255)
    @NotNull
    @Column(name = "codeInvitation", nullable = false)
    private String codeInvitation;

    @NotNull
    @Column(name = "sendDateInvitation", nullable = false)
    private LocalDate sendDateInvitation;

    @Column(name = "acceptanceDateInvitation")
    private LocalDate acceptanceDateInvitation;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fkCollaborator", nullable = false)
    private Collaborator collaborator;

}