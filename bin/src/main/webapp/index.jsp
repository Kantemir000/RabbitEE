<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="domain.Product"%>
<%
    Product r1 = new Product("2XLL", "Alta", "персональный компьютер");
    Product r2 = new Product("SOG5", "Amdahl", "персональный компьютер");
    Product r3 = new Product("FL4L", "Avalon", "персональный компьютер");
    Product r4 = new Product("MFF1", "Dell", "персональный компьютер");
    Product r5 = new Product("NVL4", "Lenovo", "ноутбук");
    Product r6 = new Product("XNL5", "Apple", "ноутбук");
    Product r7 = new Product("K3ZX", "ASUS", "ноутбук");
    Product r8 = new Product("VC7X", "HUAWEI", "ноутбук");
    Product r9 = new Product("KJEWWZ", "Canon", "принтер");
    Product r10 = new Product("NLKJFD", "Artec", "принтер");
    Product r11 = new Product("WGLINP", "Citizen", "принтер");
    Product r12 = new Product("ZVMNYY", "Acer", "принтер");
    Product[] products = new Product[]{r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12};
    int length = products.length;
    pageContext.setAttribute("products", products);
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Rabbit</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="jspf/nav-bar.jsp" />

<div id="main">
    <aside class="leftAside">
        <h3>Список Товаров</h3>
        <table>
            <thead>
            <tr>
                <th>Наименование модели</th>
                <th>Производитель</th>
                <th>Тип товара</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="prod" items="${products}">
                <tr>
                    <td>${prod.model}</td>
                    <td>${prod.maker}</td>
                    <td>${prod.type}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </aside>
    <section>
        <article>
            <h3>Данные по Товарам</h3>
            <div class="text-article">
                <form method="POST" action="">
                    <p>
                        <label for="model">Наименование модели</label>
                        <input type="text" name="model" id="model"/>
                    </p>
                    <p>
                        <label for="maker">Производитель</label>
                        <input type="text" name="maker" id="maker"/>
                    </p>
                    <p>
                        <label for="type">Тип товара</label>
                        <input type="text" name="type" id="type"/>
                    </p>
                </form>
                <p>
                    <button type="submit">Добавить</button>
                </p>
            </div>
        </article>
    </section>
</div>
</body>
</html>

<%--RabbitEE_war_exploded/--%>

<%--<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="RoleServlet">Hello Servlet</a>--%>

<%--
<div class="personal-album">
    <h1 class="personal-album__title">Регистрация</h1>

    <form class="personal-album__form" method="POST">
        <input required id="name" type="text" placeholder="ФИО">
        <input required id="pass" type="password" placeholder="Пароль">

        <div class="personal-album_line"></div>

        <input required id="email" type="email" placeholder="Почта">

        <button class="personal-album_btn">Зарегистрироваться</button>
    </form>
</div>--%>
