<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Resultado</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header>
		<div class="container">
			<div class="section-title">
				<h3>Resultado:</h3>
			</div>
		</div>
	</header>
	<section class="main-content">
		<div class="container">
			<div class="response-manager">
 				<h4>${requestScope["message"]}</h4> 

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