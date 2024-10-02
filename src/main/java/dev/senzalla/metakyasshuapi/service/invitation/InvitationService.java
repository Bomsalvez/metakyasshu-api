package dev.senzalla.metakyasshuapi.service.invitation;

import dev.senzalla.metakyasshuapi.model.invitation.module.InvitationDto;
import dev.senzalla.metakyasshuapi.model.invitation.module.InvitationForm;
import dev.senzalla.metakyasshuapi.model.invitation.module.InvitationSummarized;
import dev.senzalla.metakyasshuapi.service.InterfaceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class InvitationService implements InterfaceService<InvitationDto, Boolean, InvitationForm, InvitationSummarized> {
    private final InvitationSendService sendService;
    private final InvitationFindService findService;
    private final InvitationAcceptService acceptService;


    @Override
    public InvitationDto save(InvitationForm form, String token) {
        return sendService.sendInvitation(form, token);
    }

    @Override
    public InvitationDto update(Long pk, InvitationForm form) {
        return null;
    }

    @Override
    public void delete(Long pk) {

    }

    @Override
    public InvitationDto find(Long pk) {
        return findService.findInvitation(pk);
    }

    @Override
    public Page<InvitationSummarized> findAllPage(Boolean sent, String token, Pageable pageable) {
        return findService.getInvitations(token, pageable, sent);
    }

    public InvitationDto accept(Long pk, String token) {
        return acceptService.acceptInvitation(pk, token);
    }
}
