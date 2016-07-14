package com.company.todo.dao.http.stub.proto;

import com.company.todo.model.proto.DataItem;

import java.util.Optional;
import java.util.SortedSet;

/**
 * Created by Yevhen on 13.07.2016.
 */

public class HttpStubUniqueNameDao<T extends DataItem> extends HttpStubDao<T> {
    protected T saveOrUpdate(String name) {
        T result = null;

        SortedSet<T> dataItemSet = readDataItemSet();
        if (dataItemSet != null) {
            Optional<T> tOptional = dataItemSet.stream().filter(t -> (t.getName().equals(name))).findFirst();
            if (tOptional.isPresent()) {
                result = tOptional.get();
            }
        }
        if (result == null) {
            result = super.saveOrUpdate(name);
        }

        return result;
    }
}
