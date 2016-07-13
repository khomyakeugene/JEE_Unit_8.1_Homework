package com.company.todo.dao.http.stub;

import com.company.todo.dao.TaskItemDao;
import com.company.todo.model.TaskItem;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Created by Yevhen on 13.07.2016.
 */
public class HttpStubTaskItemDao implements TaskItemDao {
    private ServletContext servletContext;
    private ArrayList<TaskItem> taskItemList;

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    private int generateTaskId() {
        int result = 1;

        List<TaskItem> taskItemList = readTaskItemList();
        // Get max present id
        Optional<TaskItem> taskItemOptional = taskItemList.stream().max(Comparator.comparing(TaskItem::getTaskItemId));
        if (taskItemOptional.isPresent()) {
            result = taskItemOptional.get().getTaskItemId() + 1;
        }

        return result;
    }

    @Override
    public TaskItem createTaskItem(TaskItem taskItem) {
        taskItem.setTaskItemId(generateTaskId());
        readTaskItemList().add(taskItem);

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
        // Todo read from ServletContext

        if (taskItemList == null) {
            taskItemList = new ArrayList<>();
        }

        return taskItemList;
    }
}
