package br.com.senac.bo;

import br.com.senac.dao.EnderecoDAO;
import java.sql.SQLException;

import br.com.senac.model.Endereco;

public class EnderecoBO {

	/*
	public void inserir(Endereco e){

		try{
			EnderecoDAO enderecodao=new EnderecoDAO();
			enderecodao.inserir(e);

		} catch (SQLException excp){
			System.out.println("Erro ao inserir Endereco " + excp.getMessage());
			excp.printStackTrace();
		}
	}*/
//	
//	public void alterar(Endereco e){
//
//		try{
//			EnderecoDAO enderecodao=new EnderecoDAO();
//			EstadoBO estadobo=new EstadoBO;
//			
//			enderecodao.alterar(e);
//			estadobo.alterar(e.getEstado());
//
//		} catch (SQLException excp){
//			System.out.println("Erro ao alterar Endereco " + excp.getMessage());
//			excp.printStackTrace();
//		}
//	}
//	public void excluir(int id){
//
//		try{
//			EnderecoDAO enderecodao=new EnderecoDAO();
//			EstadoBO estadobo=new EstadoBO;
//			
//			estadobo.excluir(getEnderecoById(id).getEstado().getId());
//			enderecodao.excluir(id);
//
//		} catch (SQLException excp){
//			System.out.println("Erro ao excluir Endereco " + excp.getMessage());
//			excp.printStackTrace();
//		}
//	}
//	
//	public Endereco getEnderecoById(int id){
//		
//		Endereco endereco=null;
//		
//		try{
//			EnderecoDAO dao=new EnderecoDAO();
//			endereco=dao.getEnderecoById();
//			
//		}catch(SQLException excp){
//			System.out.println("Erro ao listar Endereço. " + excp.getMessage());
//			excp.printStackTrace();
//		}
//		
//		return endereco;
//	}
}
