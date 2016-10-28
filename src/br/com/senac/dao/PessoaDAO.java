// package br.com.senac.dao;
//
// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.util.ArrayList;
// import java.util.List;
//
// import br.com.senac.model.Contato;
// import br.com.senac.model.Endereco;
// import br.com.senac.model.Perfil;
// import br.com.senac.model.Pessoa;
// import br.com.senac.model.Sexo;
//
// public class PessoaDAO {
// private Connection conn;
// private PreparedStatement pstm;
// private ResultSet rs;
// private ContatoDAO contato;
// private EnderecoDAO endereco;
// boolean contatoOK;
// boolean enderecoOK;
//
// public void inserir(Pessoa p) throws SQLException {
// try {
// conn = Conexao.getConnection();
// conn.setAutoCommit(false);
//
// contato = new ContatoDAO();
// endereco = new EnderecoDAO();
// Integer idContato = contato.inserir(p.getContato());
// Integer idEndereco = endereco.inserir(p.getEndereco());
//
// String qry = "INSERT INTO PESSOA(NOME, SOBRENOME, DATANASCIMENTO, SEXO, CPF,
// IDCONTATO, IDENDERECO) VALUES(?,?,?,?,?,?,?)";
// pstm = conn.prepareStatement(qry, Statement.RETURN_GENERATED_KEYS);
// pstm.setString(1, p.getNome());
// pstm.setString(2, p.getSobrenome());
// pstm.setDate(3, new java.sql.Date(p.getDataNascimento().getTime()));
// pstm.setString(4, p.getSexo().name());
// pstm.setString(5, p.getCpf());
// pstm.setInt(6, idContato);
// pstm.setInt(7, idEndereco);
// pstm.executeUpdate();
//
// rs = pstm.getGeneratedKeys();
// rs.next();
// p.setId(rs.getInt(1));
//
// if (idContato != null && idEndereco != null & p.getId() != null) {
// conn.commit();
// } else {
// conn.rollback();
// }
//
// conn.setAutoCommit(true);
// } finally {
// Conexao.closeResources(conn, pstm, rs);
// }
//
// }
//
// public void alterar(Pessoa p) throws SQLException {
// /**
// * @param contatoOK
// * @param enderecoOK
// * Variáveis pra controle de integridade.
// * @return true se gravou em contato e endereço.
// */
// contatoOK = false;
// enderecoOK = false;
// try {
// conn = Conexao.getConnection();
// conn.setAutoCommit(false);
//
// contato = new ContatoDAO();
// endereco = new EnderecoDAO();
//
// contatoOK = contato.alterar(p.getContato());
// enderecoOK = endereco.alterar(p.getEndereco());
//
// String upd = "UPDATE PESSOA SET NOME = ?, SOBRENOME = ?, DATANASCIMENTO = ?,
// SEXO = ?, CPF = ? WHERE IDPESSOA = ?";
// pstm = conn.prepareStatement(upd);
// pstm.setString(1, p.getNome());
// pstm.setString(2, p.getSobrenome());
// pstm.setDate(3, new java.sql.Date(p.getDataNascimento().getTime()));
// pstm.setString(4, p.getSexo().toString());
// pstm.setString(5, p.getCpf());
// pstm.setInt(6, p.getId());
// int linhas = pstm.executeUpdate();
//
// if (contatoOK && enderecoOK && linhas > 0) {
// conn.commit();
// } else {
// conn.rollback();
// // throw new exception
// }
// conn.setAutoCommit(true);
// } finally {
// Conexao.closeResources(conn, pstm);
// }
// }
//
// public void excluir(Pessoa p) throws SQLException {
// contatoOK = false;
// enderecoOK = false;
// try {
// conn = Conexao.getConnection();
// conn.setAutoCommit(false);
//
// contato = new ContatoDAO();
// endereco = new EnderecoDAO();
//
// contatoOK = contato.excluir(p.getContato().getIdContato());
// enderecoOK = endereco.excluir(p.getEndereco().getId());
//
// pstm = conn.prepareStatement("DELETE FROM PESSOA WHERE IDPESSOA = ?");
// pstm.setInt(1, p.getId());
// int linhas = pstm.executeUpdate();
//
// if (linhas > 0 && contatoOK && enderecoOK) {
// conn.commit();
// } else {
// conn.rollback();
// // throw exception
// }
// conn.setAutoCommit(true);
// } finally {
// Conexao.closeResources(conn, pstm);
//
// }
// }
//
// public List<Object> getPorNome(String nome) throws SQLException {
// Pessoa p = null;
// List<Perfil> lista = new ArrayList<>();
//
// try {
// ContatoDAO contatoDao = new ContatoDAO();
// EnderecoDAO enderecoDao = new EnderecoDAO();
//
// String qry = "SELECT * FROM PESSOA WHERE NOME LIKE ?";
// conn = Conexao.getConnection();
// pstm = conn.prepareStatement(qry);
// pstm.setString(1, "'%" + nome + "%'");
// rs = pstm.executeQuery();
//
// while (rs.next()) {
// p = new Perfil();
// p.setCpf(rs.getString("cpf"));
// p.setNome(rs.getString("nome"));
// p.setSobrenome(rs.getString("sobrenome"));
// p.setDataNascimento(rs.getDate("datanascimento"));
// p.setSexo(Sexo.valueOf(rs.getString("sexo").toUpperCase()));
// p.setId(rs.getInt("idpessoa"));
//
// Contato c = new Contato();
// c = contatoDao.getById(rs.getInt("idcontato"));
// Endereco e = new Endereco();
// e = enderecoDao.getById(rs.getInt("idendereco"));
//
// p.setContato(c);
// p.setEndereco(e);
// lista.add(p);
// }
//
// return lista;
//
// } finally {
// Conexao.closeResources(conn, pstm, rs);
// }
// }
//
// }
