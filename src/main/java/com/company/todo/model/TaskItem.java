package com.company.todo.model;

/**
 * Created by Yevhen on 10.07.2016.
 */
public class TaskItem {
    private int taskId;
    private String name;
    private boolean complete;
    private TaskItemCategory taskItemCategory = new TaskItemCategory();

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
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

        return taskId == taskItem.taskId && complete == taskItem.complete && (name != null ? name.equals(taskItem.name) :
                taskItem.name == null && (taskItemCategory != null ? taskItemCategory.equals(taskItem.taskItemCategory) :
                        taskItem.taskItemCategory == null));

    }

    @Override
    public int hashCode() {
        int result = taskId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (complete ? 1 : 0);
        result = 31 * result + (taskItemCategory != null ? taskItemCategory.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TaskItem{" +
                "taskId=" + taskId +
                ", name='" + name + '\'' +
                ", complete=" + complete +
                ", taskItemCategory=" + taskItemCategory +
                '}';
    }
}
