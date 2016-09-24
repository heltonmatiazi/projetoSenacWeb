
drop database if exists projeto_egressos;
create database projeto_egressos;
use projeto_egressos;

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
    cep int,
    CONSTRAINT fk_Estado_Endereco FOREIGN KEY (idEstado) REFERENCES estado(idEstado)
);

create table pessoa(
    idPessoa int auto_increment PRIMARY KEY NOT NULL,
    idEndereco int,
    nome varchar(30),
    sobrenome varchar(40),
    dataNascimento date,
    sexo enum('masculino', 'feminino'),
    cpf char(14),
    CONSTRAINT fk_pessoa_endereco FOREIGN KEY (idendereco) REFERENCES endereco(idendereco)
);


create table usuario(
    idUsuario int auto_increment PRIMARY KEY,
    login varchar(30),
    senha varchar(20), 
    tipoUsuario int
);

create table tipoTrabalho(
    idTipoTrabalho int auto_increment PRIMARY KEY,
    codigo int,
    tipo enum('estágio', 'contratação', 'CLT', 'concurso')
);

create table titulo(
    idTitulo int auto_increment PRIMARY KEY,
    codigo int,
    tipo enum('tecnólogo', 'licenciatura', 'bacharelado', 'mestrado', 'doutorado', 'pós-doutorado')
);

create table cursoEgresso(
    idCursoEgresso int auto_increment PRIMARY KEY,
    nome varchar(100),
    unidadeSenac varchar(100),
    anoIngresso int,
    semestreIngresso int,
    anoConclusao int,
    semestreConclusao int,
    idTitulo int,
    CONSTRAINT fk_cursoEgresso_titulo FOREIGN KEY (idTitulo) REFERENCES titulo(idTitulo)    
);
create table experiencias(
    idExperiencias int auto_increment PRIMARY KEY,
    empresa varchar(100),
    cargo varchar(100),
    dataIngresso date,
    dataSaida date,
    idTipoTrabalho int,
    CONSTRAINT fk_experiencia_tipo FOREIGN KEY (idTipoTrabalho) REFERENCES tipoTrabalho(idTipoTrabalho)
    
);
create table perfil(
    idPerfil int auto_increment PRIMARY KEY,
    formacaoBasica varchar(100),
    formacaoEnsinoMedio varchar(100),
    idCursoEgresso int,
    idExperiencias int,
    CONSTRAINT fk_perfil_cursoEgresso FOREIGN KEY (idCursoEgresso) REFERENCES cursoEgresso(idCursoEgresso),
    CONSTRAINT fk_perfil_experienciasProfissionais FOREIGN KEY (idExperiencias) REFERENCES experiencias(idExperiencias)
); 


