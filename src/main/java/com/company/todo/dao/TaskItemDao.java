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
    public List<TaskItem> readTaskItems();

    /**
     * @param taskItem
     * @return whether the taskItem was persisted.
     */
    public TaskItem createTaskItem(TaskItem taskItem);

    /**
     * @param id
     * @return the TaskItem
     */
    public TaskItem readTaskItem(int taskItemId);

    /**
     * @param id
     * @return the TaskItem
     */
    public TaskItem updateTodoItem(String id, boolean isComplete);

    /**
     *
     * @param id
     * @return whether the delete was successful.
     */
    public boolean deleteTodoItem(String id);

}
