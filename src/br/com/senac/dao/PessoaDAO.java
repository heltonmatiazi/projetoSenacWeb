package br.com.senac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import br.com.senac.model.Pessoa;
import br.com.senac.model.Sexo;
import java.util.ArrayList;
import java.util.Date;

public class PessoaDAO {
    private Connection conn;
    private PreparedStatement pstm; 
    private ResultSet rs;        
    
    /*
        Teste de Métodos
    */
    public static void main(String[] args) {
        Pessoa pessoa = new Pessoa();        
        
        PessoaDAO dao = new PessoaDAO();
        
	pessoa.setNome("Teste"); 
	pessoa.setSobrenome("TesteTeste");
	pessoa.setDataNascimento(new Date());
	pessoa.setCpf("123");
	pessoa.setSexo(Sexo.FEMININO);
        
        try {
            dao.inserir(pessoa);
        
            List<Pessoa> lista = dao.getPorNome("L");
            
            dao.excluir(lista.get(0).getId());
            
            Pessoa pessoa2 = lista.get(1);
            pessoa2.setNome("Novo Nome");
            dao.alterar(pessoa2);
            
            for(Pessoa p : lista) {
                System.out.printf("%s %s%n", p.getNome(), p.getSobrenome());
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
    }  
        
    //por enquanto não contempla Endereço, já que não vem no xls
    public Integer inserir(Pessoa p) throws SQLException {
        String qry = "INSERT INTO PESSOA(NOME, SOBRENOME, DATANASCIMENTO, SEXO, CPF) VALUES(?,?,?,?,?)";
        conn = Conexao.getConnection();
        pstm = conn.prepareStatement(qry, PreparedStatement.RETURN_GENERATED_KEYS);
        pstm.setString(1, p.getNome());
        pstm.setString(2, p.getSobrenome());
        pstm.setDate(3, new java.sql.Date(p.getDataNascimento().getTime()));
        pstm.setString(4, p.getSexo().toString());
        pstm.setString(5, p.getCpf());
        pstm.executeUpdate();
        
        rs = pstm.getGeneratedKeys();
        rs.next();
        
        return rs.getInt(1);      
    }
    
    public void alterar(Pessoa p) throws SQLException {
        String upd = "UPDATE PESSOA SET NOME = ?, SOBRENOME = ?, DATANASCIMENTO = ?, SEXO = ?, CPF = ? WHERE IDPESSOA = ?";
        conn = Conexao.getConnection();        
        pstm = conn.prepareStatement(upd);
        pstm.setString(1, p.getNome());
        pstm.setString(2, p.getSobrenome());
        pstm.setDate(3, new java.sql.Date(p.getDataNascimento().getTime()));
        pstm.setString(4, p.getSexo().toString());
        pstm.setString(5, p.getCpf());
        pstm.setInt(6, p.getId());
        pstm.executeUpdate();
    }
    
    public void excluir(Integer idPessoa) throws SQLException {
        conn = Conexao.getConnection();        
        pstm = conn.prepareStatement("DELETE FROM PESSOA WHERE IDPESSOA = ?");
        pstm.setInt(1, idPessoa);
        pstm.executeUpdate();
    }
    
    public List<Pessoa> getPorNome(String nome) throws SQLException {
        Pessoa p = null;
        List<Pessoa> lista = new ArrayList<>();
        String qry = "SELECT * FROM PESSOA WHERE NOME LIKE ?";
        conn = Conexao.getConnection();        
        pstm = conn.prepareStatement(qry);
        pstm.setString(1, "%"+nome+"%");
        rs = pstm.executeQuery();
        
        while(rs.next()) {
            p = new Pessoa();
            p.setCpf(rs.getString("cpf"));
            p.setNome(rs.getString("nome"));
            p.setSobrenome(rs.getString("sobrenome"));
            p.setDataNascimento(rs.getDate("datanascimento"));
            p.setSexo(Sexo.valueOf(rs.getString("sexo").toUpperCase()));
            p.setId(rs.getInt("idpessoa"));
            lista.add(p);
        }
        
        return lista;
    }        

}