package br.com.senac.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.senac.dao.PessoaDAO;
import br.com.senac.model.Pessoa;


public class PessoaBO {

	
/*
	public void inserir(Pessoa p){

		try{
			PessoaDAO pessoadao=new PessoaDAO();
			pessoadao.inserir(p);

		} catch (SQLException excp){
			System.out.println("Erro ao inserir Pessoa " + excp.getMessage());
			excp.printStackTrace();
		}
	}
*/
//	public void alterar(Pessoa p){// alterar o para pegar pelo id
//
//		try{
//			PessoaDAO pessoadao=new PessoaDAO();
//			EnderecoBO enderecobo=new EnderecoBO();
//			
//			enderecobo.alterar(p.getEndereco());
//			pessoadao.alterar(p);
//						
//		} catch (SQLException excp){
//			System.out.println("Erro ao alterar Pessoa " + excp.getMessage());
//			excp.printStackTrace();
//		}
//	}
//	public void excluir(int id){
//
//		try{
//			PessoaDAO pessoadao=new PessoaDAO();
//			EnderecoBO enderecobo=new EnderecoBO();
//			
//			enderecobo.excluir(getPessoaById(id).getEndereco().getId());
//			pessoadao.excluir(id);		
//			
//		} catch (SQLException excp){
//			System.out.println("Erro ao excluir Pessoa " + excp.getMessage());
//			excp.printStackTrace();
//		}
//	}
//	
//	public List<Pessoa> getAllUsers(){
//			
//		List<Pessoa> lista = null;
//		try{
//			PessoaDAO pessoadao=new PessoaDAO();
//			lista=pessoadao.getAllUsers();
//
//		} catch (SQLException excp){
//			System.out.println("Erro ao listar Pessoa. " + excp.getMessage());
//			excp.printStackTrace();
//		}
//		return lista;
//	}
//	public Pessoa getPessoaById(int id){
//		
//		Pessoa pessoa=null;
//		try{
//		PessoaDAO pessoadao=new PessoaDAO();
//		pessoa=pessoadao.getPessoaById(id);
//	
//		} catch (SQLException excp){
//			System.out.println("Erro ao listar Pessoa. " + excp.getMessage());
//			excp.printStackTrace();
//		}
//		return pessoa;
//	}
	
}
