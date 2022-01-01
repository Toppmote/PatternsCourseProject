<%--
  Created by IntelliJ IDEA.
  User: Pavel
  Date: 30.12.2021
  Time: 3:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${user.FIO}</title>
    <link href="/css/headingStyle.css" rel="stylesheet" type="text/css">
    <link href="/css/navbarStyle.css" rel="stylesheet" type="text/css">
    <link href="/css/tableStyle.css" rel="stylesheet" type="text/css">
    <link href="/css/mainContainer.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="navbar">
    <button onclick="document.location.href = '/main_page'"><i class="fa fa-caret-left"></i>Вернуться</button>
</div>
<div class="main-container">
    <h3>Данные о пользователе ${user.FIO}</h3>
    <strong>id: ${user.userId}&nbsp;<br/>
        Дата регистрации: ${user.regDate}&nbsp;<br/>
        Статус: ${user.stringState}&nbsp;</strong>
    <h3>Список друзей:&nbsp;</h3>
    <table>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Имя</th>
            <th scope="col">Дата регистрации</th>
            <th scope="col">Состояние</th>
        </tr>
        <c:forEach items="${user.friendsList}" var="friend">
            <tr>
                <td><a href="/main_page/user/${friend.userId}">${friend.userId}</a></td>
                <td>${friend.FIO}</td>
                <td>${friend.regDate}</td>
                <td>${friend.stringState}</td>
            </tr>
        </c:forEach>
    </table>
    <h3>Лента активности:&nbsp;</h3>
    <table>
        <tr>
            <th scope="col">Описание</th>
            <th scope="col">Дата</th>
        </tr>
        <c:forEach items="${user.activityFeed}" var="activity">
            <tr>
                <td>${activity.description}</td>
                <td>${activity.date}</td>
            </tr>
        </c:forEach>
    </table>
    <h3>Результаты фильтрации для ${user.FIO}:&nbsp;</h3>
    <table>
        <tr>
            <th scope="col">Текст</th>
            <th scope="col">Процент вредоносности</th>
        </tr>
        <c:forEach items="${filterResults}" var="result">
            <tr>
                <td>${result.text}</td>
                <td>${result.harmPercent}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
