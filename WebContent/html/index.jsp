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
								<li><button type="button" class="btn btn-default" id="btn_1">Carregar Egressos</button></li>
								<li><button type="button" class="btn btn-primary" id="btn_2">Pesquisar Egressos</button></li>
								<li><button type="button" class="btn btn-green" id="btn_5">Cadastrar Egressos</button> </li>
							</ul>
						</div>
					</div>

					<div class="col-md-8 col-sm-8 col-xs-12 section-staged">
						<div class="form-invoker">
							<div class="form-section" id="form_1">
								<div class="section-title text-center">
									<h3>Carregar planilha de Egressos</h3>
								</div>
								<div class="section-content">
									<div class="form-master">
										<form method="POST" action="ServletControle?cmd=salvar" enctype="multipart/form-data" >
									        <label class="form-label">Arquivo:</label>
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
										<form method="POST" action="ServletControle?cmd=pesquisar">
									       <div class="pesquisar-egresso">
									       		<div class="form-group">
									       			<label class="form-label">Nome</label>
										       		<input type="text" name="text"/> 
										       	</div>
										       	<div class="clearfix"></div>

										       	<div class="form-group">
										       		<label class="form-label">Curso</label>
										       		<input type="text" name="curso">
										       	</div>
										       	<div class="clearfix"></div>

										       	<div class="form-group">
										       		<label class="form-label">Unidade Senac</label>
										       		<input type="text" name="unidade-senac">
										       	</div>
										       	<div class="clearfix"></div>

										       	<div class="form-group">
										       		<label class="form-label">Ano de Conclusão</label>
										       		<input type="text" name="ano-conclusao">
										       	</div>
										       	<div class="clearfix"></div>

										       	<div class="form-group">
										       		<label class="form-label">Semestre de Conclusão</label>
										       		<input type="text" name="semestre-conclusao">
										       	</div>
										       	<div class="clearfix"></div>

										       	<div class="form-group">
										       		<label class="form-label">estado</label>
										       		<select name="estado"> 
													<option value="estado">Selecione o Estado</option> 
													<option value="ac">Acre</option> 
													<option value="al">Alagoas</option> 
													<option value="am">Amazonas</option> 
													<option value="ap">Amapá</option> 
													<option value="ba">Bahia</option> 
													<option value="ce">Ceará</option> 
													<option value="df">Distrito Federal</option> 
													<option value="es">Espírito Santo</option> 
													<option value="go">Goiás</option> 
													<option value="ma">Maranhão</option> 
													<option value="mt">Mato Grosso</option> 
													<option value="ms">Mato Grosso do Sul</option> 
													<option value="mg">Minas Gerais</option> 
													<option value="pa">Pará</option> 
													<option value="pb">Paraíba</option> 
													<option value="pr">Paraná</option> 
													<option value="pe">Pernambuco</option> 
													<option value="pi">Piauí</option> 
													<option value="rj">Rio de Janeiro</option> 
													<option value="rn">Rio Grande do Norte</option> 
													<option value="ro">Rondônia</option> 
													<option value="rs">Rio Grande do Sul</option> 
													<option value="rr">Roraima</option> 
													<option value="sc">Santa Catarina</option> 
													<option value="se">Sergipe</option> 
													<option value="sp">São Paulo</option> 
													<option value="to">Tocantins</option> 
												</select>
										       	</div>
									        <input type="submit" value="pesquisa" name="upload" id="pesquisa" class="btn-confirm"/>	
									       </div>
									        
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
										<form method="POST" action="ServletControle?cmd=Alterar">
									       
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
										<form method="POST" action="ServletControle?cmd=Excluir">
									       
									        <input type="text" name="text" id="Excluir-parametro" /> <br/>
									        <input type="submit" value="Excluir" name="upload" id="Excluir" class="btn-confirm"/>
									    </form>
									</div>
								</div>
							</div>

							<div class="form-section" id="form_5">
								<div class="section-title text-center">
									<h3>Cadastrar Egressos</h3>
								</div>
								<div class="section-content">
									<div class="form-master">
										<form method="POST" action="ServletControle?cmd=Cadastrar">
											<div class="formacao-basica">
												<div class="section-title text-center">
													<h3>Formação Básica</h3>
												</div>
												<div class="form-group">
													<label class="form-label">Formação Básica</label>
													<input type="" name="formacao-basica" class="field-large">
												</div>
												<div class="clearfix"></div>
												<div class="form-group">
													<label class="form-label">Formação de Ensino Médio</label>
													<input type="" name="form-media" class="field-large">
												</div>
												<div class="clearfix"></div>
											</div>									  		
									        <div class="informacoes-curso">
									        	<div class="section-title text-center">
									        		<h3>Dados do Curso</h3>
									        	</div>
									        	<div class="form-group">
										        	<label class="form-label">Curso:</label>
											        <input type="text" name="curso" class="field-large">
											   		<div class="clearfix"></div>
										        </div>
										       	
										       	<div class="form-group">
										       		<label class="form-label">Tipo de Título adquirido</label>
											       	<select name="titulo">
											       		<option>Selecione a titulação</option>
											       		<option value="tecnologo">Tecnólogo</option>
											       		<option value="licenciatura">Licenciatura</option>
											       		<option value="bacharelado">Bacharelado</option>
											       		<option value="mestrado">Mestrado</option>
											       		<option value="doutorado">Doutorado</option>
											       		<option value="pos-doutorado">Pós-doutorado</option>
											       	</select>
											        <div class="clearfix"></div>
										       	</div>

										       	<div class="form-group">
										       		<label class="form-label">Unidade do senac</label>
										       		<input type="text" name="unidade-senac" class="field-large">
										       	</div>
										       	<div class="clearfix"></div>

										       	<div class="form-group">
										       		<label class="form-label">Ano de ingresso:</label>
										       		<input type="text" name="ano-ingresso">
										       	</div>
										       	<div class="clearfix"></div>

										       	<div class="form-group">
										       		<label class="form-label">Semestre de Ingresso</label>
										       		<input type="text" name="semestre-ingresso">
										       	</div>
										       	<div class="clearfix"></div>

										       	<div class="form-group">
										       		<label class="form-label">Ano de Conclusão</label>
										       		<input type="text" name="ano-conclusao">
										       	</div>
										       	<div class="clearfix"></div>

										       	<div class="form-group">
										       		<label class="form-label">Semestre de Conclusão</label>
										       		<input type="text" name="semestre-conclusao">
										       	</div>
										       	<div class="clearfix"></div>
									        </div>

									        <div class="localizacao">
									        	<div class="section-title text-center">
									        		<h3>Informações de Localização</h3>
									        	</div>
									        	<div class="form-group">
									        		<label class="form-label">Rua</label>
									        		<input type="text" name="rua" class="field-large">
									        	</div>
									        	<div class="clearfix"></div>
									        	<div class="form-group">
									        		<label class="form-label">Número</label>
									        		<input type="text" name="numero">
									        	</div>
									        	<div class="clearfix"></div>
									        	<div class="form-group">
									        		<label class="form-label">Complemento</label>
									        		<input type="text" name="Complemento" class="field-large">
									        	</div>
									        	<div class="clearfix"></div>
									        	<div class="form-group">
									        		<label class="form-label">cidade</label>
									        		<input type="text" name="cidade" class="field-large">
									        	</div>
									        	<div class="clearfix"></div>
									        	<div class="form-group">
									        		<label class="form-label">cep</label>
									        		<input type="text" name="cep">
									        	</div>
									        	<div class="clearfix"></div>
									        	<div class="form-group">
									        	<label class="form-label">Estado:</label>
										        <select name="estado"> 
													<option value="estado">Selecione o Estado</option> 
													<option value="ac">Acre</option> 
													<option value="al">Alagoas</option> 
													<option value="am">Amazonas</option> 
													<option value="ap">Amapá</option> 
													<option value="ba">Bahia</option> 
													<option value="ce">Ceará</option> 
													<option value="df">Distrito Federal</option> 
													<option value="es">Espírito Santo</option> 
													<option value="go">Goiás</option> 
													<option value="ma">Maranhão</option> 
													<option value="mt">Mato Grosso</option> 
													<option value="ms">Mato Grosso do Sul</option> 
													<option value="mg">Minas Gerais</option> 
													<option value="pa">Pará</option> 
													<option value="pb">Paraíba</option> 
													<option value="pr">Paraná</option> 
													<option value="pe">Pernambuco</option> 
													<option value="pi">Piauí</option> 
													<option value="rj">Rio de Janeiro</option> 
													<option value="rn">Rio Grande do Norte</option> 
													<option value="ro">Rondônia</option> 
													<option value="rs">Rio Grande do Sul</option> 
													<option value="rr">Roraima</option> 
													<option value="sc">Santa Catarina</option> 
													<option value="se">Sergipe</option> 
													<option value="sp">São Paulo</option> 
													<option value="to">Tocantins</option> 
												</select>
												<div class="clearfix"></div>
									        </div>
									        </div>

											<div class="experiencias-profissionais">
												<div class="section-title text-center">
													 <h3>Experiências profissionais</h3>
												</div>
												<div class="form-group">
													<label class="form-label">Empresa</label>
													<input type="text" name="empresa-1" class="field-large">
													
													<div class="clearfix"></div>
													<label class="form-label">Cargo</label>
													<input type="text" name="cargo-1" class="field-large">
													<div class="clearfix"></div>

													<label class="form-label">Tipo de Trabalho:</label>
													<select>
														<option>Selecione a titulação</option>
														<option value="estagio">Estágio</option>
														<option value="contratacao">Contratação</option>
														<option value="CLT">clt</option>
														<option value="concurso">Concurso</option>
													</select>
													
													<div class="clearfix"></div>
												</div>

												<div class="form-group">
													<label class="form-label">Empresa:</label>
													<input type="text" name="empresa-2" class="field-large">
													<div class="clearfix"></div>
													

													<label class="form-label">Cargo</label>
													<input type="text" name="cargo-2" class="field-large">
													<div class="clearfix"></div>

													<label class="form-label">Tipo de Trabalho:</label>
													<select>
														<option>Selecione o tipo de trabalho</option>
														<option value="estagio">Estágio</option>
														<option value="contratacao">Contratação</option>
														<option value="CLT">clt</option>
														<option value="concurso">Concurso</option>
													</select>
													<div class="clearfix"></div>
												</div>
												<div class="form-group">
													<label class="form-label">Empresa:</label>
													<input type="text" name="empresa-3" class="field-large">
													<div class="clearfix"></div>


													<label class="form-label">Cargo</label>
													<input type="text" name="cargo-3" class="field-large">
													<div class="clearfix"></div>
													
													<label class="form-label">Tipo de Trabalho:</label>
													<select>
														<option>Selecione o tipo de trabalho</option>
														<option value="estagio">Estágio</option>
														<option value="contratacao">Contratação</option>
														<option value="CLT">clt</option>
														<option value="concurso">Concurso</option>
													</select>
													<div class="clearfix"></div>
												</div>
		
											</div>

											<div class="clearfix"></div>
											
											<input type="submit" value="Adicionar" name="adicionar" id="Adicionar" class="btn-confirm"/>
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