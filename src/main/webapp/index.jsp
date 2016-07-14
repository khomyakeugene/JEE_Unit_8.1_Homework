<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>To do list</title>
    <link rel="stylesheet" href="styles.css">
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
        <div class="todoUpdatePanel">
            <form class="form-horizontal" role="form" action="update_tasks" method="post">
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

                <!-- Update Button -->
                <button class="button blue">Update Tasks</button>
            </form>
        </div>
    </div>

    <hr/>

    <!-- Item Input Form -->
    <div class="taskItemData">
        <form class="form-horizontal" role="form" action="add_task" method="post">
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

            <button class="button blue">Add task</button>
        </form>
    </div>
</div>


</body>
</html>
