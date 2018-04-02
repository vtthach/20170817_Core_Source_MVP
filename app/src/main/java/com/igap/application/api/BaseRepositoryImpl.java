package com.igap.application.api;

public abstract class BaseRepositoryImpl<T> {

    protected T service;

    public BaseRepositoryImpl(T service) {
        this.service = service;
    }
}
