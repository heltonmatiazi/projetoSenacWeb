package br.com.senac.model;

public class CursoEgresso {

    private Integer idCursoEgresso;
    private Integer idPerfil;
    private String unidadeSenac;
    private int anoIngresso;
    private int semestreIngresso;
    private int anoConclusao;
    private int semestreConclusao;
    private Titulo titulo;
    private Curso curso;

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Integer getIdCursoEgresso() {
        return idCursoEgresso;
    }

    public void setIdCursoEgresso(Integer idCursoEgresso) {
        this.idCursoEgresso = idCursoEgresso;
    }

    public Integer getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getUnidadeSenac() {
        return unidadeSenac;
    }

    public void setUnidadeSenac(String unidadeSenac) {
        this.unidadeSenac = unidadeSenac;
    }

    public int getAnoIngresso() {
        return anoIngresso;
    }

    public void setAnoIngresso(int anoIngresso) {
        this.anoIngresso = anoIngresso;
    }

    public int getSemestreIngresso() {
        return semestreIngresso;
    }

    public void setSemestreIngresso(int semestreIngresso) {
        this.semestreIngresso = semestreIngresso;
    }

    public int getAnoConclusao() {
        return anoConclusao;
    }

    public void setAnoConclusao(int anoConclusao) {
        this.anoConclusao = anoConclusao;
    }

    public int getSemestreConclusao() {
        return semestreConclusao;
    }

    public void setSemestreConclusao(int semestreConclusao) {
        this.semestreConclusao = semestreConclusao;
    }

    public Titulo getTitulo() {
        return titulo;
    }

    public void setTitulo(Titulo titulo) {
        this.titulo = titulo;
    }

}
