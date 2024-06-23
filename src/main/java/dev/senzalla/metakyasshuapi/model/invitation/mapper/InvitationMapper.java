package dev.senzalla.metakyasshuapi.model.invitation.mapper;

import dev.senzalla.metakyasshuapi.model.InterfaceMapper;
import dev.senzalla.metakyasshuapi.model.invitation.entity.Invitation;
import dev.senzalla.metakyasshuapi.model.invitation.module.InvitationSummarized;
import dev.senzalla.metakyasshuapi.model.user.mapper.UserMapper;
import dev.senzalla.metakyasshuapi.model.user.module.UserSummarized;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class InvitationMapper implements InterfaceMapper<InvitationSummarized, Invitation, Void, InvitationSummarized> {
    private final UserMapper userMapper;

    @Override
    public InvitationSummarized toDto(Invitation invitation) {
        UserSummarized userHost = userMapper.toSummarized(invitation.getCollaborator().getUserHost());
        UserSummarized userCollaborator = userMapper.toSummarized(invitation.getCollaborator().getUserCollaborator());
        InvitationSummarized invitationSummarized = new InvitationSummarized();
        invitationSummarized.setPkInvitation(invitation.getPkInvitation());
        invitationSummarized.setSendDateInvitation(invitation.getSendDateInvitation());
        invitationSummarized.setUserHost(userHost);
        invitationSummarized.setUserCollaborator(userCollaborator);
        return invitationSummarized;
    }

    @Override
    public Invitation toEntity(Void unused) {
        return null;
    }

    @Override
    public Page<InvitationSummarized> toSummarized(Page<Invitation> e) {
        return e.map(this::toDto);
    }
}
