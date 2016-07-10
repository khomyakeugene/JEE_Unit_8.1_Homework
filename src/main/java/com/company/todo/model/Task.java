package com.company.todo.model;

/**
 * Created by Yevhen on 10.07.2016.
 */
public class Task {
    private int taskId;
    private String name;
    private boolean complete;
    private TaskCategory taskCategory = new TaskCategory();

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

    public TaskCategory getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(TaskCategory taskCategory) {
        this.taskCategory = taskCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;

        Task task = (Task) o;

        return taskId == task.taskId && complete == task.complete && (name != null ? name.equals(task.name) :
                task.name == null && (taskCategory != null ? taskCategory.equals(task.taskCategory) :
                        task.taskCategory == null));

    }

    @Override
    public int hashCode() {
        int result = taskId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (complete ? 1 : 0);
        result = 31 * result + (taskCategory != null ? taskCategory.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", name='" + name + '\'' +
                ", complete=" + complete +
                ", taskCategory=" + taskCategory +
                '}';
    }
}
