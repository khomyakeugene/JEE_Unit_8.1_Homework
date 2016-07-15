package com.company.todo.dao.stub.http;

import org.junit.BeforeClass;


/**
 * Created by Yevhen on 13.07.2016.
 */
public class StubTaskItemDaoTest extends com.company.todo.dao.TaskItemDaoTest {
    private final static String APPLICATION_CONTEXT_NAME = "todo-controller-context.xml";

    @BeforeClass
    public static void setUpClass() throws Exception {
        initDataSource(APPLICATION_CONTEXT_NAME);
    }
}