package dev.senzalla.metakyasshuapi.service.invitation;

import dev.senzalla.metakyasshuapi.model.invitation.entity.Invitation;
import dev.senzalla.metakyasshuapi.model.invitation.mapper.InvitationMapper;
import dev.senzalla.metakyasshuapi.model.invitation.module.InvitationDto;
import dev.senzalla.metakyasshuapi.model.invitation.module.InvitationSummarized;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.repository.InvitationRepository;
import dev.senzalla.metakyasshuapi.service.tools.MessageDecode;
import dev.senzalla.metakyasshuapi.service.user.UserService;
import dev.senzalla.metakyasshuapi.settings.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class InvitationFindService {
    private final InvitationRepository repository;
    private final MessageDecode messageDecode;
    private final InvitationMapper mapper;
    private final UserService userService;

    public Page<InvitationSummarized> getInvitations(String token, Pageable pageable, boolean sent) {
        User user = userService.findByToken(token);
        Page<Invitation> invitations = repository.findInvite(user, sent, pageable);
        return mapper.toSummarized(invitations);
    }

    public InvitationDto findInvitation(Long pk) {
        Optional<Invitation> invitation = findInvitationEntity(pk);
        checkInvitation(invitation);
        return mapper.toDto(invitation.get());
    }

    public Optional<Invitation> findInvitationEntity(Long pk) {
        Optional<Invitation> invitation = repository.findById(pk);
        checkInvitation(invitation);
        return invitation;
    }

    private void checkInvitation(Optional<Invitation> invitation) {
        if (invitation.isEmpty()) {
            String message = messageDecode.getMessage("entity.invite");
            throw new NotFoundException("error.not-found", message);
        }
    }

    public Optional<Invitation> findInvitationByUserCollaborator(Long pkInvitation, User user) {
        Optional<Invitation> invitation = repository.findByPkInvitationAndCollaborator(pkInvitation, user);
        checkInvitation(invitation);
        return invitation;

    }
}
