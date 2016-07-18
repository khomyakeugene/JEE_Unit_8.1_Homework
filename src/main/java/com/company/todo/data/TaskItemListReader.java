package com.company.todo.data;

import com.company.todo.controller.impl.TaskItemControllerImpl;
import com.company.todo.model.TaskItem;

import java.util.List;

/**
 * Created by Yevhen on 18.07.2016.
 */
public class TaskItemListReader {
    public List<TaskItem> getTaskItemList() {
        return TaskItemControllerImpl.getInstance().findAllTaskItems();
    }
}
