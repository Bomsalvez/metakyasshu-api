package dev.senzalla.metakyasshuapi.controller;

import dev.senzalla.metakyasshuapi.model.invitation.module.InvitationDto;
import dev.senzalla.metakyasshuapi.model.invitation.module.InvitationForm;
import dev.senzalla.metakyasshuapi.service.invitation.InvitationService;
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

    @PostMapping
    public ResponseEntity<Void> sendInvitation(@RequestBody @Validated InvitationForm invitationForm, @RequestHeader("Authorization") String token) {
        service.sendInvitation(invitationForm, token);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<InvitationDto>> listInvitations(
            @RequestHeader("Authorization") String token,
            @SortDefault("sendDateInvitation") Pageable pageable,
            @RequestParam(defaultValue = "false") boolean sent) {
        Page<InvitationDto> invitations = service.getInvitations(token, pageable, sent);
        return ResponseEntity.ok().body(invitations);
    }
}
