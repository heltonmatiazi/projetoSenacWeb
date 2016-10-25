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

        <title>Erro</title>
    </head>
    <body>    
    <header>
        <div class="container">
            <div class="section-title">
                <h3>Ocorreu um erro...</h3>
            </div>
        </div>
    </header>
    <section class="main-content">
        <div class="container">
            <div class="error-control text-center">
                <h3>Algo inesperado aconteceu...</h3>
                <h4>Detalhes do problema: </h4>
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


