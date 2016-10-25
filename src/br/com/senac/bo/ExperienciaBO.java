package br.com.senac.bo;

import br.com.senac.model.Experiencia;
import java.sql.SQLException;


public class ExperienciaBO {
//		
//	
//	public void alterar(Experiencias e){
//			
//		try{
//			ExperienciaDAO dao=new ExperienciaDAO();
//			TipoTrabalhoBO tipoTrabalhoBO=new TipoTrabalhoBO();
//			
//			tipoTrabalhoBO.alterar(e.getTipoTrabalho());
//			dao.alterar(e);
//
//		} catch (SQLException excp){
//			System.out.println("Erro ao alterar Experiencia " + excp.getMessage());
//			excp.printStackTrace();
//		}
//	}
//	
//	public void excluir(int id){
//
//		try{
//			ExperienciaDAO dao=new ExperienciaDAO();
//			TipoTrabalhoBO tipoTrabalhoBO=new TipoTrabalhoBO();
//			
//			tipoTrabalhoBO.excluir(getExperienciaById(id).getTipoTrabalho().getId());
//			dao.excluir(id);
//
//		} catch (SQLException excp){
//			System.out.println("Erro ao excluir Experiencia " + excp.getMessage());
//			excp.printStackTrace();
//		}
//	}
//	
//	public Experiencias getExperienciaById(int id){
//		
//		Experiencias experiencia=null;
//		
//		try{
//			ExperienciaDAO dao=new ExperienciaDAO();
//			experiencia=dao.getExperienciaById();
//			
//		}catch(SQLException excp){
//			System.out.println("Erro ao listar Experiencia. " + excp.getMessage());
//			excp.printStackTrace();
//		}
//		
//		return experiencia;
//	}
}
