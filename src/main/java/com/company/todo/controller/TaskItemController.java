package com.company.todo.controller;

import com.company.todo.model.TaskItem;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * Created by Yevhen on 13.07.2016.
 */
public interface TaskItemController {
    void setServletContext(ServletContext servletContext);

    TaskItem addTaskItem(TaskItem taskItem);

    TaskItem findTaskItemById(int taskItemId);

    TaskItem updTaskItem(int taskItemId, boolean isComplete);

    boolean delTaskItem(int taskItemId);

    List<TaskItem> findAllTaskItems();
}
