<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Enviando arquivos</title>
		<link rel="stylesheet" href="../css/style.css">
		<!-- Latest compiled and minified CSS & JS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
		<script src="//code.jquery.com/jquery.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
		<script src="../js/script.js"></script>
	</head>
	<body>
		<header>
			<div class="container">
				<div class="section-title">
					<h3>Controle Administrativo</h3>
				</div>
				<div class="section-content section-welcome">
					<p class="pull-right">Bem vindo, <span>Administrador</span></p>
				</div>
			</div>
		</header>
		<section class="main-content">
			<div class="container">
				<div class="section-control">
					<div class="col-md-4 col-sm-4 col-xs-12 section-select">
						<div class="section-title text-center">
							<h3>Selecione a operação desejada</h3>
						</div>
						<div class="staging-buttons">
							<ul>
								<li><button type="button" class="btn btn-default" id="btn_1">Cadastrar Egressos</button></li>
								<li><button type="button" class="btn btn-primary" id="btn_2">Pesquisar Egressos</button></li>
								<li><button type="button" class="btn btn-info" id="btn_3">Alterar Egressos</button></li>
								<li><button type="button" class="btn btn-danger" id="btn_4">Excluir Egressos</button></li>
							</ul>
						</div>
					</div>
					<div class="col-md-8 col-sm-8 col-xs-12 section-staged">
						<div class="form-invoker">
							<div class="form-section" id="form_1">
								<div class="section-title text-center">
									<h3>Cadastro de Egressos</h3>
								</div>
								<div class="section-content">
									<div class="form-master">
										<form method="POST" action="ServletControle?cmd=salvar" enctype="multipart/form-data" >
									        <label class="label-name">Arquivo:</label>
									        <input type="file" name="file" id="file" /> <br/>
									        <input type="submit" value="Upload" name="upload" id="upload" class="btn-confirm"/>
									    </form>
									</div>
								</div>
								
							</div>
							<div class="form-section" id="form_2">
								<div class="section-title text-center">
									<h3>Pesquisa de Egressos</h3>
								</div>
								<div class="section-content">
									<div class="form-master">
										<form method="POST" action="ServletControle?cmd=pesquisar" enctype="multipart/form-data" >
									       
									        <input type="text" name="text" id="pesquisa-parametro" /> <br/>
									        <input type="submit" value="pesquisa" name="upload" id="pesquisa" class="btn-confirm"/>
									    </form>
									</div>
								</div>
							</div>
							<div class="form-section" id="form_3">
								<div class="section-title text-center">
									<h3>Alteração de dados de Egressos</h3>
								</div>
								<div class="section-content">
									<div class="form-master">
										<form method="POST" action="ServletControle?cmd=Alterar" enctype="multipart/form-data" >
									       
									        <input type="text" name="text" id="alterar-parametro" /> <br/>
									        <input type="submit" value="alterar" name="upload" id="alterar" class="btn-confirm"/>
									    </form>
									</div>
								</div>
							</div>
							<div class="form-section" id="form_4">
								<div class="section-title text-center">
									<h3>Exclusão de Egressos</h3>
								</div>
								<div class="section-content">
									<div class="form-master">
										<form method="POST" action="ServletControle?cmd=Excluir" enctype="multipart/form-data" >
									       
									        <input type="text" name="text" id="Excluir-parametro" /> <br/>
									        <input type="submit" value="Excluir" name="upload" id="Excluir" class="btn-confirm"/>
									    </form>
									</div>
								</div>
							</div>
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