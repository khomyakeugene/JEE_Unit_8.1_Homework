package com.company.todo.dao;

import com.company.todo.model.TaskItem;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * Created by Yevhen on 12.07.2016.
 */
public interface TaskItemDao {
    void setServletContext(ServletContext servletContext);

    TaskItem createTaskItem(TaskItem taskItem);

    TaskItem readTaskItem(int taskItemId);

    TaskItem updateTaskItem(int taskItemId, boolean isComplete);

    boolean deleteTaskItem(int taskItemId);

    List<TaskItem> readTaskItemList();
}
