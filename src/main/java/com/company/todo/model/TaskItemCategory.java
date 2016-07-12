package com.company.todo.model;

/**
 * Created by Yevhen on 10.07.2016.
 */
public class TaskItemCategory {
    private int taskItemCategoryId;
    private String name;

    public int getTaskItemCategoryId() {
        return taskItemCategoryId;
    }

    public void setTaskItemCategoryId(int taskItemCategoryId) {
        this.taskItemCategoryId = taskItemCategoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskItemCategory)) return false;

        TaskItemCategory that = (TaskItemCategory) o;

        return taskItemCategoryId == that.taskItemCategoryId && (name != null ? name.equals(that.name) : that.name == null);

    }

    @Override
    public int hashCode() {
        int result = taskItemCategoryId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TaskItemCategory{" +
                "taskItemCategoryId=" + taskItemCategoryId +
                ", name='" + name + '\'' +
                '}';
    }
}
