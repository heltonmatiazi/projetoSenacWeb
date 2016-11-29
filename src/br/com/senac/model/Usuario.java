package br.com.senac.model;

public class Usuario {

    private String login;
    private String senha;
    private int tipoUsuario;
    private String nomeUsuario;

    public Usuario() {

    }

    public Usuario(String login, String senha) {
        super();
        this.login = login;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

}
