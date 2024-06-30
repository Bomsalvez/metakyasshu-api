package dev.senzalla.metakyasshuapi.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InterfaceService<D, T, F, S> {

    D save(F form, String token);

    D update(Long pk, F form);

    void delete(Long pk);

    D find(Long pk);

    Page<S> findAllPage(T t, String token, Pageable pageable);

//    List<S> findAllList(T t, String token);
}
