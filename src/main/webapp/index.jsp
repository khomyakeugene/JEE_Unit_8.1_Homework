<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>To do list</title>
    <style>
        button.blue {
            background-color: #6a82ff;
            color: white;
            width: 120px;
            padding: 5px 10px;
            border-radius: 5px;
            border: 0;
            cursor: pointer;
            box-shadow: inset 0 1px 0 0 #FFFFFF;
        }
        button.blue:hover {
            background: #5e71ff;
        }
        button.blue:active {
            background: #5e71ff;
            box-shadow: inset 1px 1px 0 0 #6447e2;
        }
    </style>
</head>

<body>

<div style="background-color:black; color:lightgrey; padding: 20px;">
    <p>My Tasks</p>
</div>

<div class="container">
    <h1>My ToDo List</h1>

    <hr/>

    <!-- The Task List -->
    <div class = "todoList">
        <table class="table table-bordered table-striped" id="todoItems">
            <thead>
            <tr>
                <th>Name</th>
                <th>Category</th>
                <th>Complete</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>

        <!-- Update Button -->
        <div class="todoUpdatePanel">
            <form class="form-horizontal" role="form">
                <button type="button" class="blue">Update Tasks</button>
            </form>
        </div>

    </div>

    <hr/>

    <!-- Item Input Form -->
    <div class="todoForm">
        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="inputItemName" class="col-sm-2">Task Name</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="inputItemName" placeholder="Enter name">
                </div>
            </div>

            <div class="form-group">
                <label for="inputItemCategory" class="col-sm-2">Task Category</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="inputItemCategory" placeholder="Enter category">
                </div>
            </div>

            <button type="button" class="blue">Add Task</button>
        </form>
    </div>
</div>


<table width="100%" border="1" align="center">
    <tr bgcolor="#949494">
        <th>Header Name</th>
        <th>Header Value</th>
    </tr>
    <c:forEach items="${header.keySet()}" var="headerName">
        <tr>
            <td><c:out value="${headerName}"/></td>
            <td><c:out value="${header.get(headerName)}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
