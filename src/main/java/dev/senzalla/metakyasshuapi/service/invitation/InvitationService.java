package dev.senzalla.metakyasshuapi.service.invitation;

import dev.senzalla.metakyasshuapi.model.invitation.module.InvitationSummarized;
import dev.senzalla.metakyasshuapi.model.invitation.module.InvitationForm;
import dev.senzalla.metakyasshuapi.service.InterfaceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class InvitationService implements InterfaceService<InvitationSummarized, Boolean,InvitationForm, InvitationSummarized> {
    private final InvitationSendService sendService;
    private final InvitationFindService findService;


    @Override
    public InvitationSummarized save(InvitationForm form, String token) {
        return sendService.sendInvitation(form, token);
    }

    @Override
    public InvitationSummarized update(Long pk, InvitationForm form) {
        return null;
    }

    @Override
    public void delete(Long pk) {

    }

    @Override
    public InvitationSummarized find(Long pk) {
        return null;
    }

    @Override
    public Page<InvitationSummarized> findAllPage(Boolean sent, String token, Pageable pageable) {
        return findService.getInvitations(token, pageable, sent);
    }

    @Override
    public List<InvitationSummarized> findAllList(Boolean unused, String token) {
        return List.of();
    }
}
