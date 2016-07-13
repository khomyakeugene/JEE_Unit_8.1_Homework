package com.company.todo.controller.impl;

import com.company.todo.controller.TaskItemController;
import com.company.todo.dao.TaskItemDao;
import com.company.todo.model.TaskItem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * Created by Yevhen on 13.07.2016.
 */
public class TaskItemControllerImpl implements TaskItemController {
    private final static String TODO_CONTROLLER_CONTEXT_NAME = "todo-controller-context.xml";

    private static TaskItemController taskItemController;

    private TaskItemDao taskItemDao;

    public static TaskItemController getInstance() {
        if (taskItemController == null) {
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext(TODO_CONTROLLER_CONTEXT_NAME);
            taskItemController = applicationContext.getBean(TaskItemControllerImpl.class);
        }

        return taskItemController;
    }


    public void setTaskItemDao(TaskItemDao taskItemDao) {
        this.taskItemDao = taskItemDao;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        taskItemDao.setServletContext(servletContext);
    }

    @Override
    public TaskItem createTaskItem(TaskItem taskItem) {
        return taskItemDao.createTaskItem(taskItem);
    }

    @Override
    public TaskItem readTaskItem(int taskItemId) {
        return taskItemDao.readTaskItem(taskItemId);
    }

    @Override
    public TaskItem updateTaskItem(int taskItemId, boolean isComplete) {
        return taskItemDao.updateTaskItem(taskItemId, isComplete);
    }

    @Override
    public boolean deleteTaskItem(int taskItemId) {
        return taskItemDao.deleteTaskItem(taskItemId);
    }

    @Override
    public List<TaskItem> readTaskItemList() {
        return taskItemDao.readTaskItemList();
    }
}
