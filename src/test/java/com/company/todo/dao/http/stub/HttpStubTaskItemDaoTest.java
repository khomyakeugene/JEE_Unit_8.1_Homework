package com.company.todo.dao.http.stub;

import com.company.todo.Util;
import com.company.todo.dao.TaskItemCategoryDao;
import com.company.todo.dao.TaskItemDao;
import com.company.todo.model.TaskItem;
import com.company.todo.model.TaskItemCategory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockServletContext;

import java.util.List;

import static org.junit.Assert.assertTrue;


/**
 * Created by Yevhen on 13.07.2016.
 */
public class HttpStubTaskItemDaoTest {
    private final static String APPLICATION_CONTEXT_NAME = "todo-controller-context.xml";

    private static MockServletContext mockServletContext = new MockServletContext();

    private static TaskItemCategoryDao taskItemCategoryDao;
    private static TaskItemDao taskItemDao;

    @BeforeClass
    public static void setUpClass() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_NAME);

        taskItemCategoryDao = applicationContext.getBean(TaskItemCategoryDao.class);
        taskItemCategoryDao.setServletContext(mockServletContext);

        taskItemDao = applicationContext.getBean(TaskItemDao.class);
        taskItemDao.setServletContext(mockServletContext);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {

    }

    @Test(timeout = 1000)
    public void saveTaskItemCategory() throws Exception {
        List<TaskItemCategory> taskItemCategoryList = taskItemCategoryDao.findAllTaskItemCategories();
        int listSize = (taskItemCategoryList == null) ? 0 : taskItemCategoryList.size();

        TaskItemCategory taskItemCategory =
                taskItemCategoryDao.addTaskItemCategory(Util.getRandomString());
        assertTrue(taskItemCategoryDao.findAllTaskItemCategories().size() == (listSize + 1));
        assertTrue(taskItemCategoryDao.findAllTaskItemCategories().get(listSize).equals(taskItemCategory));
    }

    @Test(timeout = 1000)
    public void findAddUpdDelUpdateTaskItem() throws Exception {
        List<TaskItem> taskItemList = taskItemDao.findAllTaskItems();
        int listSize = (taskItemList == null) ? 0 : taskItemList.size();

        TaskItem firstTaskItem = new TaskItem();
        firstTaskItem.setName(Util.getRandomString());
        firstTaskItem.getTaskItemCategory().setName(Util.getRandomString());
        firstTaskItem = taskItemDao.addTaskItem(firstTaskItem);

        TaskItem secondTaskItem = new TaskItem();
        secondTaskItem.setName(Util.getRandomString());
        secondTaskItem.getTaskItemCategory().setName(Util.getRandomString());
        secondTaskItem = taskItemDao.addTaskItem(secondTaskItem);

        TaskItem thirdTaskItem = new TaskItem();
        thirdTaskItem.setName(Util.getRandomString());
        thirdTaskItem.getTaskItemCategory().setName(Util.getRandomString());
        thirdTaskItem = taskItemDao.addTaskItem(thirdTaskItem);

        int firstTaskItemId = firstTaskItem.getTaskItemId();
        int secondTaskItemId = secondTaskItem.getTaskItemId();
        int thirdTaskItemId = thirdTaskItem.getTaskItemId();
        assertTrue(firstTaskItemId != secondTaskItemId && firstTaskItemId != thirdTaskItemId &&
                secondTaskItemId != thirdTaskItemId);

        assertTrue(taskItemDao.findTaskItemById(firstTaskItemId).equals(firstTaskItem));
        assertTrue(taskItemDao.findTaskItemById(secondTaskItemId).equals(secondTaskItem));
        assertTrue(taskItemDao.findTaskItemById(thirdTaskItemId).equals(thirdTaskItem));
        assertTrue(taskItemDao.findAllTaskItems().size() == (listSize + 3));

        taskItemDao.updTaskItem(firstTaskItemId, true);
        assertTrue(taskItemDao.findTaskItemById(firstTaskItemId).isComplete());
        taskItemDao.updTaskItem(firstTaskItemId, false);
        assertTrue(!taskItemDao.findTaskItemById(firstTaskItemId).isComplete());
        taskItemDao.updTaskItem(thirdTaskItemId, true);
        assertTrue(taskItemDao.findTaskItemById(thirdTaskItemId).isComplete());

        taskItemDao.delTaskItem(firstTaskItem);
        assertTrue(taskItemDao.findTaskItemById(firstTaskItemId) == null);
        assertTrue(taskItemDao.findAllTaskItems().size() == (listSize + 2));

        taskItemDao.delTaskItem(secondTaskItemId);
        assertTrue(taskItemDao.findTaskItemById(secondTaskItemId) == null);
        assertTrue(taskItemDao.findAllTaskItems().size() == (listSize + 1));

        taskItemDao.delTaskItem(thirdTaskItem);
        assertTrue(taskItemDao.findTaskItemById(thirdTaskItemId) == null);
        assertTrue(taskItemDao.findAllTaskItems().size() == listSize);
    }
}