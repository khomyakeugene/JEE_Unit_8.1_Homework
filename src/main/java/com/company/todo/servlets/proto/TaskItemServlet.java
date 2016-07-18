package com.company.todo.servlets.proto;

import com.company.todo.controller.TaskItemController;
import com.company.todo.controller.impl.TaskItemControllerImpl;

/**
 * Created by Yevhen on 14.07.2016.
 */
public abstract class TaskItemServlet  extends CallerPageReturnServlet  {
    private TaskItemController taskItemController;

    protected TaskItemController getTaskItemController() {
        if (taskItemController == null) {
            taskItemController = TaskItemControllerImpl.getInstance();
        }

        return taskItemController;
    }
}
