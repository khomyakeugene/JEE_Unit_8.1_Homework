package com.company.todo.dao;

import com.company.todo.model.TaskItem;

import java.util.List;

/**
 * Created by Yevhen on 12.07.2016.
 */
public interface TaskItemDao {
    /**
     * @return A list of TaskItems
     */
    public List<TaskItem> readTaskItemList();

    /**
     * @param taskItem
     * @return whether the taskItem was persisted.
     */
    public TaskItem createTaskItem(TaskItem taskItem);

    /**
     * @param taskItemId
     * @return the TaskItem
     */
    public TaskItem readTaskItem(int taskItemId);

    /**
     * @param taskItemId
     * @return the TaskItem
     */
    public TaskItem updateTaskItem(int taskItemId, boolean isComplete);

    /**
     *
     * @param taskItemId
     * @return whether the delete was successful.
     */
    public boolean deleteTaskItem(int taskItemId);

}
