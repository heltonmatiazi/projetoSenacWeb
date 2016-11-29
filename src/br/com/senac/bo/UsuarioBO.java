package br.com.senac.bo;

import java.sql.SQLException;

import br.com.senac.dao.UsuarioDAO;
import br.com.senac.model.Perfil;
import br.com.senac.model.Usuario;

public class UsuarioBO {

	private UsuarioDAO dao;
	private Usuario usuario;

	public Usuario geraUsuario(Perfil p, int tipo) {
		Usuario user = new Usuario();
		user.setLogin(p.getCpf());
		// user.setSenha(BCrypt.hashpw(p.getSobrenome(), BCrypt.gensalt(12)));
		user.setSenha(p.getSobrenome());
		user.setTipoUsuario(tipo);
		user.setNomeUsuario(p.getNome());

		return user;
	}

	public Usuario validaUsuario(String username, String password) throws SQLException {
		dao = new UsuarioDAO();
		usuario = dao.getPorLogin(username);
		// if (usuario != null && BCrypt.checkpw(hashed, usuario.getSenha())) {
		// return usuario;
		// }
		if (usuario != null && usuario.getSenha().equals(password)) {
			return usuario;
		}
		return null;
	}

	public Usuario getUsuario(Usuario u) throws SQLException {
		dao = new UsuarioDAO();
		return dao.getUsuario(u);
	}

	public Integer inserir(Usuario u) throws SQLException {
		dao = new UsuarioDAO();

		return dao.inserir(u);
	}

	// public void alterar(Usuario u) {// alterar o para pegar pelo id
	//
	// try {
	// UsuarioDAO dao = new UsuarioDAO();
	// dao.alterar(u);
	// } catch (SQLException excp) {
	// System.out.println("Erro ao alterar Usuario " + excp.getMessage());
	// excp.printStackTrace();
	// }
	// }
	//
	// public void excluir(int id) {
	// try {
	// UsuarioDAO dao = new UsuarioDAO();
	// dao.excluir(id);
	// } catch (SQLException excp) {
	// System.out.println("Erro ao excluir Usuario " + excp.getMessage());
	// excp.printStackTrace();
	// }
	// }
}
