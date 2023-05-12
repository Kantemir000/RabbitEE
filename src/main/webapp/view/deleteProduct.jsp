<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%--<%@ taglib prefix="c" uri="jakarta.tags.core" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="domain.Product"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <title>Rabbit</title>
   <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">   -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="../jspf/nav-bar.jsp" />

<div id="main">
    <aside class="leftAside">
	    <h3>Список Товаров</h3>
	        <table>
	            <thead>
	            <tr>
	                <th>Наименование модели</th>
	                <th>Производитель</th>
	                <th>Тип товара</th>
	                <th>Редактировать</th>
	                <th>Удалить</th>
	            </tr>
	            </thead>
	            <tbody>
	            <c:forEach var="prod" items="${products}">
	                <tr>
	                    <td>${prod.model}</td>
	                    <td>${prod.maker}</td>
	                    <td>${prod.type}</td>
	                    <td><a href='<c:url value="/editProductServlet?id=${prod.model}" />'role="button" class="btn btn-outline-primary"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-square" viewBox="0 0 16 16">
						  <path d="M15.502 1.94a.5.5 0 0 1 0 .706L14.459 3.69l-2-2L13.502.646a.5.5 0 0 1 .707 0l1.293 1.293zm-1.75 2.456-2-2L4.939 9.21a.5.5 0 0 0-.121.196l-.805 2.414a.25.25 0 0 0 .316.316l2.414-.805a.5.5 0 0 0 .196-.12l6.813-6.814z"/>
						  <path fill-rule="evenodd" d="M1 13.5A1.5 1.5 0 0 0 2.5 15h11a1.5 1.5 0 0 0 1.5-1.5v-6a.5.5 0 0 0-1 0v6a.5.5 0 0 1-.5.5h-11a.5.5 0 0 1-.5-.5v-11a.5.5 0 0 1 .5-.5H9a.5.5 0 0 0 0-1H2.5A1.5 1.5 0 0 0 1 2.5v11z"/>
						</svg></a>  </td> 
						                    <td><a href='<c:url value="/deleteProductServlet?id=${prod.model}"/>'  role="button" class="btn btn-outline-primary"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash-fill" viewBox="0 0 16 16">
						  <path d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"/>
						</svg> </a></td>
						                </tr>
						            </c:forEach>
	            </tbody>
	        </table>
    </aside>
    <section>
        <article>
            <h3>Данные по товару</h3>
            <div class="text-article">
                <form method="POST" action="" id='edit_product'>
                    <p>
                        <label for="model">Наименование модели</label>
                        <input type="text" name="model" id="model" value="${productEdit[0].getModel()}" readonly/>
                    </p>
                    <p>
                        <label for="maker">Производитель</label>
                        <input type="text" readonly name="maker" id="maker" value="${productEdit[0].getMaker()}"/>
                    </p>
                    <p>
                        <label for="type">Тип товара</label>
                        <input type="text" readonly name="type" id="type" value="${productEdit[0].getType()}"/>
                    </p>
                </form>
                <p>
                    <button type="submit" form='edit_product'>Удалить</button>
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
