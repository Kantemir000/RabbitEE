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
<%@ page import="domain.Pc"%>
<%@ page import="domain.Product"%>
<%
    Product r1 = new Product("2XLL", "Alta", "персональный компьютер");
    Product r2 = new Product("SOG5", "Amdahl", "персональный компьютер");
    Product r3 = new Product("FL4L", "Avalon", "персональный компьютер");
    Product r4 = new Product("MFF1", "Dell", "персональный компьютер");
    Product[] products = new Product[]{r1, r2, r3, r4};
    pageContext.setAttribute("products", products);
    Pc p1 = new Pc(1L, r1, "3.2GHz", "16gb", "1000gb", "CD-RW", "50000руб", "300шт");
    Pc p2 = new Pc(2L, r2, "2.6GHz", "32gb", "2000gb", "CD-RW", "80000руб", "210шт");
    Pc p3= new Pc(3L, r3, "2.8GHz", "8gb", "5000gb", "CD-RW", "30000руб", "190шт");
    Pc p4 = new Pc(4L, r4, "3GHz", "16gb", "2000gb", "CD-RW", "45000руб", "250шт");
    Pc[] pcAll = new Pc[]{p1, p2, p3, p4};
    pageContext.setAttribute("pcAll", pcAll);
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Rabbit</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <jsp:include page="../jspf/nav-bar-pc.jsp" />

    <div id="main">
        <aside class="leftAside">
            <h3>Список ПК</h3>
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
                <c:forEach var="pc" items="${pcAll}">
                    <tr>
                        <td>${pc.id}</td>
                        <td>${pc.model}</td>
                        <td>${pc.speed}</td>
                        <td>${pc.ram}</td>
                        <td>${pc.hd}</td>
                        <td>${pc.cd}</td>
                        <td>${pc.price}</td>
                        <td>${pc.count}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </aside>
        <section>
            <article>
                <h3>Данные по ПК</h3>
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
                            <label for="sd">Тип cd-диска</label>
                            <input type="text" name="sd" id="sd"/>
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
