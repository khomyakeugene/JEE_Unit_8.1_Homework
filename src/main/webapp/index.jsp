<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>To do list</title>
        <link rel="stylesheet" href="buttons.css">
    <style>
        .navbar-fixed-top-reverse-colour {
            position: relative;
            top: 0;
            left: 0;
            margin-left: 0;
            width: 100%;
            border-width: 0 0 1px;
            background-color: black;
            color: #d3d3d3;
            padding: 20px;
            font-family: Helvetica, serif
        }
        .container {
            position: relative;
            padding-right: 15px;
            padding-left: 15px;
            margin-bottom: 20px;
            font-family: Arial, serif;
        }
        table {
            border-spacing: 5px;
            width: 100%;
            max-width: 100%;
            margin-bottom: 20px;
            text-align: left;
            font-family: Arial, serif;
        }
        table, th, td {
            border: 1px solid #afafaf;
            border-collapse: collapse;
            padding: 7px;
        }
        .dangerous-color, .dangerous {
            font-weight: bold;
            color: red;
        }
        .dangerous {
            background-color: rgba(233, 150, 122, 0.45);
        }
        .table-background-color {
            background-color: rgba(255, 233, 192, 0.49);
        }
        .horizontal-container-space-between {
            display: flex;
            flex-direction: row;
            flex-wrap: wrap;
            justify-content: space-between;
            padding-right: 15px;
            padding-left: 15px;
            margin-bottom: 20px;
            font-family: Arial, serif;
        }
        .horizontal-container-flex-start {
            display: flex;
            padding-top: 10px;
            padding-bottom: 10px;
            font-family: Arial, serif;
        }
        .horizontal-container-left-part {
            width: 200px;
        }
        .horizontal-container-right-part {
            width: 100%;
        }
        .form-label {
            font-weight: bold;
        }
        .form-control {
            border-radius: 5px;
            padding: 7px;
            width: 100%;
        }

    </style>

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
