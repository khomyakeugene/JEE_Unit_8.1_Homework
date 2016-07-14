package com.company.todo.controller.impl;

import com.company.todo.Util;
import com.company.todo.controller.TaskItemController;
import com.company.todo.model.TaskItem;
import com.company.util.DataIntegrityException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.mock.web.MockServletContext;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Yevhen on 14.07.2016.
 */
public class TaskItemControllerImplTest {
    private static MockServletContext mockServletContext = new MockServletContext();
    private static TaskItemController taskItemController;

    private static void initDataSource() throws Exception {
        taskItemController = TaskItemControllerImpl.getInstance();
        taskItemController.setServletContext(mockServletContext);
    }

    @BeforeClass
    public static void  setUpClass() throws Exception {
        initDataSource();
    }

    @Test(timeout = 1000)
    public void findAddUpdDelUpdateTaskItem() throws Exception {
        List<TaskItem> taskItemList = taskItemController.findAllTaskItems();
        int listSize = (taskItemList == null) ? 0 : taskItemList.size();

        TaskItem firstTaskItem = new TaskItem();
        firstTaskItem.setName(Util.getRandomString());
        firstTaskItem.getTaskItemCategory().setName(Util.getRandomString());
        firstTaskItem = taskItemController.addTaskItem(firstTaskItem);

        TaskItem secondTaskItem = new TaskItem();
        secondTaskItem.setName(Util.getRandomString());
        secondTaskItem.getTaskItemCategory().setName(Util.getRandomString());
        secondTaskItem = taskItemController.addTaskItem(secondTaskItem);

        TaskItem thirdTaskItem = taskItemController.addTaskItem(Util.getRandomString(), Util.getRandomString());

        int firstTaskItemId = firstTaskItem.getTaskItemId();
        int secondTaskItemId = secondTaskItem.getTaskItemId();
        int thirdTaskItemId = thirdTaskItem.getTaskItemId();
        assertTrue(firstTaskItemId != secondTaskItemId && firstTaskItemId != thirdTaskItemId &&
                secondTaskItemId != thirdTaskItemId);

        assertTrue(taskItemController.findTaskItemById(firstTaskItemId).equals(firstTaskItem));
        assertTrue(taskItemController.findTaskItemById(secondTaskItemId).equals(secondTaskItem));
        assertTrue(taskItemController.findTaskItemById(thirdTaskItemId).equals(thirdTaskItem));
        assertTrue(taskItemController.findAllTaskItems().size() == (listSize + 3));

        taskItemController.updTaskItem(firstTaskItemId, true);
        assertTrue(taskItemController.findTaskItemById(firstTaskItemId).isComplete());
        taskItemController.updTaskItem(firstTaskItemId, false);
        assertTrue(!taskItemController.findTaskItemById(firstTaskItemId).isComplete());
        taskItemController.updTaskItem(thirdTaskItemId, true);
        assertTrue(taskItemController.findTaskItemById(thirdTaskItemId).isComplete());

        taskItemController.updTaskItem(firstTaskItemId, true);
        taskItemController.delTaskItem(firstTaskItemId);
        assertTrue(taskItemController.findTaskItemById(firstTaskItemId) == null);
        assertTrue(taskItemController.findAllTaskItems().size() == (listSize + 2));

        taskItemController.updTaskItem(thirdTaskItemId, true);
        taskItemController.delTaskItem(thirdTaskItem);
        assertTrue(taskItemController.findTaskItemById(thirdTaskItemId) == null);
        assertTrue(taskItemController.findAllTaskItems().size() == (listSize + 1));
    }

    @Test(timeout = 1000, expected = DataIntegrityException.class)
    public void delTaskItem() throws Exception {
        TaskItem taskItem = new TaskItem();
        taskItem.setName(Util.getRandomString());
        taskItem.getTaskItemCategory().setName(Util.getRandomString());
        taskItem = taskItemController.addTaskItem(taskItem);

        taskItemController.delTaskItem(taskItem);
    }

    @Test(timeout = 1000, expected = DataIntegrityException.class)
    public void delTaskItem1() throws Exception {
        TaskItem taskItem = new TaskItem();
        taskItem.setName(Util.getRandomString());
        taskItem.getTaskItemCategory().setName(Util.getRandomString());
        taskItem = taskItemController.addTaskItem(taskItem);

        taskItemController.delTaskItem(taskItem.getTaskItemId());
    }
}