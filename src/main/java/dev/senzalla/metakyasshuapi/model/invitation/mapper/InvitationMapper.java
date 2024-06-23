package dev.senzalla.metakyasshuapi.model.invitation.mapper;

import dev.senzalla.metakyasshuapi.model.InterfaceMapper;
import dev.senzalla.metakyasshuapi.model.invitation.entity.Invitation;
import dev.senzalla.metakyasshuapi.model.invitation.module.InvitationDto;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.model.user.mapper.UserMapper;
import dev.senzalla.metakyasshuapi.model.user.module.UserSummarized;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class InvitationMapper implements InterfaceMapper<InvitationDto, Invitation, Void, InvitationDto> {
    private final UserMapper userMapper;

    @Override
    public InvitationDto toDto(Invitation invitation) {
        UserSummarized userHost = userMapper.toSummarized(invitation.getCollaborator().getUserHost());
        UserSummarized userCollaborator = userMapper.toSummarized(invitation.getCollaborator().getUserCollaborator());
        InvitationDto invitationDto = new InvitationDto();
        invitationDto.setPkInvitation(invitation.getPkInvitation());
        invitationDto.setSendDateInvitation(invitation.getSendDateInvitation());
        invitationDto.setUserHost(userHost);
        invitationDto.setUserCollaborator(userCollaborator);
        return invitationDto;
    }

    @Override
    public Invitation toEntity(Void unused) {
        return null;
    }

    @Override
    public Page<InvitationDto> toSummarized(Page<Invitation> e) {
        return null;
    }
}
