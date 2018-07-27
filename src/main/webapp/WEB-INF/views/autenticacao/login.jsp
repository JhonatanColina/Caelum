<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@taglib	uri="http://www.springframework.org/security/tags"	prefix="sec"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	<%@ taglib prefix="cdc" tagdir="/WEB-INF/tags" %> <!--  tag para invoke do jsp -->
<cdc:page title="Login Page">
<link rel="stylesheet" href="<c:url	value='/resources/css/bootstrap.min.css'/>">
<h1>Bem vindo a casa do codigo</h1> 
<form:form class="form-horizontal col-sm-5 col-md-5 col-lg-5" servletRelativeAction="/login" method="post">
	<c:if test="${param.error != null}">
		<p><spring:message code="message.badCredentials"></spring:message></p>
	</c:if>
	
	<div>
		<label class="control-label" for="username">Usuario:</label>
		<input class="form-control" name="username" type="text"/>
	</div>
	<div>
		<label class="control-label" for="password">Senha:</label>
		<input class="form-control" name="password" type="password"/>
	</div>
	<br>
	<div>
		<input class="btn btn-primary"" type="submit" value="Login"/>
	</div>
</form:form>
</cdc:page>