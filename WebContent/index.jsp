<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix ="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/js/script.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <header>
            <div class="logo">
                <img src="${pageContext.request.contextPath}/assets/images/finalTop.jpg" class="img-responsive center-block">
                <h3 class="text-center">Login</h3>
            </div>
        </header>
        <section class="main-content">
            <div class="container">
                <div class="login-control">
                    <div class="col-md-offset-3 col-md-6">
                        <div class="login-form jumbotron">                            
                            <form action="${pageContext.request.contextPath}/egressos/login" method="POST">
                                <div class="form-group">

                                    <label class="form-label">Login</label>
                                    <input type="text" name="login">

                                    <label class="form-label">Senha</label>
                                    <input type="password" name="senha" >
                                    <div class="clearfix"></div>			
                                    <input type="submit" value="Entrar" class="submitLogin">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </section>
        <div class="clearfix"></div>
        <footer>
            <div class="container">
                <div class="copyright">
                    <p>Irreprováveis 2016, no rights reserved.</p>
                </div>
            </div>
        </footer>
    </body>
</html>