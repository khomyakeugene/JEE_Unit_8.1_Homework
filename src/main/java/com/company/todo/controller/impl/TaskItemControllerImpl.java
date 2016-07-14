package com.company.todo.controller.impl;

import com.company.todo.controller.TaskItemController;
import com.company.todo.controller.impl.proto.Controller;
import com.company.todo.dao.TaskItemDao;
import com.company.todo.model.TaskItem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * Created by Yevhen on 13.07.2016.
 */
public class TaskItemControllerImpl extends Controller implements TaskItemController {
    private final static String IT_IS_PROHIBITED_TO_DELETE_UNCOMPLETED_TASK_PATTERN =
            "It is prohibited to delete uncompleted task with id <%d>";
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
    public TaskItem addTaskItem(TaskItem taskItem) {
        return taskItemDao.addTaskItem(taskItem);
    }

    @Override
    public TaskItem addTaskItem(String taskName, String taskCategoryName) {
        return taskItemDao.addTaskItem(taskName, taskCategoryName);
    }

    @Override
    public TaskItem findTaskItemById(int taskItemId) {
        return taskItemDao.findTaskItemById(taskItemId);
    }

    @Override
    public TaskItem updTaskItem(int taskItemId, boolean isComplete) {
        return taskItemDao.updTaskItem(taskItemId, isComplete);
    }

    @Override
    public boolean delTaskItem(int taskItemId) {
        boolean result = false;

        TaskItem taskItem = taskItemDao.findTaskItemById(taskItemId);
        if (taskItem != null) {
            if (taskItem.isComplete()) {
                result = taskItemDao.delTaskItem(taskItemId);
            } else {
                throwDataIntegrityException(String.format(
                        IT_IS_PROHIBITED_TO_DELETE_UNCOMPLETED_TASK_PATTERN, taskItemId));
            }
        }

        return result;
    }

    @Override
    public boolean delTaskItem(TaskItem taskItem) {
        return (taskItem != null) && delTaskItem(taskItem.getTaskItemId());
    }

    @Override
    public List<TaskItem> findAllTaskItems() {
        return taskItemDao.findAllTaskItems();
    }
}
