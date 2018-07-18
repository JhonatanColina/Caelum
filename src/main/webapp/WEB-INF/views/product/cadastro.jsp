<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de Livros</title>
<link rel="stylesheet" href="<c:url	value='/resources/css/bootstrap.min.css'/>">
</head>
<body>
	<form class="form-horizontal" method="POST" action="<c:url	value='/products'/>" style="margin-left: 30%;margin-top: 20px">
		<div class="form-group">
			<div class="col-sm-6 col-md-6 col-lg-6">
				<label class="control-label" for="title">Título:</label>
				<input class="form-control"  type="text" name="title" id="title" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-6 col-md-6 col-lg-6">
				<label class="control-label" for="description">Descricao:</label>
				<textarea class="form-control" rows="2" cols="20" name="description"></textarea>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-6 col-md-6 col-lg-6">
			<label class="control-label" for="autor">Autor:</label>
			<input class="form-control" type="text" name="author" id="author" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-6 col-md-6 col-lg-6">
			<label class="control-label" for="numPag">Paginas:</label>
			<input class="form-control" type="number" name="numPage" id="numPage" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-6 col-md-6 col-lg-6">
			<c:forEach items="${tiposLivros}" var="tipo" varStatus="index">
				<div>
					<label class="control-label">${tipo} - ${index.index}</label>
					<input class="form-control" type="text" name="prices[${index.index}].price" />
					<input type="hidden" value="${tipo}" name="prices[${index.index}].booktype" />
				</div>
			</c:forEach>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-6 col-md-6 col-lg-6" style="text-align: right;">
				<input class="btn btn-primary" type="submit" value="Salvar" />
			</div>
		</div>
	</form>
</body>
</html>