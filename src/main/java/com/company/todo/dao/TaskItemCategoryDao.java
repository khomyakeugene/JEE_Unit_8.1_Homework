package com.company.todo.dao;

import com.company.todo.model.TaskItemCategory;

import java.util.List;

/**
 * Created by Yevhen on 13.07.2016.
 */
public interface TaskItemCategoryDao {
    TaskItemCategory addTaskItemCategory(String name);

    List<TaskItemCategory> findAllTaskItemCategories();
}
