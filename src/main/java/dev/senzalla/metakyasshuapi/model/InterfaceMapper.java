package dev.senzalla.metakyasshuapi.model;

import org.springframework.data.domain.Page;

public interface InterfaceMapper<D, E, F, S> {

    D toDto(E e);

    E toEntity(F f);

    Page<S> toSummarized(Page<E> e);
}