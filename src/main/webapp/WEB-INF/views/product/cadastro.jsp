<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib	uri="http://www.springframework.org/security/tags"	prefix="sec"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="cdc" tagdir="/WEB-INF/tags" %> <!--  tag para invoke do jsp -->
<cdc:page title="Cadastro de Produtos">
<link rel="stylesheet" href="<c:url	value='/resources/css/bootstrap.min.css'/>">
	<!-- servletRelativeAction para security csrInput -->
	<form:form servletRelativeAction="/products" 
	enctype="multipart/form-data" commandName="product" 
	class="form-horizontal" method="POST" 
	style="margin-left: 30%;margin-top: 20px">
		<div class="form-group">
			<div class="col-sm-6 col-md-6 col-lg-6">
				<label class="control-label" for="title">T�tulo:</label>
				<form:input path="title" class="form-control"  type="text" name="title" id="title" />
				<form:errors path="title"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-6 col-md-6 col-lg-6">
				<label class="control-label" for="description">Descricao:</label>
				<form:textarea path="description" class="form-control" rows="2" cols="20" name="description"></form:textarea>
				<form:errors path="description"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-6 col-md-6 col-lg-6">
			<label class="control-label" for="autor">Autor:</label>
			<form:input path="author" class="form-control" type="text" name="author" id="author" />
			<form:errors path="author"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-6 col-md-6 col-lg-6">
			<label class="control-label" for="numPag">Paginas:</label>
			<form:input path="numPage" class="form-control" type="number" name="numPage" id="numPage" />
			<form:errors path="numPage"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-6 col-md-6 col-lg-6">
				<label class="control-label" for="releaseDate">Data de Lan�amento:</label>
				<form:input path="releaseDate" class="form-control" type="date" name="releaseDate" id="releaseDate" />
				<form:errors path="releaseDate"/>
			</div>
		</div>
		<div class="form-group">
		<div class="col-sm-6 col-md-6 col-lg-6">
			<label class="control-label" for="summary">Sumario:</label>
			<input class="form-control" type="file" name="summary" id="summary" />
			<form:errors path="summaryPath" />
		</div>
		</div>
		<div class="form-group">
			<div class="col-sm-6 col-md-6 col-lg-6">
			<c:forEach items="${tiposLivros}" var="tipo" varStatus="index">
				<div>
					<label class="control-label">${tipo} - ${index.index}</label>
					<form:input path="prices[${index.index}].price" class="form-control" type="text" name="prices[${index.index}].price" />
					<form:errors path="prices[${index.index}].price" />
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
	</form:form>
</cdc:page>