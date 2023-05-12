<%--
  Created by IntelliJ IDEA.
  User: Бублик
  Date: 04.04.2023
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="nav-bar">
    <a href="${pageContext.request.contextPath}/homeServlet" class="nav-bar__img-container">
        <img class="nav-bar__img" src="images/Rabbit.svg"/>
    </a>

    <div class="nav-bar__wrapper">
        <div class="nav-bar__tabs">
            <div class="nav-bar__btn">
                <a href="${pageContext.request.contextPath}/pcServlet">Пк</a>
            </div>
            <div class="nav-bar__btn">
                <a href="${pageContext.request.contextPath}/laptopServlet">Ноутбуки</a>
            </div>
            <div class="nav-bar__btn">
                <a href="${pageContext.request.contextPath}/printerServlet">Принтеры</a>
            </div>
        </div>
    </div>
</div>