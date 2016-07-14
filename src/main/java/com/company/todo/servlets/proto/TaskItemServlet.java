package com.company.todo.servlets.proto;

import com.company.todo.controller.TaskItemController;

import javax.servlet.ServletException;

/**
 * Created by Yevhen on 14.07.2016.
 */
public abstract class TaskItemServlet  extends CallerPageReturnServlet  {
    private TaskItemController taskItemController;

    public TaskItemController getTaskItemController() {
        return taskItemController;
    }

    @Override
    public void init() throws ServletException {
        super.init();

        // Get Controller
        //taskItemController = TaskItemControllerImpl.getInstance();
    }
}
