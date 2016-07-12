package com.company.todo.model;

/**
 * Created by Yevhen on 10.07.2016.
 */
public class TaskItem {
    private int taskItemId;
    private String name;
    private boolean complete;
    private TaskItemCategory taskItemCategory = new TaskItemCategory();

    public int getTaskItemId() {
        return taskItemId;
    }

    public void setTaskItemId(int taskItemId) {
        this.taskItemId = taskItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public TaskItemCategory getTaskItemCategory() {
        return taskItemCategory;
    }

    public void setTaskItemCategory(TaskItemCategory taskItemCategory) {
        this.taskItemCategory = taskItemCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskItem)) return false;

        TaskItem taskItem = (TaskItem) o;

        return taskItemId == taskItem.taskItemId && complete == taskItem.complete && (name != null ? name.equals(taskItem.name) :
                taskItem.name == null && (taskItemCategory != null ? taskItemCategory.equals(taskItem.taskItemCategory) :
                        taskItem.taskItemCategory == null));

    }

    @Override
    public int hashCode() {
        int result = taskItemId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (complete ? 1 : 0);
        result = 31 * result + (taskItemCategory != null ? taskItemCategory.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TaskItem{" +
                "taskItemId=" + taskItemId +
                ", name='" + name + '\'' +
                ", complete=" + complete +
                ", taskItemCategory=" + taskItemCategory +
                '}';
    }
}
