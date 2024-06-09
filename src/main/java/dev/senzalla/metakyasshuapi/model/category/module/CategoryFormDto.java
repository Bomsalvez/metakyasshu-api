package dev.senzalla.metakyasshuapi.model.category.module;

import dev.senzalla.metakyasshuapi.model.types.TypeCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * DTO for {@link dev.senzalla.metakyasshuapi.model.category.entity.Category}
 */
@Getter
@Setter
public class CategoryFormDto implements Serializable {
    private Long pkCategory;
    @NotBlank
    private String nameCategory;
    @NotNull
    private TypeCategory typeCategory;
}