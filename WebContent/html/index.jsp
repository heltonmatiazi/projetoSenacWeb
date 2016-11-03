<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core"%>
    <%@taglib prefix ="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Enviando arquivos</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
		<!-- Latest compiled and minified CSS & JS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
		<script src="//code.jquery.com/jquery.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
		<!--  validate - plugin usado para dinamizar a validação dos formulários -->
        <script src="//cdnjs.cloudflare.com/ajax/libs/validate.js/0.10.0/validate.min.js"></script>
		<script src="${pageContext.request.contextPath}/js/script.js"></script>
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
										<form method="POST" action="${pageContext.request.contextPath}/ServletControle?cmd=upload" enctype="multipart/form-data" >
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
									       <div class="pesquisar-egresso">
									       		<div class="form-group">
									       		<form action="${pageContext.request.contextPath}/ServletControle?cmd=pesquisar&param=1" method="post">
									       			<label class="form-label">Nome</label>
										       		<input type="text" name="text"/><input type="submit" value="Pesquisar">
										       		</form>
										       	</div>
										       	<div class="clearfix"></div>

										       	<div class="form-group">
										       	<form action="${pageContext.request.contextPath}/ServletControle?cmd=pesquisar&param=2" method="post">
										       		<label class="form-label">Curso</label>
										       		<input type="text" name="curso"><input type="submit" value="Pesquisar">
										       		</form>
                                                                                                	</div>
										       	<div class="clearfix"></div>

										       	<div class="form-group">
										       	<form action="${pageContext.request.contextPath}/ServletControle?cmd=pesquisar&param=3" method="post">
										       		<label class="form-label">Unidade Senac</label>
										       		<input type="text" name="unidade-senac"><input type="submit" value="Pesquisar">
                                                                                                </form>
										       	</div>
										       	<div class="clearfix"></div>

										       	<div class="form-group">
										       	<form action="${pageContext.request.contextPath}/ServletControle?cmd=pesquisar&param=4" method="post" >
										       		<label class="form-label">Ano de Conclusão</label>
										       		<input type="text" name="ano-conclusao"><input type="submit" value="Pesquisar">
										       		</form>
                                                                                                </div>
										       	<div class="clearfix"></div>

										       	<div class="form-group">
										       	<form action="${pageContext.request.contextPath}/ServletControle?cmd=pesquisar&param=5" method="post">
										       		<label class="form-label">Semestre de Conclusão</label>
										       		<input type="text" name="semestre-conclusao"><input type="submit" value="Pesquisar">
										       		</form>
                                                                                                
										       	</div>
										       	<div class="clearfix"></div>

										       	<div class="form-group">
										       	<form action="${pageContext.request.contextPath}/ServletControle?cmd=pesquisar&param=6" method="post">
										       		<label class="form-label">Estado</label>
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
                                                        <input type="submit" value="Pesquisar">
                                                        </form>
										       	</div>
									        </div>
									        
									    
									</div>
								</div>
							</div>
							
							<div class="form-section" id="form_5">
								<div class="section-title text-center">
									<h3>Cadastrar Egressos</h3>
								</div>
								<div class="section-content">
									<div class="form-master">
										<form method="POST" action="${pageContext.request.contextPath}/ServletControle?cmd=cadastrar">
                                                                                    <div class="informacao-basica">
												<div class="section-title text-center">
													<h3>Informações Básicas</h3>
												</div>
												<div class="form-group">
											
												<input name="id" type="hidden" value="${perfil.idPerfil}" />
					
													<label class="form-label">Nome</label>
													<input type="text" name="nome" class="field-large" value="${perfil.nome}"/>
												</div>
												<div class="clearfix"></div>
												<div class="form-group">
													<label class="form-label">Sobrenome</label>
													<input type="text" name="sobrenome" class="field-large" value="${perfil.sobrenome}"/>
												</div>
												<div class="clearfix"></div>
												<div class="form-group">
													<label class="form-label">Data de Nascimento</label>
													<input type="text" id="dateInput" name="dt-nascimento" value="<fmt:formatDate value='${perfil.dataNascimento}' pattern='dd/MM/yyyy'/>" />
												</div>
												<div class="clearfix"></div>
												<div class="form-group">
													<label class="form-label">Sexo</label>
													<select name="sexo">
											       		<option>Selecione seu sexo</option>											       		
											       		<option value="Masculino">Masculino</option>
											       		<option value="Feminino">Feminino</option>
											       	</select>
												</div>
												<div class="clearfix"></div>
												<div class="form-group">
													<label class="form-label">CPF</label>
													<input type="text" name="cpf" value="${perfil.cpf}">
												</div>
												<div class="clearfix"></div>
											</div>																																												
											<div class="formacao-basica">
												<div class="section-title text-center">
													<h3>Formação Básica</h3>
												</div>
												<div class="form-group">
													<label class="form-label">Formação Básica</label>
													<input type="text" name="formacao-basica" class="field-large" value="${perfil.formacaoBasica}">
												</div>
												<div class="clearfix"></div>
												<div class="form-group">
													<label class="form-label">Formação de Ensino Médio</label>
													<input type="text" name="form-media" class="field-large" value="${perfil.formacaoEnsinoMedio}">
												</div>
												<div class="clearfix"></div>
											</div>					
											
														  	
									        <div class="informacoes-curso">
									        	<div class="section-title text-center">
									        		<h3>Dados do Curso</h3>
									        	</div>
									        	<div class="wrapper-cursos">
									        	<c:forEach var="curso" items="${perfil.curso}">	
									        	<input name="idCurso" type="hidden" value="${curso.idCursoEgresso}" />
									        	<div class="cursos-main">
									        		<div class="form-group">
										        	<label class="form-label">Curso:</label>
											        <input type="text" name="curso" class="field-large" value="${curso.nomeCurso}">
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
										       		<input type="text" name="unidade-senac" class="field-large" value="${curso.unidadeSenac}">
										       	</div>
										       	<div class="clearfix"></div>

										       	<div class="form-group">
										       		<label class="form-label">Ano de ingresso:</label>
										       		<input type="text" name="ano-ingresso" value="${curso.anoIngresso}">
										       	</div>
										       	<div class="clearfix"></div>

										       	<div class="form-group">
										       		<label class="form-label">Semestre de Ingresso</label>
										       		<input type="text" name="semestre-ingresso" value="${curso.semestreIngresso}">
										       	</div>
										       	<div class="clearfix"></div>

										       	<div class="form-group">
										       		<label class="form-label">Ano de Conclusão</label>
										       		<input type="text" name="ano-conclusao" value="${curso.anoConclusao}">
										       	</div>
										       	<div class="clearfix"></div>

										       	<div class="form-group  form-hack">
										       		<label class="form-label">Semestre de Conclusão</label>
										       		<input type="text" name="semestre-conclusao" value="${curso.semestreConclusao}">
										       	</div>
										       	<div class="clearfix"></div>
									        	</div>
									        	</c:forEach>
									        </div>
									       	<button class="add-curso-button btn-confirm">Adicionar novos cursos</button>
									       </div>
											
											
									        <div class="localizacao">
									        	<div class="section-title text-center">
									        		<h3>Endereço</h3>
									        	</div>
									        	<input name="idEndereco" type="hidden" value="${perfil.endereco.id}" />
									        	<div class="form-group">
									        		<label class="form-label">Rua</label>
									        		<input type="text" name="rua" class="field-large" value="${perfil.endereco.rua}">
									        	</div>
									        	<div class="clearfix"></div>
									        	<div class="form-group">
									        		<label class="form-label">Número</label>
									        		<input type="text" name="numero" value = "${perfil.endereco.numero}">
									        	</div>
									        	<div class="clearfix"></div>
									        	<div class="form-group">
									        		<label class="form-label">Complemento</label>
									        		<input type="text" name="Complemento" class="field-large" value="${perfil.endereco.complemento}">
									        	</div>
									        	<div class="clearfix"></div>
									        	<div class="form-group">
									        		<label class="form-label">Cidade</label>
									        		<input type="text" name="cidade" class="field-large" value = "${perfil.endereco.cidade}">
									        	</div>
									        	<div class="clearfix"></div>
									        	<div class="form-group">
									        		<label class="form-label">CEP</label>
									        		<input type="text" name="cep" value ="${perfil.endereco.cep }">
									        	</div>
									        	<div class="clearfix"></div>
									        	<div class="form-group">
									        	<label class="form-label">Estado:</label>
										        <select name="estado"> 
													<option value="estado">Selecione o Estado</option> 												
													<option value="1">Acre</option> 
													<option value="2">Alagoas</option> 
													<option value="3">Amazonas</option> 
													<option value="4">Amapá</option> 
													<option value="5">Bahia</option> 
													<option value="6">Ceará</option> 
													<option value="7">Distrito Federal</option> 
													<option value="8">Espírito Santo</option> 
													<option value="9">Goiás</option> 
													<option value="10">Maranhão</option> 
													<option value="11">Mato Grosso</option> 
													<option value="12">Mato Grosso do Sul</option> 
													<option value="13">Minas Gerais</option> 
													<option value="14">Pará</option> 
													<option value="15">Paraíba</option> 
													<option value="16">Paraná</option> 
													<option value="17">Pernambuco</option> 
													<option value="18">Piauí</option> 
													<option value="19">Rio de Janeiro</option> 
													<option value="20">Rio Grande do Norte</option> 
													<option value="21">Rondônia</option> 
													<option value="22">Rio Grande do Sul</option> 
													<option value="23">Roraima</option> 
													<option value="24">Santa Catarina</option> 
													<option value="25">Sergipe</option> 
													<option value="26">São Paulo</option> 
													<option value="27">Tocantins</option> 
												</select>
												<div class="clearfix"></div>
									        </div>
									        </div>

									        <div class="informacoes-contato">
									        	<div class="section-title text-center">
									        		<h3>Informações de Contato</h3>
									        	</div>
									        	<input name="idContato" type="hidden" value="${perfil.contato.idContato}" />
									        	<div class="form-group">
									        		<label class="form-label">Telefone</label>
									        		<input type="text" name="telefone" value="${perfil.contato.telefone}">
									        		<div class="clearfix"></div>
									        		<label class="form-label">Email</label>
									        		<input type="text" name="email" value="${perfil.contato.email}">
									        	</div>
									        	<div class="clearfix"></div>
									        </div>
											<div class="experiencias-profissionais">
												<div class="section-title text-center">
													 <h3>Experiências profissionais</h3>
												</div>
											<!-- geração de divs de experiencia profissional -->
												<div class="input-fields-wrap">
												<c:forEach var="exp" items = "${perfil.experiencias}">
												<input name="idExp" type="hidden" value="${exp.idExperiencia}" />
												    <div class="form-group">
														<label class="form-label">Empresa</label>
														<input type="text" name="empresa-1" class="field-large" value="${exp.empresa}">
														
														<div class="clearfix"></div>
														<label class="form-label">Cargo</label>
														<input type="text" name="cargo-1" class="field-large" value="${exp.cargo}">
														<div class="clearfix"></div>

														<label class="form-label">Tipo de Trabalho:</label>
														<select name="tipo-trabalho">
															<option>Selecione a titulação</option>
															<option value="estagio">Estágio</option>
															<option value="contratacao">Contratação</option>
															<option value="CLT">CLT</option>
															<option value="concurso">Concurso</option>
														</select>
														
														<div class="clearfix"></div>
													</div>
													<label class="form-label">Data de Entrada</label>
													<input type="text" name="dataEntrada" value="<fmt:formatDate value='${exp.dataIngresso}' pattern='dd/MM/yyyy'/>">
													<div class="clearfix"></div>
													<label class="form-label">Data de Saída</label>
													<input type="text" name="dataSaida" value="<fmt:formatDate value='${exp.dataSaida}' pattern='dd/MM/yyyy'/>">
													<div class="clearfix"></div>
													</c:forEach>
												</div>
												<button class="add-field-button btn-confirm">Inserir Novas Experiências</button>
											</div>

											<div class="clearfix"></div>
											
											<input type="submit" value="Adicionar" name="adicionar" id="Adicionar" class="btn-confirm"/>
											<input type="reset" name="Apagar" value="Apagar" id="Apagar" class="btn-erase">
											<button class="casoTeste">Gerar caso de teste</button>
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