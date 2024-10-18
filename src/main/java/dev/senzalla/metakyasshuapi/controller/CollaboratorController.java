package dev.senzalla.metakyasshuapi.controller;

import dev.senzalla.metakyasshuapi.model.collaborator.module.CollaboratorDto;
import dev.senzalla.metakyasshuapi.model.collaborator.module.CollaboratorFilter;
import dev.senzalla.metakyasshuapi.model.collaborator.module.CollaboratorSummarized;
import dev.senzalla.metakyasshuapi.model.types.AccessLevel;
import dev.senzalla.metakyasshuapi.service.collaborator.CollaboratorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collaborator")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CollaboratorController {
    private final CollaboratorService service;

    @Operation(summary = "List all collaborators")
    @GetMapping
    public ResponseEntity<Page<CollaboratorSummarized>> listCollaborator(
            @ModelAttribute("CollaboratorFilter") CollaboratorFilter filter,
            @RequestHeader(value = "Authorization") String token,
            @SortDefault Pageable pageable) {
        Page<CollaboratorSummarized> collaborator = service.findAllPage(filter, token, pageable);
        return ResponseEntity.ok().body(collaborator);
    }

    @Operation(summary = "Get a collaborator by its primary key")
    @GetMapping("/{pk}")
    public ResponseEntity<CollaboratorDto> getCollaborator(@PathVariable Long pk) {
        CollaboratorDto collaborator = service.find(pk);
        return ResponseEntity.ok().body(collaborator);
    }

    @Operation(summary = "Update a collaborator's access level")
    @PatchMapping("/{pk}")
    public ResponseEntity<CollaboratorDto> updateCollaborator(@PathVariable Long pk, @RequestParam AccessLevel accessLevel) {
        CollaboratorDto collaborator = service.update(pk, accessLevel);
        return ResponseEntity.ok().body(collaborator);
    }

    @Operation(summary = "Delete a collaborator by its primary key")
    @DeleteMapping("/{pk}")
    public ResponseEntity<Void> deleteCollaborator(@PathVariable Long pk) {
        service.delete(pk);
        return ResponseEntity.noContent().build();
    }
}