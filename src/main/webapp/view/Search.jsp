<%@ page language="java" pageEncoding="UTF-8" session="true" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Поиск клиентов</title>
    <link rel="stylesheet" type="text/css" href="/wpc/css/style.css" />
</head>
<body>
    <div class="main">
        <div class="header">
            <h1>Поиск клиентов</h1>
            <a href="${pageContext.servletContext.contextPath}/clinic/"><img src="/wpc/images/edit.jpg"></a>
        </div>
        <div class="center">
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
    </div>
</body>
</html>