package com.Haulmont.TestProjectHaulmont.base;

public interface CRUDService<T, ID> {

    T getById(ID id);

    T save(T t);

    T update(T t);

    void delete(ID id);
}