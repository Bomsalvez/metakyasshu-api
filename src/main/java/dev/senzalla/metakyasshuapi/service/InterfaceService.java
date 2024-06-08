package dev.senzalla.metakyasshuapi.service;

import org.springframework.data.domain.Page;

import java.util.List;

public interface InterfaceService<Dto, Filter, Form, Summarized> {

    void save(Form form);

    void update(Long pk, Form form);

    void delete(Long pk);

    Dto find(Long pk);

    Page<Summarized> findAll(Filter filter);

    List<Summarized> findAll();
}
