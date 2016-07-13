package com.company.todo.dao.http.stub.proto;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Yevhen on 13.07.2016.
 */
public class GenericHolder<T> {
    private Class<T> entityClass;

    private Class<T> getGenericClass() {
        return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).
                getActualTypeArguments()[0]);
    }

    private Class<T> getEntityClass() {
        if (entityClass == null) {
            entityClass = getGenericClass();
        }

        return entityClass;
    }

    protected String getEntityName() {
        return getEntityClass().getName();
    }
}
