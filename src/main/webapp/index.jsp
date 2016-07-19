<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>To do list</title>

    <link rel="stylesheet" href="style.css">

    <jsp:useBean class="com.company.todo.data.TaskItemListReader" id="taskItemListReader"
                 scope="application"/>
</head>

<body>

<div>
    <div class="navbar-fixed-top-reverse-colour">
        My Task
    </div>
    <div class="container">
        <h1>My ToDo List</h1>
        <hr/>
    </div>

    <form action="do_action" method="post">
        <input type="submit" name="action" value="addTask" hidden>

        <div class="container">
            <!-- The Task List -->
            <table class="table" id="todoItems">
                <tr>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Complete</th>
                    <th class="dangerous">To be deleted</th>
                </tr>
                <c:forEach items="${taskItemListReader.taskItemList}" var="taskItem">
                    <tr class="table-background-color">
                        <td><c:out value="${taskItem.name}"/></td>
                        <td><c:out value="${taskItem.taskItemCategory.name}"/></td>
                        <td><input type="checkbox" name="completeTask" value="${taskItem.id}"
                                   <c:if test="${taskItem.complete}">checked</c:if>/></td>
                        <td class="dangerous"><input type="checkbox" name="deleteTask" value="${taskItem.id}"/></td>
                    </tr>
                </c:forEach>
            </table>

            <!-- Update Button -->
            <div class="horizontal-container-space-between">
                <div>
                    <button type="submit" class="button blue" name="action" value="updateTasks" formnovalidate>
                        Update Tasks
                    </button>
                </div>
                <div class="dangerous-color">${errorMessage}</div>
            </div>

            <hr style="margin-bottom: 20px;"/>

            <!-- Item Input Form -->
            <div>
                <div class="horizontal-container-flex-start">
                    <div class="horizontal-container-left-part">
                        <label for="taskItemName" class="form-label">Task Name</label>
                    </div>
                    <div class="horizontal-container-right-part">
                        <input type="text" class="form-control" id="taskItemName"
                               name="task_item_name" placeholder="Enter name" required autofocus>
                    </div>
                </div>

                <div class="horizontal-container-flex-start">
                    <div class="horizontal-container-left-part">
                        <label for="taskItemCategoryName" class="form-label">Task Category</label>
                    </div>
                    <div class="horizontal-container-right-part">
                        <input type="text" class="form-control" id="taskItemCategoryName"
                               name="task_item_category_name" placeholder="Enter category" required>
                    </div>
                </div>

                <div class="container">
                    <button type="submit" class="button blue" name="action" value="addTask">Add Task</button>
                </div>
            </div>
        </div>
    </form>
</div>

</body>
</html>
