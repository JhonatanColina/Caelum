<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib	uri="http://www.springframework.org/security/tags"	prefix="sec"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cdc" tagdir="/WEB-INF/tags" %> <!--  tag para invoke do jsp -->

<cdc:page title="Listagem de Produtos">
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
</cdc:page>