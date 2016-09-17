<%@ page language="java" pageEncoding="UTF-8" session="true" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Редактирование клиента</title>
    <link rel="stylesheet" type="text/css" href="/wpc/css/style.css" />
</head>
<body>
    <div class="main">
        <div class="header">
            <h1>Редактирование клиента</h1>
            <a href="${pageContext.servletContext.contextPath}/clinic/"><img src="/wpc/images/edit.jpg"></a>
        </div>

        <div class="center">
            ФИО: <input type="text" name="clientName" value="${client.fullName}" id="clientName">
            <h2>Добавить питомца</h2>
            <form action="${pageContext.servletContext.contextPath}/clinic/edit/" method='POST' id="editForm">
                Кличка питомца: <input type="text" name="petName" value="">
                Тип: <select name="petType">
                <option value="0">Собака
                <option value="1">Кот
            </select>
                Возраст: <input type="number" name="petAge" value="" id="petAge">
                <input type="submit" name="addNewPet" value="Добавить питомца" id="addPettButton">
            </form>

            <div>
                <h3 align="center">Питомцы:</h3>
                <table border="1">
                    <tr>
                        <th>Животное</th>
                        <th>Кличка</th>
                        <th>Возраст</th>
                    </tr>
                    <c:forEach items="${client.pets}" var="pet" varStatus="status">
                        <tr>
                            <td>${pet.type}</td>
                            <td>${pet.name}</td>
                            <td>${pet.age}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>

</body>
</html>
