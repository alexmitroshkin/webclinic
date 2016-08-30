<%@ page language="java" pageEncoding="UTF-8" session="true" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Клиника домашних питомцев</title>
    <%--Bootstrap--%>
    <link rel="stylesheett" href="../bootstrap/css/bootstrap-theme.min.css"/>
    <link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>
<body>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="../bootstrap/js/bootstrap.min.js"></script>
    <div class="main">
        <div class="header">
            <h1>Клиника домашних питомцев</h1>
            <a href="${pageContext.servletContext.contextPath}/clinic/"><img src="../images/logo.png"></a>
        </div>

        <div class="center">
            <div class="addNewClientForm">
                <form action="${pageContext.servletContext.contextPath}/clinic/" method='GET'>
                    Добавить клиента:
                    <input type="submit" name="addClient" value=" " class="addButton" />
                </form>
            </div>

            <div class="clientTable">
                <table border="1">
                    <tr>
                        <th>Имя клиента</th>
                        <th>Имя питомцев</th>
                        <th>Действия</th>
                    </tr>
                    <c:forEach items="${clients}" var="client" varStatus="status">
                        <tr>
                            <td>${client.fullName}</td>
                            <td>
                                <c:forEach items="${client.pets}" var="pet" varStatus="status">
                                    ${pet.name}
                                    <br>
                                </c:forEach>
                            </td>
                            <td>
                                <a href="${pageContext.servletContext.contextPath}/clinic/delete/?id=${client.id}">Удалить</a>
                                <br>
                                <a href="${pageContext.servletContext.contextPath}/clinic/edit/?id=${client.id}">Изменить</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

        <%--<div class="search">--%>
            <%--<h2 align="center">Поиск клиентов</h2>--%>
            <%--<br>--%>
            <%--<label for="name">Имя клиента: </label>--%>
            <%--<input type="search" name="name" value="" id="name" />--%>
        <%--</div>--%>
    </div>

</body>
</html>
