package com.company.todo.dao.http.stub;

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

    @Test//(timeout = 1000)
    public void saveTaskItemCategory() throws Exception {
        String taskItemCategoryName = Util.getRandomString();

        List<TaskItemCategory> taskItemCategoryList = taskItemCategoryDao.readTaskItemCategoryList();
        int listSize = (taskItemCategoryList == null) ? 0 : taskItemCategoryList.size();

        TaskItemCategory taskItemCategory =
                taskItemCategoryDao.saveTaskItemCategory(taskItemCategoryName);
        assertTrue(taskItemCategoryDao.readTaskItemCategoryList().size() == (listSize + 1));
        assertTrue(taskItemCategoryDao.readTaskItemCategoryList().get(listSize).equals(taskItemCategory));
    }

    @Test(timeout = 1000)
    public void createReadUpdateTaskItem() throws Exception {
        TaskItem taskItem1 = new TaskItem();
        taskItem1.setName(Util.getRandomString());
        taskItem1.getTaskItemCategory().setName(Util.getRandomString());
        taskItem1 = taskItemDao.createTaskItem(taskItem1);

        TaskItem taskItem2 = new TaskItem();
        taskItem2.setName(Util.getRandomString());
        taskItem2.getTaskItemCategory().setName(Util.getRandomString());
        taskItem2 = taskItemDao.createTaskItem(taskItem2);

        int firstTaskItemId = taskItem1.getTaskItemId();
        int secondTaskItemId = taskItem2.getTaskItemId();
        assertTrue(firstTaskItemId != secondTaskItemId);

        assertTrue(taskItemDao.readTaskItem(firstTaskItemId).equals(taskItem1));
        assertTrue(taskItemDao.readTaskItem(secondTaskItemId).equals(taskItem2));
        assertTrue(taskItemDao.readTaskItemList().size() == 2);

        taskItemDao.updateTaskItem(firstTaskItemId, true);
        assertTrue(taskItemDao.readTaskItem(firstTaskItemId).isComplete());
        taskItemDao.updateTaskItem(firstTaskItemId, false);
        assertTrue(!taskItemDao.readTaskItem(firstTaskItemId).isComplete());
    }

    @Test
    public void deleteTaskItem() throws Exception {

    }

    @Test
    public void readTaskItemList() throws Exception {

    }

}