
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
    email varchar(50),
    telefone varchar(20)
);

create table usuario(   
    login varchar(30) PRIMARY KEY NOT NULL UNIQUE,
    senha varchar(20), 
    tipoUsuario int
);

create table tipoTrabalho(
    idTipoTrabalho int auto_increment PRIMARY KEY,
    tipo enum('estágio', 'contratação', 'CLT', 'concurso')
);

create table titulo(
    idTitulo int auto_increment PRIMARY KEY,
    tipo enum('tecnólogo', 'licenciatura', 'bacharelado', 'mestrado', 'doutorado', 'pós-doutorado')
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

create table cursoEgresso(
    idCursoEgresso int auto_increment PRIMARY KEY,
    idPerfil int,
    nomeCurso varchar(100),
    unidadeSenac varchar(100),
    anoIngresso int,
    semestreIngresso int,
    anoConclusao int,
    semestreConclusao int,
    idTitulo int,
    CONSTRAINT fk_cursoEgresso_titulo FOREIGN KEY (idTitulo) REFERENCES titulo(idTitulo),		
    CONSTRAINT fk_cursoEgresso_perfil FOREIGN KEY (idPerfil) REFERENCES perfil(idPerfil)
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

DELIMITER $$
CREATE TRIGGER GERA_USUARIO AFTER INSERT ON PERFIL FOR EACH ROW
BEGIN
	INSERT INTO USUARIO(LOGIN, SENHA, TIPOUSUARIO) VALUES (NEW.CPF, NEW.NOME, 1);	
END $$
DELIMITER ;

INSERT INTO ESTADO(NOMEESTADO, SIGLAESTADO) VALUES('ACRE','AC'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('ALAGOAS','AL'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('AMAPA','AP'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('AMAZONAS','AM');
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('BAHIA','BA');
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('CEARA','CE'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('DISTRITO FEDERAL','DF'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('ESPIRITO SANTO','ES');
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('GOIAS','GO');
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('MARANHAO','MA');
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('MATO GROSSO','MT');
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('MATO GROSSO DO SUL','MS');
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('MINAS GERAIS','MG');
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('PARA','PA'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('PARAIBA','PB'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('PARANA','PR'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('PERNAMBUCO','PE'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('PIAUI','PI'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('RIO DE JANEIRO','RJ'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('RIO GRANDE DO NORTE','RN'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('RIO GRANDE DO SUL','RS'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('RONDONIA','RO'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('RORAIMA','RR'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('SANTA CATARINA','SC'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('SAO PAULO','SP'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('SERGIPE','SE'); 
INSERT INTO ESTADO(nomeestado, siglaestado) VALUES('TOCANTINS','TO'); 

INSERT INTO TITULO(TIPO) VALUES ('TECNOLOGO');
INSERT INTO TITULO(TIPO) VALUES ('LICENCIATURA');
INSERT INTO TITULO(TIPO) VALUES ('BACHARELADO');
INSERT INTO TITULO(TIPO) VALUES ('MESTRADO');
INSERT INTO TITULO(TIPO) VALUES ('DOUTORADO');
INSERT INTO TITULO(TIPO) VALUES ('PÓS-DOUTORADO');

INSERT INTO TIPOTRABALHO(TIPO) VALUES('ESTÁGIO');
INSERT INTO TIPOTRABALHO(TIPO) VALUES('CONTRATAÇÃO');
INSERT INTO TIPOTRABALHO(TIPO) VALUES('CLT');
INSERT INTO TIPOTRABALHO(TIPO) VALUES('CONCURSO');
 