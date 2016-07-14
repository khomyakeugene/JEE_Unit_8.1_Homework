package com.company.todo.dao.http.stub.proto;

import com.company.todo.model.proto.DataItem;
import com.company.util.DataIntegrityException;

import javax.servlet.ServletContext;
import java.util.*;

/**
 * Created by Yevhen on 13.07.2016.
 */
public class HttpStubDao<T extends DataItem> extends GenericHolder<T> {
    private static final String SET_VAR_SUFFIX = "Set";
    private static final String CANNOT_FIND_DATA_ITEM_PATTERN = "<%s>: Cannot find item with id <%d>!";

    private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    private Object getAttribute(String attributeName) {
        return (servletContext == null) ? null : servletContext.getAttribute(attributeName);
    }

    private void setAttribute(String attributeName, Object attributeValue) {
        if (servletContext != null) {
            servletContext.setAttribute(attributeName, attributeValue);
        }
    }

    private String getEntitySetVariableName() {
        return getEntityName() + SET_VAR_SUFFIX;
    }

    private void saveDataItemSet(SortedSet<T> sortedSet) {
        setAttribute(getEntitySetVariableName(), sortedSet);
    }

    private int generateId() {
        SortedSet<T> dataItemSet = readDataItemSet();

        return (dataItemSet != null && !dataItemSet.isEmpty()) ? dataItemSet.last().getId() + 1 : 1;
    }

    @Override
    protected T newObject() {
        T result = super.newObject();
        result.setId(generateId());

        return result;
    }

    private T saveOrUpdate(T dataItem) {
        if (dataItem != null) {
            int dataItemId = dataItem.getId();
            if (dataItemId <= 0) {
                dataItem.setId(generateId());
            }
            SortedSet<T> dataItemSet = readDataItemSet();

            if (!dataItemSet.add(dataItem)) {
                T oldDataItem = findDataItem(dataItemId);
                if (oldDataItem == null) {
                    throw new DataIntegrityException(String.format(CANNOT_FIND_DATA_ITEM_PATTERN, getEntityName(),
                            dataItemId));
                } else {
                    dataItemSet.remove(oldDataItem);
                    dataItemSet.add(dataItem);
                }
            }

            saveDataItemSet(dataItemSet);
        }

        return dataItem;
    }

    protected T saveOrUpdate(String name) {
        T dataItem = newObject();
        dataItem.setName(name);

        return saveOrUpdate(dataItem);
    }

    protected SortedSet<T> readDataItemSet() {
        SortedSet<T> result = (SortedSet<T>)getAttribute(getEntitySetVariableName());

        return (result == null) ? new TreeSet<>() : result;
    }

    protected List<T> findAllDataItems() {
        return new ArrayList<>(readDataItemSet());
    }

    protected T addDataItem(T dataItem) {
        dataItem.setId(generateId());

        return saveOrUpdate(dataItem);
    }

    protected T findDataItem(int dataItemId) {
        T result = null;

        SortedSet<T> dataItemSet = readDataItemSet();
        if (dataItemSet != null) {
            Optional<T> tOptional = dataItemSet.stream().filter(t -> (t.getId() == dataItemId)).findFirst();
            if (tOptional.isPresent()) {
                result = tOptional.get();
            }
        }

        return result;
    }

    protected T updateDataItem(T dataItem) {
        return saveOrUpdate(dataItem);
    }

    protected boolean deleteDataItem(int dataItemId) {
        boolean result = false;

        SortedSet<T> dataItemSet = readDataItemSet();
        if (dataItemSet != null) {
            T dataItem = findDataItem(dataItemId);
            if (dataItem != null) {
                result = dataItemSet.remove(dataItem);
                saveDataItemSet(dataItemSet);
            }
        }

        return result;
    }

    protected boolean deleteDataItem(T dataItem) {
        return (dataItem != null) && deleteDataItem(dataItem.getId());
    }

}
