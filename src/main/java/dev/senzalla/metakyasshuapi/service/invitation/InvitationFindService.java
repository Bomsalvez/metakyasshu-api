package dev.senzalla.metakyasshuapi.service.invitation;

import dev.senzalla.metakyasshuapi.model.invitation.entity.Invitation;
import dev.senzalla.metakyasshuapi.model.invitation.mapper.InvitationMapper;
import dev.senzalla.metakyasshuapi.model.invitation.module.InvitationSummarized;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.repository.InvitationRepository;
import dev.senzalla.metakyasshuapi.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class InvitationFindService {
    private final InvitationRepository repository;
    private final InvitationMapper mapper;
    private final UserService userService;

    public Page<InvitationSummarized> getInvitations(String token, Pageable pageable, boolean sent) {
        User user = userService.findByToken(token);
        Page<Invitation> invitations = repository.findInvite(user, sent, pageable);
        return mapper.toSummarized(invitations);
    }
}
