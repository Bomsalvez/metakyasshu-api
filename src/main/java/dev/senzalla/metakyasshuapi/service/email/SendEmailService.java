package dev.senzalla.metakyasshuapi.service.email;

import dev.senzalla.metakyasshuapi.model.invitation.entity.Invitation;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class SendEmailService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    protected void sendEmail(User user, String message, String html) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(emailFrom);
            helper.setTo(user.getEmailUser());
            helper.setSubject(message);
            helper.setText(html, true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new RuntimeException("error.email");
        }
    }


    protected Map<String, Object> defineUsersVariables(User user, String host) {
        Map<String, Object> variablesHtml = new HashMap<>();
        variablesHtml.put("emailUser", user.getEmailUser());
        variablesHtml.put("nameUser", user.getNameUser());
        variablesHtml.put("hashKey", host + user.getKeyUser());
        return variablesHtml;
    }

    protected Map<String, Object> defineInviteVariables(Invitation invitation, String host) {
        Map<String, Object> variablesHtml = new HashMap<>();
        variablesHtml.put("emailGuest", invitation.getCollaborator().getUserCollaborator().getEmailUser());
        variablesHtml.put("nameGuest", invitation.getCollaborator().getUserCollaborator().getNameUser());
        variablesHtml.put("nameHost", invitation.getCollaborator().getUserHost().getNameUser());
        variablesHtml.put("hashKey", host + invitation.getPkInvitation());
        return variablesHtml;
    }
}
