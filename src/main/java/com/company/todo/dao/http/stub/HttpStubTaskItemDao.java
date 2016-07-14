package com.company.todo.dao.http.stub;

import com.company.todo.dao.TaskItemDao;
import com.company.todo.dao.http.stub.proto.HttpStubDao;
import com.company.todo.model.TaskItem;

import javax.servlet.ServletContext;
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
    public TaskItem addTaskItem(TaskItem taskItem) {
        taskItem.setTaskItemCategory(taskItemCategoryDao.addTaskItemCategory(
                taskItem.getTaskItemCategory().getName()));

        return addDataItem(taskItem);
    }

    @Override
    public TaskItem addTaskItem(String taskName, String taskCategoryName) {
        TaskItem taskItem = new TaskItem();
        taskItem.setName(taskName);
        taskItem.getTaskItemCategory().setName(taskCategoryName);

        return addTaskItem(taskItem);
    }

    @Override
    public TaskItem findTaskItemById(int taskItemId) {
        return findDataItem(taskItemId);
    }

    @Override
    public TaskItem updTaskItem(int taskItemId, boolean isComplete) {
        TaskItem taskItem = findTaskItemById(taskItemId);
        if (taskItem != null) {
            taskItem.setComplete(isComplete);
            taskItem = updateDataItem(taskItem);
        }

        return taskItem;
    }

    @Override
    public boolean delTaskItem(int taskItemId) {
        return deleteDataItem(taskItemId);
    }

    @Override
    public boolean delTaskItem(TaskItem taskItem) {
        return deleteDataItem(taskItem);
    }

    @Override
    public List<TaskItem> findAllTaskItems() {
        return findAllDataItems();
    }
}
