package com.company.todo.dao.http.stub;

import com.company.todo.dao.TaskItemDao;
import com.company.todo.model.TaskItem;

import java.util.List;

/**
 * Created by Yevhen on 13.07.2016.
 */
public class HttpStubTaskItemDao implements TaskItemDao {
    @Override
    public TaskItem createTaskItem(TaskItem taskItem) {
        return null;
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
        return null;
    }
}
