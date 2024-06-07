package dev.senzalla.metakyasshuapi.model;

public interface InterfaceMapper<Dto, Entity, Form, Summarized> {

    Dto toDto(Entity entity);

    Entity toEntity(Form form);

    Summarized toSummarized(Entity entity);
}