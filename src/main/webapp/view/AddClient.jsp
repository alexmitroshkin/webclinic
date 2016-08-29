<%@ page language="java" pageEncoding="UTF-8" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Добавление клиента</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css" />
</head>
<body>
    <div id="main">
        <div id="header">
            <h1>Добавление клиента</h1>
            <a href="${pageContext.servletContext.contextPath}/clinic/"><img src="../images/view.jpg"></a>
        </div>

        <div id="center">

                <form action="${pageContext.servletContext.contextPath}/clinic/add/" method='POST' id="addForm">
                    ФИО: <input type="text" name="clientName" value="" id="clientID">
                    <br>
                    Тип: <select name="petType">
                    <option value="0">Собака
                    <option value="1">Кошка
                </select>
                    Кличка питомца: <input type="text" name="petName" value="" id="petID">
                </select>
                    Возраст: <input type="number" name="petAge" value="" id="petAge">
                    <input type="submit" name="addNewClient" value="Добавить клиента" id="addClientButton">

                </form>
        </div>
    </div>
</body>
</html>
