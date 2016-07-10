package com.company.todo.model;

/**
 * Created by Yevhen on 10.07.2016.
 */
public class TaskCategory {
    private int taskCategoryId;
    private String name;

    public int getTaskCategoryId() {
        return taskCategoryId;
    }

    public void setTaskCategoryId(int taskCategoryId) {
        this.taskCategoryId = taskCategoryId;
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
        if (!(o instanceof TaskCategory)) return false;

        TaskCategory that = (TaskCategory) o;

        return taskCategoryId == that.taskCategoryId && (name != null ? name.equals(that.name) : that.name == null);

    }

    @Override
    public int hashCode() {
        int result = taskCategoryId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TaskCategory{" +
                "taskCategoryId=" + taskCategoryId +
                ", name='" + name + '\'' +
                '}';
    }
}
