
package br.com.senac.model;

public enum Titulo {
	TECNOLOGO(1), LICENCIATURA(2), BACHARELADO(3), MESTRADO(4), DOUTORADO(5), POS_DOUTORADO(6);

	private Integer codigoTitulo;

	private Titulo(Integer codigoTitulo) {
		this.codigoTitulo = codigoTitulo;
	}

	public Integer getCodigoTitulo() {
		return codigoTitulo;
	}

	public void setCodigoTitulo(Integer codigoTitulo) {
		this.codigoTitulo = codigoTitulo;
	}

}