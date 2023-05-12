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
<%@ page import="domain.Printer"%>
<%@ page import="domain.Product"%>
<%
    Product r1 = new Product("KJEWWZ", "Canon", "принтер");
    Product r2 = new Product("NLKJFD", "Artec", "принтер");
    Product r3 = new Product("WGLINP", "Citizen", "принтер");
    Product r4 = new Product("ZVMNYY", "Acer", "принтер");
    Product[] products = new Product[]{r1, r2, r3, r4};
    pageContext.setAttribute("products", products);
    Printer p1 = new Printer(1L, r1, "цветной", "лазерный", "60000руб", "200шт");
    Printer p2 = new Printer(2L, r2, "чёрно-белый", "струйный", "100000руб", "120шт");
    Printer p3= new Printer(3L, r3, "чёрно-белый", "лазерный", "40000руб", "150шт");
    Printer p4 = new Printer(4L, r4, "цветной", "лазерный",  "44000руб", "290шт");
    Printer[] printerAll = new Printer[]{p1, p2, p3, p4};
    pageContext.setAttribute("printerAll", printerAll);
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Rabbit</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="../jspf/nav-bar-pr.jsp" />

<div id="main">
    <aside class="leftAside">
        <h3>Список Принтеров</h3>
        <table>
            <thead>
            <tr>
                <th>Код</th>
                <th>Наименование модели</th>
                <th>Печать</th>
                <th>Тип устройства</th>
                <th>Цена</th>
                <th>Количество на складе</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="pr" items="${printerAll}">
                <tr>
                    <td>${pr.id}</td>
                    <td>${pr.model}</td>
                    <td>${pr.color}</td>
                    <td>${pr.type}</td>
                    <td>${pr.price}</td>
                    <td>${pr.count}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </aside>
    <section>
        <article>
            <h3>Данные по Принтеру</h3>
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
                        <label for="color">Печать</label>
                        <input type="text" name="color" id="color"/>
                    </p>
                    <p>
                        <label for="type">Тип устройства</label>
                        <input type="text" name="type" id="type"/>
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
