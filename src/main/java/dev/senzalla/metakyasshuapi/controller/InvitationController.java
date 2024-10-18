package dev.senzalla.metakyasshuapi.controller;

import dev.senzalla.metakyasshuapi.model.invitation.module.InvitationDto;
import dev.senzalla.metakyasshuapi.model.invitation.module.InvitationForm;
import dev.senzalla.metakyasshuapi.model.invitation.module.InvitationSummarized;
import dev.senzalla.metakyasshuapi.service.invitation.InvitationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invitation")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class InvitationController {
    private final InvitationService service;

    @Operation(summary = "Send a new invitation")
    @PostMapping
    public ResponseEntity<Void> sendInvitation(@RequestBody @Validated InvitationForm invitationForm, @RequestHeader("Authorization") String token) {
        service.save(invitationForm, token);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "List all invitations")
    @GetMapping
    public ResponseEntity<Page<InvitationSummarized>> listInvitations(
            @RequestHeader("Authorization") String token,
            @SortDefault("sendDateInvitation") Pageable pageable,
            @RequestParam(defaultValue = "false") boolean sent) {
        Page<InvitationSummarized> invitations = service.findAllPage(sent, token, pageable);
        return ResponseEntity.ok().body(invitations);
    }

    @Operation(summary = "Find an invitation by its primary key")
    @GetMapping("/{pk}")
    public ResponseEntity<InvitationDto> findInvitation(@PathVariable Long pk) {
        InvitationDto invitation = service.find(pk);
        return ResponseEntity.ok().body(invitation);
    }

    @Operation(summary = "Accept an invitation")
    @PatchMapping("/{pk}")
    public ResponseEntity<InvitationDto> acceptInvitation(@PathVariable Long pk, @RequestHeader("Authorization") String token) {
        InvitationDto invitation = service.accept(pk, token);
        return ResponseEntity.ok().body(invitation);
    }
}