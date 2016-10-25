package br.com.senac.model;

import java.util.Date;
import java.util.List;

public class Perfil {
	private Integer idPerfil;
	private String formacaoBasica;
	private String formacaoEnsinoMedio;
	private List<CursoEgresso> curso;
	private List<Experiencia> experiencias;
	private String nome;
	private String sobrenome;
	private Date dataNascimento;
	private String cpf;
	private Sexo sexo;
	private Endereco endereco;
	private Contato contato;

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getFormacaoBasica() {
		return formacaoBasica;
	}

	public void setFormacaoBasica(String formacaoBasica) {
		this.formacaoBasica = formacaoBasica;
	}

	public String getFormacaoEnsinoMedio() {
		return formacaoEnsinoMedio;
	}

	public void setFormacaoEnsinoMedio(String formacaoEnsinoMedio) {
		this.formacaoEnsinoMedio = formacaoEnsinoMedio;
	}

	public List<CursoEgresso> getCurso() {
		return curso;
	}

	public void setCurso(List<CursoEgresso> curso) {
		this.curso = curso;
	}

	public List<Experiencia> getExperiencias() {
		return experiencias;
	}

	public void setExperiencias(List<Experiencia> experiencias) {
		this.experiencias = experiencias;
	}

}