package dev.senzalla.metakyasshuapi.service.email;

import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.service.tools.MessageDecode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailUserRecoverService  extends SendEmailService implements InterfaceEmailService<User> {
    private final MessageDecode messageDecode;

    public EmailUserRecoverService(JavaMailSender mailSender, MessageDecode messageDecode) {
        super(mailSender);
        this.messageDecode = messageDecode;
    }

    @Value("${host.confirmation_url}")
    private String host;

    @Override
    public void sendEmail(User user) {

    }

    @Override
    public String defineHtmlBodyEmail(User user) {
        return "";
    }
}
