package com.company.todo.dao;

import com.company.todo.model.TaskItem;

import java.util.List;

/**
 * Created by Yevhen on 12.07.2016.
 */
public interface TaskItemDao {
    TaskItem addTaskItem(TaskItem taskItem);

    TaskItem addTaskItem(String taskName, String taskCategoryName);

    TaskItem findTaskItemById(int taskItemId);

    TaskItem updTaskItem(int taskItemId, boolean isComplete);

    boolean delTaskItem(int taskItemId);

    boolean delTaskItem(TaskItem taskItem);

    List<TaskItem> findAllTaskItems();
}
