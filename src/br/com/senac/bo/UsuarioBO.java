// package br.com.senac.bo;
//
// import java.sql.SQLException;
//
// import br.com.senac.dao.UsuarioDAO;
// import br.com.senac.model.Perfil;
// import br.com.senac.model.Usuario;
//
// public class UsuarioBO {
// private UsuarioDAO dao;
//
// public Usuario geraUsuario(Perfil p, int tipo) {
// Usuario user = new Usuario();
// user.setLogin(p.getCpf());
// user.setSenha(p.getNome());
// user.setTipoUsuario(tipo);
//
// return user;
// }
//
// public boolean validaUsuario(Perfil p) throws SQLException {
// try {
// return dao.validaLogin(p.getNome());
//
// } catch (SQLException e) {
//
// }
// return false;
// }
//
// public Integer inserir(Usuario u) throws SQLException {
// dao = new UsuarioDAO();
//
// return dao.inserir(u);
// }
//
// public void alterar(Usuario u) {// alterar o para pegar pelo id
//
// try {
// UsuarioDAO dao = new UsuarioDAO();
// dao.alterar(u);
//
// } catch (SQLException excp) {
// System.out.println("Erro ao alterar Usuario " + excp.getMessage());
// excp.printStackTrace();
// }
// }
//
// public void excluir(int id) {
//
// try {
// UsuarioDAO dao = new UsuarioDAO();
// dao.excluir(id);
//
// } catch (SQLException excp) {
// System.out.println("Erro ao excluir Usuario " + excp.getMessage());
// excp.printStackTrace();
// }
// }
//
// }
