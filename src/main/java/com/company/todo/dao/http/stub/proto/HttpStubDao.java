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

    protected SortedSet<T> readDataItemSet() {
        return (SortedSet<T>)getAttribute(getEntitySetVariableName());
    }

    protected List<T> readDataItemList() {
        return new ArrayList<>(readDataItemSet());
    }

    private void saveDataItemSet(SortedSet<T> sortedSet) {
        setAttribute(getEntitySetVariableName(), sortedSet);
    }

    private int generateId() {
        SortedSet<T> dataItemSet = readDataItemSet();

        return (dataItemSet != null && !dataItemSet.isEmpty()) ? dataItemSet.last().getId() + 1 : 1;
    }

    private T saveOrUpdate(T dataItem) {
        if (dataItem != null) {
            SortedSet<T> dataItemSet = readDataItemSet();

            if (dataItemSet == null) {
                dataItemSet = new TreeSet<>();
            }
            if (!dataItemSet.add(dataItem)) {
                int dataItemId = dataItem.getId();
                T oldDataItem = readDataItem(dataItemId);
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

    protected T createDataItem(T dataItem) {
        dataItem.setId(generateId());

        return saveOrUpdate(dataItem);
    }

    protected T readDataItem(int dataItemId) {
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
            T dataItem = readDataItem(dataItemId);
            if (dataItem != null) {
                result = dataItemSet.remove(dataItem);
                saveDataItemSet(dataItemSet);
            }
        }

        return result;
    }
}
