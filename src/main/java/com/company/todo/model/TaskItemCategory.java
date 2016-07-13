package com.company.todo.model;

import com.company.todo.model.proto.DataItem;

/**
 * Created by Yevhen on 10.07.2016.
 */
public class TaskItemCategory  extends DataItem {
    public int getTaskItemCategoryId() {
        return getId();
    }

    public void setTaskItemCategoryId(int taskItemCategoryId) {
        setId(taskItemCategoryId);
    }
}
