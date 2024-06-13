package dev.senzalla.metakyasshuapi.service;


import org.springframework.data.domain.Page;

import java.util.List;

public interface InterfaceService<D, T, F, S> {

    D save(F form, String token);

    D update(Long pk, F form);

    void delete(Long pk);

    D find(Long pk);

    Page<S> findAll(T t);

    List<S> findAll(T t, String token);
}
