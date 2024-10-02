package dev.senzalla.metakyasshuapi.service.invitation;

import dev.senzalla.metakyasshuapi.model.invitation.entity.Invitation;
import dev.senzalla.metakyasshuapi.model.invitation.mapper.InvitationMapper;
import dev.senzalla.metakyasshuapi.model.invitation.module.InvitationDto;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.repository.InvitationRepository;
import dev.senzalla.metakyasshuapi.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class InvitationAcceptService {
    private final InvitationFindService findService;
    private final InvitationRepository repository;
    private final InvitationMapper mapper;
    private final UserService userService;

    public InvitationDto acceptInvitation(Long pk, String token) {
        User user = userService.findByToken(token);
        Optional<Invitation> invitation = findService.findInvitationByUserCollaborator(pk, user);
        Invitation invitationEntity = invitation.get();
        invitationEntity.setAcceptanceDateInvitation(LocalDate.now());
        invitationEntity.getCollaborator().setAcceptDateCollaborator(LocalDate.now());
        repository.save(invitationEntity);
        return mapper.toDto(invitationEntity);
    }
}
