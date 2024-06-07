package dev.senzalla.metakyasshuapi.service.email;

import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.service.tools.MessageDecode;
import dev.senzalla.metakyasshuapi.service.tools.Thymeleaf;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
class EmailUserCreateService extends SendEmailService implements InterfaceEmailService<User> {
    private final MessageDecode messageDecode;

    public EmailUserCreateService(JavaMailSender mailSender, MessageDecode messageDecode) {
        super(mailSender);
        this.messageDecode = messageDecode;
    }

    @Value("${host.confirmation_url}")
    private String host;

    @Override
    public void sendEmail(User user) {
        String message = messageDecode.info("info.create-account");
        String html = defineHtmlBodyEmail(user);
        super.sendEmail(user, message, html);
    }

    @Override
    public String defineHtmlBodyEmail(User user) {
        Map<String, Object> variablesHtml = super.defineUsersVariables(user, host);
        return new Thymeleaf().createContext("CreateAccount.html", variablesHtml);
    }
}
