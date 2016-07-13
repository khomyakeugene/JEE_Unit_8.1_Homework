package com.company.todo.dao.http.stub;

import com.company.todo.dao.TaskItemCategoryDao;
import com.company.todo.dao.http.stub.proto.HttpStubUniqueNameDao;
import com.company.todo.model.TaskItemCategory;

/**
 * Created by Yevhen on 13.07.2016.
 */
public class HttpStubTaskItemCategoryDao extends HttpStubUniqueNameDao<TaskItemCategory>
        implements TaskItemCategoryDao {
    @Override
    public TaskItemCategory saveTaskItemCategory(String name) {
        return saveOrUpdate(name);
    }
}
