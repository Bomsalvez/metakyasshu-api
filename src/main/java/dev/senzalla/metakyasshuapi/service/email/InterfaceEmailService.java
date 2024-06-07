package dev.senzalla.metakyasshuapi.service.email;

public interface InterfaceEmailService<Entity> {

    void sendEmail(Entity entity);

    String defineHtmlBodyEmail(Entity entity);
}
