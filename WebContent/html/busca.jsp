<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<!-- Latest compiled and minified CSS & JS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
		<script src="//code.jquery.com/jquery.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
		<script src="${pageContext.request.contextPath}/js/script.js"></script>

        <title>Busca</title>
    </head>
    <body>    
    <header>
        <div class="container">
			<div class="section-title">
				<h3>Busca:</h3>
			</div>
		</div>
	</header>
	<section class="main-content">
		<div class="container">
			<div class="response-manager">
				<div class="table-responsive table-stripped">
				  <table class="table">
				    <thead>
				      <tr>
				        <th>Nome</th>
				        <th>Sobrenome</th>
				        <th>Alterar</th>
				        <th>Excluir</th>
				      </tr>
				    </thead>
				    <tbody>
				      <c:forEach var="resultado" items="${lista}">
				      	<tr>
				      		<td>${resultado.nome}</td>
				      		<td>${resultado.sobrenome}</td>
				      		<td><a href="${pageContext.request.contextPath}/ServletControle?cmd=carregar&id=${resultado.idPerfil}">(Alterar)</a></td>
				      		<td><a href="${pageContext.request.contextPath}/ServletControle?cmd=excluir&id=${resultado.idPerfil}">(Excluir)</a></td>
				      	</tr>
					  </c:forEach>
					</tbody>
				  </table>
				</div>
			</div>
		</div>
	</section>
	<footer>
		<div class="container">
			<div class="copyright">
				<p>Irreprováveis 2016, no rights reserved.</p>
			</div>
		</div>
	</footer>
    </body>
</html>
