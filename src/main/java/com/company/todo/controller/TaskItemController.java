package com.company.todo.controller;

import com.company.todo.model.TaskItem;

import java.util.List;

/**
 * Created by Yevhen on 13.07.2016.
 */
public interface TaskItemController {
    TaskItem createTaskItem(TaskItem taskItem);

    TaskItem readTaskItem(int taskItemId);

    TaskItem updateTaskItem(int taskItemId, boolean isComplete);

    boolean deleteTaskItem(int taskItemId);

    List<TaskItem> readTaskItemList();
}
