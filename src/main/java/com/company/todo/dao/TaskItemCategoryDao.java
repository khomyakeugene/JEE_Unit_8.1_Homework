package com.company.todo.dao;

import com.company.todo.model.TaskItemCategory;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * Created by Yevhen on 13.07.2016.
 */
public interface TaskItemCategoryDao {
    void setServletContext(ServletContext servletContext);

    TaskItemCategory saveTaskItemCategory(String name);

    List<TaskItemCategory> readTaskItemCategoryList();
}
