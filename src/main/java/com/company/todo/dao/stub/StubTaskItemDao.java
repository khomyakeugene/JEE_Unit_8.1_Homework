package com.company.todo.dao.stub;

import com.company.todo.dao.TaskItemDao;
import com.company.todo.dao.stub.proto.StubDao;
import com.company.todo.model.TaskItem;

import java.util.List;

/**
 * Created by Yevhen on 13.07.2016.
 */
public class StubTaskItemDao extends StubDao<TaskItem> implements TaskItemDao {
    private StubTaskItemCategoryDao taskItemCategoryDao;

    public void setTaskItemCategoryDao(StubTaskItemCategoryDao taskItemCategoryDao) {
        this.taskItemCategoryDao = taskItemCategoryDao;
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
        /*
        TaskItem firstTaskItem = new TaskItem();
        firstTaskItem.setName("Aaaaa");
        firstTaskItem.getTaskItemCategory().setName("bbbbb");
        addTaskItem(firstTaskItem);
*/
        return findAllDataItems();
    }
}
