package dev.senzalla.metakyasshuapi.model.category.entity;

import dev.senzalla.metakyasshuapi.model.types.TypeCategory;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tbl_category", schema = "db_metakyasshu")
public class Category {
    @Id
    @Column(name = "pkCategory", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkCategory;

    @Size(max = 255)
    @NotNull
    @Column(name = "nameCategory", nullable = false)
    private String nameCategory;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "typeCategory", nullable = false)
    private TypeCategory typeCategory;

    @ManyToMany
    @JoinTable(name = "tbl_category_user", schema = "db_metakyasshu",
            joinColumns = @JoinColumn(name = "fkCategory", referencedColumnName = "pkCategory"),
            inverseJoinColumns = @JoinColumn(name = "fkUser", referencedColumnName = "pkUser"))
    private Set<User> users;

}