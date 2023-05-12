<%--
  Created by IntelliJ IDEA.
  User: Бублик
  Date: 12.04.2023
  Time: 12:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="domain.Laptop"%>
<%@ page import="domain.Product"%>
<%
    Product r1 = new Product("NVL4", "Lenovo", "ноутбук");
    Product r2 = new Product("XNL5", "Apple", "ноутбук");
    Product r3 = new Product("K3ZX", "ASUS", "ноутбук");
    Product r4 = new Product("VC7X", "HUAWEI", "ноутбук");
    Product[] products = new Product[]{r1, r2, r3, r4};
    pageContext.setAttribute("products", products);
    Laptop p1 = new Laptop(1L, r1, "3.5GHz", "16gb", "1000gb", "1920 × 1080", "60000руб", "200шт");
    Laptop p2 = new Laptop(2L, r2, "3.2GHz", "32gb", "2000gb", "2048 × 1080", "100000руб", "120шт");
    Laptop p3= new Laptop(3L, r3, "2.8GHz", "8gb", "5000gb", "2560 × 1440", "40000руб", "150шт");
    Laptop p4 = new Laptop(4L, r4, "3.2GHz", "16gb", "2000gb", "1920 × 1080", "44000руб", "290шт");
    Laptop[] laptopAll = new Laptop[]{p1, p2, p3, p4};
    pageContext.setAttribute("laptopAll", laptopAll);
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Rabbit</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="../jspf/nav-bar-lp.jsp" />

<div id="main">
    <aside class="leftAside">
        <h3>Список Ноутбуков</h3>
        <table>
            <thead>
            <tr>
                <th>Код</th>
                <th>Наименование модели</th>
                <th>Быстродействие процессора</th>
                <th>Объем оперативной памяти</th>
                <th>Объем памяти жесткого диска</th>
                <th>Тип cd-диска</th>
                <th>Цена</th>
                <th>Количество на складе</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="lp" items="${laptopAll}">
                <tr>
                    <td>${lp.id}</td>
                    <td>${lp.model}</td>
                    <td>${lp.speed}</td>
                    <td>${lp.ram}</td>
                    <td>${lp.hd}</td>
                    <td>${lp.screen}</td>
                    <td>${lp.price}</td>
                    <td>${lp.count}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </aside>
    <section>
        <article>
            <h3>Данные по Ноутбуку</h3>
            <div class="text-article">
                <form method="POST" action="">
                    <p>
                        <label for="model">Наименование модели</label>
                        <select id="model">
                            <%--<option disabled>Выберите модель</option>--%>
                            <c:forEach var="product" items="${products}">
                                <option value="${product}">
                                        ${product.model}
                                </option>
                            </c:forEach>
                        </select>
                    </p>
                    <p>
                        <label for="speed">Быстродействие процессора</label>
                        <input type="text" name="speed" id="speed"/>
                    </p>
                    <p>
                        <label for="ram">Объем оперативной памяти</label>
                        <input type="text" name="ram" id="ram"/>
                    </p>
                    <p>
                        <label for="hd">Объем памяти жесткого диска</label>
                        <input type="text" name="hd" id="hd"/>
                    </p>
                    <p>
                        <label for="screen">Диагональ экрана</label>
                        <input type="text" name="screen" id="screen"/>
                    </p>
                    <p>
                        <label for="price">Цена</label>
                        <input type="text" name="price" id="price"/>
                    </p>
                    <p>
                        <label for="count">Количество на складе</label>
                        <input type="text" name="count" id="count"/>
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