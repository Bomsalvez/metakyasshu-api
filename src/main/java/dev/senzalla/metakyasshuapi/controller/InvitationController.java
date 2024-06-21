package dev.senzalla.metakyasshuapi.controller;

import dev.senzalla.metakyasshuapi.model.invitation.module.InvitationForm;
import dev.senzalla.metakyasshuapi.service.invitation.InvitationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invitation")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class InvitationController {
    private final InvitationService invitationService;

    @PostMapping
    public ResponseEntity<Void> sendInvitation(@RequestBody @Validated InvitationForm invitationForm, @RequestHeader("Authorization") String token) {
        invitationService.sendInvitation(invitationForm, token);
        return ResponseEntity.ok().build();
    }
}
