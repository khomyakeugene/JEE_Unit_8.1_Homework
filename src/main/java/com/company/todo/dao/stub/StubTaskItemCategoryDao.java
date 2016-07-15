package com.company.todo.dao.stub;

import com.company.todo.dao.TaskItemCategoryDao;
import com.company.todo.dao.stub.proto.StubUniqueNameDao;
import com.company.todo.model.TaskItemCategory;

import java.util.List;

/**
 * Created by Yevhen on 13.07.2016.
 */
public class StubTaskItemCategoryDao extends StubUniqueNameDao<TaskItemCategory>
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
