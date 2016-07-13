package com.company.todo.model;

import com.company.todo.model.proto.DataItem;

/**
 * Created by Yevhen on 10.07.2016.
 */
public class TaskItem extends DataItem {
    private boolean complete;
    private TaskItemCategory taskItemCategory = new TaskItemCategory();

    public int getTaskItemId() {
        return getId();
    }

    public void setTaskItemId(int taskItemId) {
        setId(taskItemId);
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
        if (!super.equals(o)) return false;

        TaskItem taskItem = (TaskItem) o;

        return complete == taskItem.complete && (taskItemCategory != null ?
                taskItemCategory.equals(taskItem.taskItemCategory) : taskItem.taskItemCategory == null);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (complete ? 1 : 0);
        result = 31 * result + (taskItemCategory != null ? taskItemCategory.hashCode() : 0);

        return result;
    }

    @Override
    public String toString() {
        return "TaskItem{" +
                super.toString() +
                ", complete=" + complete +
                ", taskItemCategory=" + taskItemCategory +
                '}';
    }
}
