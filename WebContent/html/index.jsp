<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Enviando arquivos</title>
		<link rel="stylesheet" href="../css/style.css">
	</head>
	<body>
		<header>
			<div class="container">
				<div class="section-title">
					<h3>Envie seu arquivo</h3>
				</div>
			</div>
		</header>
		<section class="main-content">
			<div class="container">
				<div class="form-master">
					<form method="POST" action="/UploadFile" enctype="multipart/form-data" >
				        <label class="label-name">Arquivo:</label>
				        <input type="file" name="file" id="file" /> <br/>
				        <input type="submit" value="Upload" name="upload" id="upload" />
				    </form>
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