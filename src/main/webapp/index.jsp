<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>To do list</title>
    <!--
        <link rel="stylesheet" href="styles.css">
    -->
    <style>
        .button {
            display: inline-block;
            zoom: 1; /* zoom and *display = ie7 hack for display:inline-block */
            *display: inline;
            vertical-align: baseline;
            margin: 0 2px;
            outline: none;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            font: 14px/100% Arial, Helvetica, sans-serif;
            padding: .5em 2em .55em;
            text-shadow: 0 1px 1px rgba(0, 0, 0, .3);
            -webkit-border-radius: .5em;
            -moz-border-radius: .5em;
            border-radius: .5em;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
        }
        .button:hover {
            text-decoration: none;
        }
        .button:active {
            position: relative;
            top: 1px;
        }
        .blue {
            color: #d9eef7;
            border: solid 1px #0076a3;
            background: #0095cd;
            background: -webkit-gradient(linear, left top, left bottom, from(#00adee), to(#0078a5));
            background: -moz-linear-gradient(top, #00adee, #0078a5);
        }
        .blue:hover {
            background: #007ead;
            background: -webkit-gradient(linear, left top, left bottom, from(#0095cc), to(#00678e));
            background: -moz-linear-gradient(top, #0095cc, #00678e);
        }

        .blue:active {
            color: #80bed6;
            background: -webkit-gradient(linear, left top, left bottom, from(#0078a5), to(#00adee));
            background: -moz-linear-gradient(top, #0078a5, #00adee);
        }

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
            background-color: rgba(247, 225, 181, 0.64);
        }
        .horizontal-container-space-between {
            display: flex;
            flex-direction: row;
            flex-wrap: wrap;
            justify-content: space-between;
            padding-right: 15px;
            padding-left: 15px;
            margin-right: auto;
            margin-left: auto;
            border-radius: 6px;
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
