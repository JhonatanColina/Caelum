<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listagem de Produtos</title>
<link rel="stylesheet" href="<c:url	value='/resources/css/bootstrap.min.css'/>">
</head>
<body>
	<h1 style="text-align: center;">Listagem de Livros</h1>
	<h1>${success}</h1>
	<table class="table table-bordered table-stripped" border="1">
	<th>Titulo</th>
	<th>Autor</th>
	<th>Paginas</th>
	<th>Tipos</th>
	<c:forEach items="${books}" var="book">
	<tr>
	<c:url var="linkToProduct"  value="/products/${book.id}" />
		<td><a href="${linkToProduct}">${book.title}</a></td>
		<td>${book.author}</td>
		<td>${book.numPage}</td>
		<td>
			<c:forEach items="${book.prices}" var="price">
			${price.booktype}: ${price.price}
			</c:forEach>
		</td>
	</tr>
	</c:forEach>
	</table>
</body>
</html>