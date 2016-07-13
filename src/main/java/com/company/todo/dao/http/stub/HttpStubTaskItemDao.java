package com.company.todo.dao.http.stub;

import com.company.todo.dao.TaskItemDao;
import com.company.todo.model.TaskItem;

import javax.servlet.ServletContext;
import java.util.*;

/**
 * Created by Yevhen on 13.07.2016.
 */
public class HttpStubTaskItemDao implements TaskItemDao {
    private static final String TASK_ITEM_LIST_VAR_NAME = "taskItemList";

    private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    private Object getAttribute(String attributeName) {
        return servletContext.getAttribute(attributeName);
    }

    private void setAttribute(String attributeName, Object attributeValue) {
        servletContext.setAttribute(attributeName, attributeValue);
    }

    private int generateTaskId() {
        int result = 1;

        // Get max present id
        Optional<TaskItem> taskItemOptional =
                readTaskItemList().stream().max(Comparator.comparing(TaskItem::getTaskItemId));
        if (taskItemOptional.isPresent()) {
            result = taskItemOptional.get().getTaskItemId() + 1;
        }

        return result;
    }

    private void saveOrUpdate(TaskItem taskItem) {
        servletContext.
                getAttribute(TASK_ITEM_LIST_VAR_NAME);

        readTaskItemList().add(taskItem);
    }

    @Override
    public TaskItem createTaskItem(TaskItem taskItem) {
        taskItem.setTaskItemId(generateTaskId());
        saveOrUpdate(taskItem);

        return taskItem;
    }

    @Override
    public TaskItem readTaskItem(int taskItemId) {
        return null;
    }

    @Override
    public TaskItem updateTaskItem(int taskItemId, boolean isComplete) {
        return null;
    }

    @Override
    public boolean deleteTaskItem(int taskItemId) {
        return false;
    }

    @Override
    public List<TaskItem> readTaskItemList() {
        return (List<TaskItem>)getAttribute(TASK_ITEM_LIST_VAR_NAME);
    }
}
