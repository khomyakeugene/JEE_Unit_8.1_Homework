package com.company.todo.dao.http.stub;

import com.company.todo.dao.TaskItemCategoryDao;
import com.company.todo.dao.http.stub.proto.HttpStubUniqueNameDao;
import com.company.todo.model.TaskItemCategory;

import java.util.List;

/**
 * Created by Yevhen on 13.07.2016.
 */
public class HttpStubTaskItemCategoryDao extends HttpStubUniqueNameDao<TaskItemCategory>
        implements TaskItemCategoryDao {
    @Override
    public TaskItemCategory addTaskItemCategory(String name) {
        return saveOrUpdate(name);
    }

    @Override
    public List<TaskItemCategory> findAllTaskItemCategories() {
        return findAllDataItems();
    }
}
