package com.company.todo.servlets;

import com.company.todo.controller.TaskItemController;
import com.company.todo.model.TaskItem;
import com.company.todo.servlets.proto.TaskItemServlet;
import com.company.util.DataIntegrityException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by Yevhen on 18.07.2016.
 */
public class ActionServlet extends TaskItemServlet {
    private static final String TASK_ITEM_NAME_PARAMETER_NAME = "task_item_name";
    private static final String TASK_ITEM_CATEGORY_NAME_PARAMETER_NAME = "task_item_category_name";
    private static final String COMPLETE_TASK_PARAMETER_NAME = "completeTask";
    private static final String DELETE_TASK_PARAMETER_NAME = "deleteTask";
    private static final String ACTION_PARAMETER_NAME = "action";
    private static final String ACTION_PARAMETER_ADD_TASK_VALUE = "addTask";
    private static final String ACTION_PARAMETER_UPDATE_TASKS_VALUE = "updateTasks";
    private static final String ERROR_MESSAGE_ATTRIBUTE_NAME = "errorMessage";

    private void updateTaskCompleteness(HttpServletRequest request) {
        TaskItemController taskItemController = getTaskItemController();

        // All the list
        List<TaskItem> taskItemList = taskItemController.findAllTaskItems();

        String[] completeStates = request.getParameterValues(COMPLETE_TASK_PARAMETER_NAME);
        if (completeStates != null) {
            for (String completeState : completeStates) {
                // Save completed task
                int completedTaskId = Integer.parseInt(completeState);
                getTaskItemController().updTaskItem(completedTaskId, true);
                // Find this one in common list
                Optional<TaskItem> optionalTaskItem = taskItemList.stream().filter(ti -> ti.getTaskItemId() == completedTaskId).findFirst();
                if (optionalTaskItem.isPresent()) {
                    // Delete from common list
                    taskItemList.remove(optionalTaskItem.get());
                }
            }
        }

        // Save all uncompleted tasks
        taskItemList.forEach(ti -> taskItemController.updTaskItem(ti.getTaskItemId(), false));
    }

    private void deleteTasks(HttpServletRequest request) {
        TaskItemController taskItemController = getTaskItemController();

        String[] deleteStates = request.getParameterValues(DELETE_TASK_PARAMETER_NAME);
        if (deleteStates != null) {
            for (String deleteState : deleteStates) {
                taskItemController.delTaskItem(Integer.parseInt(deleteState));
            }
        }

    }

    private void updateTasks(HttpServletRequest request) {
        // It is important to firstly update the tasks because only completed task could be deleted
        updateTaskCompleteness(request);

        deleteTasks(request);
    }

    private void addTask(HttpServletRequest request) {
        String taskItemName = request.getParameter(TASK_ITEM_NAME_PARAMETER_NAME);
        String taskItemCategoryName = request.getParameter(TASK_ITEM_CATEGORY_NAME_PARAMETER_NAME);

        getTaskItemController().addTaskItem(taskItemName, taskItemCategoryName);
    }

    private void updateTaskCompletenessAndAddTask(HttpServletRequest request) {
        updateTaskCompleteness(request);

        addTask(request);
    }

    @Override
    protected void responseAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String errorMessage = null;

        try {
            switch (request.getParameter(ACTION_PARAMETER_NAME)) {
                case ACTION_PARAMETER_UPDATE_TASKS_VALUE:
                    updateTasks(request);
                    break;

                case ACTION_PARAMETER_ADD_TASK_VALUE:
                    updateTaskCompletenessAndAddTask(request);
                    break;
            }

        } catch (DataIntegrityException e) {
            // Save error message
            errorMessage = e.getMessage();
        }

        // Store error message; next should be displayed in the target JSP
        request.getSession().setAttribute(ERROR_MESSAGE_ATTRIBUTE_NAME, errorMessage);
    }
}
