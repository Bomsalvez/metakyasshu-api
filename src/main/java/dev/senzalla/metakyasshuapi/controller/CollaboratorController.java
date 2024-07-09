package dev.senzalla.metakyasshuapi.controller;

import dev.senzalla.metakyasshuapi.model.collaborator.module.CollaboratorDto;
import dev.senzalla.metakyasshuapi.model.collaborator.module.CollaboratorFilter;
import dev.senzalla.metakyasshuapi.model.collaborator.module.CollaboratorSummarized;
import dev.senzalla.metakyasshuapi.model.types.AccessLevel;
import dev.senzalla.metakyasshuapi.service.collaborator.CollaboratorService;
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

    @GetMapping
    public ResponseEntity<Page<CollaboratorSummarized>> listCollaborator(
            @ModelAttribute("CollaboratorFilter") CollaboratorFilter filter,
            @RequestHeader(value = "Authorization") String token,
            @SortDefault Pageable pageable) {
        Page<CollaboratorSummarized> collaborator = service.findAllPage(filter, token, pageable);
        return ResponseEntity.ok().body(collaborator);
    }

    @GetMapping("/{pk}")
    public ResponseEntity<CollaboratorDto> getCollaborator(@PathVariable Long pk) {
        CollaboratorDto collaborator = service.find(pk);
        return ResponseEntity.ok().body(collaborator);
    }

    @PatchMapping("/{pk}")
    public ResponseEntity<CollaboratorDto> updateCollaborator(@PathVariable Long pk, @RequestParam AccessLevel accessLevel) {
        CollaboratorDto collaborator = service.update(pk, accessLevel);
        return ResponseEntity.ok().body(collaborator);
    }

    @DeleteMapping("/{pk}")
    public ResponseEntity<Void> deleteCollaborator(@PathVariable Long pk) {
        service.delete(pk);
        return ResponseEntity.noContent().build();
    }
}
