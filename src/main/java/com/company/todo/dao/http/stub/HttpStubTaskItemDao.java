package com.company.todo.dao.http.stub;

import com.company.todo.dao.TaskItemDao;
import com.company.todo.dao.http.stub.proto.HttpStubDao;
import com.company.todo.model.TaskItem;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yevhen on 13.07.2016.
 */
public class HttpStubTaskItemDao extends HttpStubDao<TaskItem> implements TaskItemDao {
    private HttpStubTaskItemCategoryDao taskItemCategoryDao;

    public void setTaskItemCategoryDao(HttpStubTaskItemCategoryDao taskItemCategoryDao) {
        this.taskItemCategoryDao = taskItemCategoryDao;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        super.setServletContext(servletContext);
        taskItemCategoryDao.setServletContext(servletContext);
    }

    @Override
    public TaskItem createTaskItem(TaskItem taskItem) {
        taskItem.setTaskItemCategory(taskItemCategoryDao.saveTaskItemCategoty(
                taskItem.getTaskItemCategory().getName()));

        return createDataItem(taskItem);
    }

    @Override
    public TaskItem readTaskItem(int taskItemId) {
        return readDataItem(taskItemId);
    }

    @Override
    public TaskItem updateTaskItem(int taskItemId, boolean isComplete) {
        TaskItem taskItem = readTaskItem(taskItemId);
        if (taskItem != null) {
            taskItem.setComplete(isComplete);
            taskItem = updateDataItem(taskItem);
        }

        return taskItem;
    }

    @Override
    public boolean deleteTaskItem(int taskItemId) {
        return deleteDataItem(taskItemId);
    }

    @Override
    public List<TaskItem> readTaskItemList() {
        return new ArrayList<>(readDataItemSet());
    }
}
