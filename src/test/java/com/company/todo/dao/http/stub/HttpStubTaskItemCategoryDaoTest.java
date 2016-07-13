package com.company.todo.dao.http.stub;

import com.company.todo.dao.TaskItemCategoryDao;
import com.company.todo.model.TaskItemCategory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.web.MockServletContext;

import static org.junit.Assert.assertTrue;


/**
 * Created by Yevhen on 13.07.2016.
 */
public class HttpStubTaskItemCategoryDaoTest {
    private final static String APPLICATION_CONTEXT_NAME = "todo-controller-context.xml";

    private static TaskItemCategoryDao taskItemCategoryDao;
    private static MockServletContext mockServletContext = new MockServletContext();

    @BeforeClass
    public static void setUpClass() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_NAME);

        taskItemCategoryDao = applicationContext.getBean(TaskItemCategoryDao.class);
        taskItemCategoryDao.setServletContext(mockServletContext);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {

    }

    @Test
    public void saveTaskItemCategory() throws Exception {
        String taskItemCategoryName = Util.getRandomString();

        TaskItemCategory taskItemCategory =
                taskItemCategoryDao.saveTaskItemCategory(taskItemCategoryName);
        assertTrue(taskItemCategoryDao.readTaskItemCategoryList().size() == 1);
        assertTrue(taskItemCategoryDao.readTaskItemCategoryList().get(0).equals(taskItemCategory));
    }
}