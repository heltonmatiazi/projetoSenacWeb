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
            <div class="logo">
                <img src="${pageContext.request.contextPath}/assets/images/finalTop.jpg" class="img-responsive center-block">
                <h3 class="text-center">Pesquisa</h3>
            </div>
        </header>
        <section class="main-content">
            <div class="container">
                <div class="response-manager">
                    <p>${request.message}</p>
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
                                        <td><a href="${pageContext.request.contextPath}/egressos/carregar?id=${resultado.idPerfil}"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a></td>
                                        <td><a href="${pageContext.request.contextPath}/egressos/excluir?id=${resultado.idPerfil}"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a></td>
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
