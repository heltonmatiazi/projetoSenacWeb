<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix ="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Projeto Senac Egressos</title>
        <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/js/script.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    </head>
    <body>
        <header>
            <div class="logo">
                <img src="${pageContext.request.contextPath}/assets/images/finalTop.jpg" class="img-responsive center-block">
                <h3 class="text-center">Home</h3>
            </div>
        </header>
        <section class="main-content">
            <div class="container">
            	<div class="welcome-section">
	                <div class="col-sm-12">
	                    <div class="text-right">
	                        <c:if test="${!empty sessionScope.usuario}"> 
	                            <p>Bem Vindo, <span class="user-name">${sessionScope.usuario.nomeUsuario}</span> <a href="${pageContext.request.contextPath}/egressos/logout" class="calLogout"><i class="fa fa-power-off"></i></a>
	                            </p>
	                            </c:if>
	                    </div>
	                </div>
	            </div>
                <div class="section-control">
                    <div class="col-md-4 col-sm-4 col-xs-12 section-select">
                        <div class="section-title text-center">
                            <h3>Selecione a operação desejada</h3>
                        </div>
                        <div class="staging-buttons">
                            <ul>
                            	<c:if test="${sessionScope.usuario.tipoUsuario eq '2'}">
                                <li><button type="button" class="btn" id="btn_1">Carregar Egressos</button></li>
                                </c:if>
                                <li><button type="button" class="btn" id="btn_2">Pesquisar Egressos</button></li>
                                <li><button type="button" class="btn" id="btn_5">Cadastrar Egressos</button> </li>
                            </ul>
                        </div>
                    </div>
					
					
                    <div class="col-md-8 col-sm-8 col-xs-12 section-staged">
                        <div class="form-invoker">
                        <c:if test="${sessionScope.usuario.tipoUsuario eq '2'}">
                            <div class="form-section" id="form_1">
                                <div class="section-title text-center">
                                    <h3>Carregar planilha de Egressos</h3>
                                </div>
                                <div class="section-content">
                                    <div class="form-master">
                                        <form method="POST" action="${pageContext.request.contextPath}/egressos/upload" enctype="multipart/form-data" >
                                            <label class="form-label">Arquivo:</label>
                                            <input type="file" name="file" id="file" required/> <br/>
                                            <input type="submit" value="Upload" name="upload" id="upload" class="btn-confirm"/>
                                        </form>
                                    </div>
                                </div>
                            </div>
                         </c:if>
                            <div class="form-section" id="form_2">
                                <div class="section-title text-center">
                                    <h3>Pesquisa de Egressos</h3>
                                </div>
                                <div class="section-content">
                                    <div class="form-master">										
                                        <div class="pesquisar-egresso">
                                            <div class="form-group">
                                                <form action="${pageContext.request.contextPath}/egressos/pesquisar?p=1" method="POST">
                                                    <label class="form-label">Nome</label>
                                                    <input type="text" required  name="text"/><input type="submit" value="Pesquisar">
                                                </form>
                                            </div>
                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <form action="${pageContext.request.contextPath}/egressos/pesquisar?p=2" method="POST">
                                                    <label class="form-label">Curso</label>
                                                    <select required name="cursos">
                                                        <option value="1">Análise e Desenvolvimento de Sistemas</option>
                                                        <option value="2">Gestão de Tecnologia da Informação</option>
                                                        <option value="3">Processos Gerenciais</option>                                                                                                               
                                                    </select>
                                                    <input type="submit" value="Pesquisar">
                                                </form>
                                            </div>
                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <form action="${pageContext.request.contextPath}/egressos/pesquisar?p=3" method="POST">
                                                    <label class="form-label">Unidade Senac</label>
                                                    <input type="text" required  name="unidade-senac"><input type="submit" value="Pesquisar">
                                                </form>
                                            </div>
                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <form action="${pageContext.request.contextPath}/egressos/pesquisar?p=4" method="POST" >
                                                    <label class="form-label">Ano de Conclusão</label>
                                                    <input type="text" required  name="ano-conclusao"><input type="submit" value="Pesquisar">
                                                </form>
                                            </div>
                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <form action="${pageContext.request.contextPath}/egressos/pesquisar?p=5" method="POST">
                                                    <label class="form-label">Semestre de Conclusão</label>
                                                    <select required>
                                                        <option value="1"> 1 </option>
                                                        <option value="2"> 2 </option>										       			
                                                    </select>
                                                    <input type="submit" value="Pesquisar">
                                                </form>

                                            </div>
                                            <div class="clearfix"></div>

                                            <div class="form-group">
                                                <form action="${pageContext.request.contextPath}/egressos/pesquisar?p=6" method="POST">
                                                    <label class="form-label">Estado</label>
                                                    <select required name="estado" id="estados"> 

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
                                        <p><span>${requestScope.message}</span></p>
                                        <form method="POST" action="${pageContext.request.contextPath}/egressos/cadastrar">
                                            <div class="informacao-basica">
                                                <div class="section-title text-center">
                                                    <h3>Informações Básicas</h3>
                                                </div>
                                                <div class="form-group">
                                                    <input name="id" type="hidden" value="${perfil.idPerfil}" />
                                                    <label class="form-label">Nome</label>
                                                    <input type="text" required  name="nome" class="field-large" value="${perfil.nome}"/>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="form-label">Sobrenome</label>
                                                    <input type="text" required  name="sobrenome" class="field-large" value="${perfil.sobrenome}"/>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="form-label">Data de Nascimento</label>
                                                    <input type="text" required  id="dateInput" name="dt-nascimento" value="${perfil.dataNascimento}" pattern="\d{1,2}/\d{1,2}/\d{4}"/>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="form-label">Sexo</label>
                                                    <select required name="sexo">
                                                        <option>Selecione seu sexo</option>											       		
                                                        <option value="Masculino">Masculino</option>
                                                        <option value="Feminino">Feminino</option>
                                                    </select>
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="form-label">CPF</label>
                                                    <input type="text" required  name="cpf" value="${perfil.cpf}"  pattern="\d{3}\.\d{3}\.\d{3}-\d{2}">
                                                </div>
                                                <div class="clearfix"></div>
                                            </div>																																												
                                            <div class="formacao-basica">
                                                <div class="section-title text-center">
                                                    <h3>Formação Básica</h3>
                                                </div>
                                                <div class="form-group">
                                                    <label class="form-label">Formação Básica</label>
                                                    <input type="text" required  name="formacao-basica" class="field-large" value="${perfil.formacaoBasica}">
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="form-label">Formação de Ensino Médio</label>
                                                    <input type="text" required  name="form-media" class="field-large" value="${perfil.formacaoEnsinoMedio}">
                                                </div>
                                                <div class="clearfix"></div>
                                            </div>					


                                            <div class="informacoes-curso">
                                                <div class="section-title text-center">
                                                    <h3>Dados do Curso</h3>
                                                </div>
                                                <c:forEach var="curso" items="${perfil.curso}">
                                                <div class="wrapper-cursos">
                                                    <input name="idCursoEgresso" type="hidden" value="${curso.idCursoEgresso}" />
                                                    <div class="cursos-main">
                                                        <div class="form-group">
                                                            <label class="form-label">Curso:</label>
                                                            <select required name="cursos" id="cursos">                                                                
                                                                <option value="1">Análise e Desenvolvimento de Sistemas</option>
                                                                <option value="2">Gestão de Tecnologia da Informação</option>
                                                                <option value="3">Processos Gerenciais</option>                                                        
                                                            </select>
                                                            <div class="clearfix"></div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label class="form-label">Tipo de Título adquirido</label>
                                                            <select required name="titulo">
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
                                                            <input type="text" required  name="unidade-senac" class="field-large" value="${curso.unidadeSenac}">
                                                        </div>
                                                        <div class="clearfix"></div>

                                                        <div class="form-group">
                                                            <label class="form-label">Ano de ingresso:</label>
                                                            <input type="text" required  name="ano-ingresso" value="${curso.anoIngresso}">
                                                        </div>
                                                        <div class="clearfix"></div>

                                                        <div class="form-group">
                                                            <label class="form-label">Semestre de Ingresso</label>
                                                            <input type="text" required  name="semestre-ingresso" value="${curso.semestreIngresso}">
                                                        </div>
                                                        <div class="clearfix"></div>

                                                        <div class="form-group">
                                                            <label class="form-label">Ano de Conclusão</label>
                                                            <input type="text" required  name="ano-conclusao" value="${curso.anoConclusao}">
                                                        </div>
                                                        <div class="clearfix"></div>

                                                        <div class="form-group  form-hack">
                                                            <label class="form-label">Semestre de Conclusão</label>
                                                            <input type="text" required  name="semestre-conclusao" value="${curso.semestreConclusao}">
                                                        </div>
                                                        <div class="clearfix"></div>
                                                    </div>

                                                </div>
                                                </c:forEach>
                                                <button class="add-curso-button btn-confirm">Adicionar novos cursos</button>
                                            </div>


                                            <div class="localizacao">
                                                <div class="section-title text-center">
                                                    <h3>Endereço</h3>
                                                </div>
                                                <input name="idEndereco" type="hidden" value="${perfil.endereco.id}" />
                                                <div class="form-group">
                                                    <label class="form-label">Rua</label>
                                                    <input type="text" required  name="rua" class="field-large" value="${perfil.endereco.rua}">
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="form-label">Número</label>
                                                    <input type="text" required  name="numero" value = "${perfil.endereco.numero}">
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="form-label">Complemento</label>
                                                    <input type="text" required  name="Complemento" class="field-large" value="${perfil.endereco.complemento}">
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="form-label">Cidade</label>
                                                    <input type="text" required  name="cidade" class="field-large" value = "${perfil.endereco.cidade}">
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="form-label">CEP</label>
                                                    <input type="text" required  name="cep" value ="${perfil.endereco.cep }">
                                                </div>
                                                <div class="clearfix"></div>
                                                <div class="form-group">
                                                    <label class="form-label">Estado:</label>
                                                    <select required name="estado" id="estados2"> 

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
                                                    <input type="text" required  name="telefone" value="${perfil.contato.telefone}">
                                                    <div class="clearfix"></div>
                                                    <label class="form-label">Email</label>
                                                    <input type="text" required  pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" name="email" value="${perfil.contato.email}">
                                                </div>
                                                <div class="clearfix"></div>
                                            </div>

                                            <div class="experiencias-profissionais">
                                                <div class="section-title text-center">
                                                    <h3>Experiências profissionais</h3>
                                                </div>				
                                                <c:forEach var="exp" items="${perfil.experiencias}">                                                							
                                                <div class="input-fields-wrap">												
                                                    <input name="idExp" type="hidden" value="${exp.idExperiencia}" />
                                                    <div class="form-group">
                                                        <label class="form-label">Empresa</label>
                                                        <input type="text" required  name="empresa-1" class="field-large" value="${exp.empresa}">

                                                        <div class="clearfix"></div>
                                                        <label class="form-label">Cargo</label>
                                                        <input type="text" required  name="cargo-1" class="field-large" value="${exp.cargo}">
                                                        <div class="clearfix"></div>

                                                        <label class="form-label">Tipo de Trabalho:</label>
                                                        <select required name="tipo-trabalho">
                                                            <option>Selecione a titulação</option>
                                                            <option value="estagio">Estágio</option>
                                                            <option value="contratacao">Contratação</option>
                                                            <option value="CLT">CLT</option>
                                                            <option value="concurso">Concurso</option>
                                                        </select>

                                                        <div class="clearfix"></div>
                                                    </div>
                                                    <div class="form-group">													
                                                        <label class="form-label">Data de Entrada</label>
                                                        <input type="text" required  name="dataEntrada" value="${exp.dataIngresso}" pattern="\d{1,2}/\d{1,2}/\d{4}">
                                                        <div class="clearfix"></div>
                                                        <label class="form-label">Data de Saída</label>
                                                        <input type="text" required  name="dataSaida" value="${exp.dataSaida}" pattern="\d{1,2}/\d{1,2}/\d{4}">													
                                                        <div class="clearfix"></div>
                                                    </div>
                                                </div>
                                                </c:forEach>
                                                <button class="add-field-button btn-confirm">Adicionar novas experiências</button>
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
        <div class="footer-research">
            <div class="container">
                <div class="text-center research-link">
                    <a href="https://docs.google.com/forms/d/e/1FAIpQLScD-_xdGQehztoDhYDsuRxNzXN-TpNflwhP30dyd2AZEAgUpw/viewform" target="_blank" class="text-center research-link">Participe da pesquisa do portal</a>
                </div>
            </div>
        </div>
        <footer>
            <div class="container">
                <div class="copyright">
                    <p>Irreprováveis 2016, no rights reserved.</p>
                </div>
            </div>
        </footer>
    </body>
</html>