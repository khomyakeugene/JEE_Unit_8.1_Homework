package com.company.todo.controller;

import com.company.todo.model.TaskItem;

import java.util.List;

/**
 * Created by Yevhen on 13.07.2016.
 */
public interface TaskItemController {
    TaskItem addTaskItem(TaskItem taskItem);

    TaskItem addTaskItem(String taskName, String taskCategoryName);

    TaskItem findTaskItemById(int taskItemId);

    TaskItem updTaskItem(int taskItemId, boolean isComplete);

    boolean delTaskItem(int taskItemId);

    boolean delTaskItem(TaskItem taskItem);

    List<TaskItem> findAllTaskItems();
}
