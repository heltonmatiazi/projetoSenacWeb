package br.com.senac.bo;

public class CursoEgressoBO {

//	
//	public void alterar(CursoEgresso c){
//
//		try{
//			CursoEgressoDAO dao=new CursoEgressoDAO();
//			TituloBO titulobo=new TituloBO();
//			
//			titulobo.alterar(c.getTitulo());
//			dao.alterar(c);
//
//		} catch (SQLException excp){
//			System.out.println("Erro ao alterar CursoEgresso " + excp.getMessage());
//			excp.printStackTrace();
//		}
//	}
//	
//	public void excluir(int id){
//
//		try{
//			CursoEgressoDAO dao=new CursoEgressoDAO();
//			TituloBO titulobo=new TituloBO();
//			
//			titulobo.excluir(getCursoEgressoById(id).getTitulo().getId());
//			dao.excluir(id);
//
//		} catch (SQLException excp){
//			System.out.println("Erro ao excluir CursoEgresso " + excp.getMessage());
//			excp.printStackTrace();
//		}
//	}
//	
//	public CursoEgresso getCursoEgressoById(int id){
//		
//		CursoEgresso cursoEgresso=null;
//		
//		try{
//			CursoEgressoDAO dao=new CursoEgressoDAO();
//			cursoEgresso=dao.getCursoEgressoById();
//			
//		}catch(SQLException excp){
//			System.out.println("Erro ao listar CursoEgresso. " + excp.getMessage());
//			excp.printStackTrace();
//		}
//		
//		return cursoEgresso;
//	}
}
