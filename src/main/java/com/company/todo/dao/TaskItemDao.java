package com.company.todo.dao;

import com.company.todo.model.TaskItem;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * Created by Yevhen on 12.07.2016.
 */
public interface TaskItemDao {
    void setServletContext(ServletContext servletContext);

    TaskItem addTaskItem(TaskItem taskItem);

    TaskItem addTaskItem(String taskName, String taskCategoryName);

    TaskItem findTaskItemById(int taskItemId);

    TaskItem updTaskItem(int taskItemId, boolean isComplete);

    boolean delTaskItem(int taskItemId);

    boolean delTaskItem(TaskItem taskItem);

    List<TaskItem> findAllTaskItems();
}
