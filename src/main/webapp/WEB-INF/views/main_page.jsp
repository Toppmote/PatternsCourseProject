<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Главная страница</title>
    <link href="/css/headingStyle.css" rel="stylesheet" type="text/css">
    <link href="/css/navbarStyle.css" rel="stylesheet" type="text/css">
    <link href="/css/tableStyle.css" rel="stylesheet" type="text/css">
    <link href="/css/mainContainer.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<div class="navbar">
    <ul>
        <li>
            <button onclick='location.href="/main_screen/generate_users"'>Сгенерировать</button>
        </li>
        <li>
            <button onclick='location.href="/main_screen/do_filtration"'>Отфильтровать заново</button>
        </li>
        <li>
            <button>Изменить алгоритм фильтрации<i class="fa fa-caret-down"></i></button>
            <ul class="dropdown-menu">
                <li>
                    <button onclick='location.href="/main_screen/set_algorithm/standard"'>Стандартный алгоритм</button>
                </li>
                <li>
                    <button onclick='location.href="/main_screen/set_algorithm/strict"'>Строгий алгоритм</button>
                </li>
            </ul>
        </li>
    </ul>
</div>
<div class="main-container">
    <h3>Текущий алгоритм фильтрации: ${currentAlg}</h3>
    <div class="header"><h1>Таблица пользователей</h1></div>
    <table>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Имя</th>
            <th scope="col">Дата регистрации</th>
            <th scope="col">Состояние</th>
        </tr>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td><a href="/main_screen/user/${user.userId}">${user.userId}</a></td>
                <td>${user.FIO}</td>
                <td>${user.regDate}</td>
                <td>${user.stringState}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
