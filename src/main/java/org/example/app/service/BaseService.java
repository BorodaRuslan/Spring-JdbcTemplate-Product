package org.example.app.service;

public interface BaseService <T>{
    String create(T object);
    String getAll();
    String getById(String id);
    String update(T object);
    String delete(String id);
}
