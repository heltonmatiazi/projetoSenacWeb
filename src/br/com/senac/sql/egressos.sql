
drop database if exists egressos;
create database egressos;
use egressos;

create table estado(
    idEstado int auto_increment PRIMARY KEY NOT NULL,    
    nomeEstado varchar(20),
    siglaEstado char(2)
);

create table endereco(
    idEndereco int auto_increment PRIMARY KEY NOT NULL,    
    idEstado int,
    rua varchar(100),
    numero int,
    complemento varchar(100),
    cidade varchar(30),
    cep varchar(25),
    CONSTRAINT fk_Estado_Endereco FOREIGN KEY (idEstado) REFERENCES estado(idEstado)
);

create table contato(
    idContato int auto_increment PRIMARY KEY not null,
    email varchar(150),
    telefone varchar(50)
);

create table usuario(   
    login varchar(30) PRIMARY KEY NOT NULL UNIQUE,
    senha char (60) binary, 
    tipoUsuario int,
    nomeUsuario varchar(200)
);

create table tipoTrabalho(
    idTipoTrabalho int auto_increment PRIMARY KEY,
    tipoTrabalho varchar(150)
);

create table titulo(
    idTitulo int auto_increment PRIMARY KEY,
    tipoTitulo varchar(100)
);


create table perfil(
    idPerfil int auto_increment PRIMARY KEY,    
    idEndereco int,
    idContato int,
    nome varchar(30),
    sobrenome varchar(40),
    dataNascimento date,
    sexo enum('masculino', 'feminino'),
    cpf char(14),    
    formacaoBasica varchar(100),
    formacaoEnsinoMedio varchar(100),
    FOREIGN KEY (idendereco) REFERENCES endereco(idendereco) ON DELETE CASCADE ON UPDATE CASCADE,    
    FOREIGN KEY(idcontato) REFERENCES contato(idcontato) ON DELETE CASCADE ON UPDATE CASCADE    
);

create table curso (
    idCurso int not null auto_increment primary key,
    nomeCurso varchar(250)
);

create table cursoEgresso(
    idCursoEgresso int auto_increment PRIMARY KEY,
    idPerfil int,
    idCurso int,
    unidadeSenac varchar(100),
    anoIngresso int,
    semestreIngresso int,
    anoConclusao int,
    semestreConclusao int,
    idTitulo int,
    CONSTRAINT fk_cursoEgresso_titulo FOREIGN KEY (idTitulo) REFERENCES titulo(idTitulo),		
    CONSTRAINT fk_cursoEgresso_perfil FOREIGN KEY (idPerfil) REFERENCES perfil(idPerfil),
    CONSTRAINT fk_cursoEgresso_curso FOREIGN KEY (idCurso) REFERENCES curso(idCurso)		    
);

create table experiencias(
    idExperiencias int auto_increment PRIMARY KEY,
    idPerfil int,
    empresa varchar(100),
    cargo varchar(100),
    dataIngresso date,
    dataSaida date,
    idTipoTrabalho int,
    CONSTRAINT fk_experiencia_tipo FOREIGN KEY (idTipoTrabalho) REFERENCES tipoTrabalho(idTipoTrabalho),
    CONSTRAINT fk_experiencia_perfil FOREIGN KEY (idPerfil) REFERENCES perfil(idPerfil)
);

/*
DELIMITER $$
CREATE TRIGGER GERA_USUARIO AFTER INSERT ON PERFIL FOR EACH ROW
BEGIN
	INSERT INTO USUARIO(LOGIN, SENHA, TIPOUSUARIO) VALUES (NEW.CPF, NEW.NOME, 1);	
END $$
DELIMITER ;
*/

INSERT INTO ESTADO(NOMEESTADO, SIGLAESTADO) VALUES('Acre','AC'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Alagoas','AL'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Amapa','AP'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Amazonas','AM');
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Bahia','BA');
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Ceara','CE'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Distrito Federal','DF'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Espirito Santo','ES');
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Goias','GO');
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Maranhao','MA');
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Mato Grosso','MT');
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Mato Grosso do Sul','MS');
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Minas Gerais','MG');
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Para','PA'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Paraiba','PB'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Parana','PR'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Pernambuco','PE'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Piaui','PI'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Rio de Janeiro','RJ'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Rio Grande do Norte','RN'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Rio Grande do Sul','RS'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Rondonia','RO'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Roraima','RR'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Santa Catarina','SC'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Sao Paulo','SP'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Sergipe','SE'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('Tocantins','TO'); 

INSERT INTO TITULO(TIPOTITULO) VALUES ('Tecnologo');
INSERT INTO TITULO(TIPOTITULO) VALUES ('Licenciatura');
INSERT INTO TITULO(TIPOTITULO) VALUES ('Bacharelado');
INSERT INTO TITULO(TIPOTITULO) VALUES ('Mestrado');
INSERT INTO TITULO(TIPOTITULO) VALUES ('Doutorado');
INSERT INTO TITULO(TIPOTITULO) VALUES ('Pos-Doutorado');

INSERT INTO TIPOTRABALHO(TIPOTRABALHO) VALUES('Estagio');
INSERT INTO TIPOTRABALHO(TIPOTRABALHO) VALUES('Contratacao');
INSERT INTO TIPOTRABALHO(TIPOTRABALHO) VALUES('CLT');
INSERT INTO TIPOTRABALHO(TIPOTRABALHO) VALUES('Concurso');

insert into curso(nomecurso) values('Análise e Desenvolvimento de Sistemas');
insert into curso(nomecurso) values('Gestão da Tecnologia da Informação');
insert into curso(nomecurso) values('Processos Gerenciais');  
 
insert into endereco(idEstado,rua,numero,complemento,cidade,cep)values(1,'rua de teste',3,'complemento de teste','São josé',88222333);

insert into contato(email,telefone) values('teste@teste.com','4833445566');


insert into perfil(idEndereco,idContato,nome,sobrenome,dataNascimento,sexo,cpf,formacaoBasica,formacaoEnsinoMedio)
values(1,1,'nome de teste','sobrenome de teste','2016-01-01','masculino','02333322212','formaçao basica teste', 'formação media teste');

insert into cursoEgresso(idPerfil,idCurso,unidadeSenac,anoIngresso,
		semestreIngresso,anoConclusao,semestreConclusao,idTitulo)
			values(1, 3, 'senac de outro teste', 2015,1,2016,2,1);


insert into experiencias(idPerfil,empresa,cargo,dataIngresso,dataSaida,idTipoTrabalho)
				values(1,'empresa de teste','CEO','2016-01-01','2016-01-02',1);



select * from perfil;
