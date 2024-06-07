package dev.senzalla.metakyasshuapi.service.tools;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MessageDecode {
    private MessageSource messageSource;

    public static String decodeUnique(String error) {
        String message = error.substring(error.indexOf("uk_") + 3, error.length() - 1);
        return removeEntitySuffix(message);
    }

    public static String removeEntitySuffix(String input) {
        String phrase = input.replaceAll("([a-z0-9])([A-Z])", "$1 $2");
        String[] words = phrase.split(" ");
        return words[0];
    }

    public String info(String code) {
        return messageSource.getMessage(code, null, LocaleContextHolder.getLocale());
    }

    public String extractMessage(String message) {
        return message.substring(message.indexOf("[") + 1, message.indexOf("]"));
    }
}
