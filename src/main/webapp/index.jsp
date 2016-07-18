<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>To do list</title>

    <link rel="stylesheet" href="styles.css">

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
            top: auto;
            padding-right: 15px;
            padding-left: 15px;
            margin-right: auto;
            margin-left: auto;
            border-radius: 6px;
            margin-bottom: 20px;
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
            border: 1px solid black;
            border-collapse: collapse;
            padding: 5px;
        }

        .dangerous {
            color: red;
            background-color: rgba(233, 150, 122, 0.45);
        }

        .table-background-color {
            background-color: rgba(247, 225, 181, 0.64);
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
            <div class="container">
                <button type="submit" class="button blue" name="action" value="updateTasks" formnovalidate>
                    Update Tasks
                </button>
            </div>

            <hr/>

            <!-- Item Input Form -->
            <div class="todoForm">
                <div class="container">
                    <label for="taskItemName" class="col-sm-2">Task Name</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="taskItemName"
                               name="task_item_name" placeholder="Enter name" required autofocus>
                    </div>
                </div>

                <div class="container">
                    <label for="taskItemCategoryName" class="col-sm-2">Task Category</label>
                    <div class="col-sm-10">
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
