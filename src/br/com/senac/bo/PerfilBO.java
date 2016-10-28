package br.com.senac.bo;

import java.sql.SQLException;

import br.com.senac.dao.PerfilDAO;
import br.com.senac.model.Perfil;

public class PerfilBO {
	private PerfilDAO perfildao;

	public void inserir(Perfil p) {
		try {
			perfildao = new PerfilDAO();
			perfildao.inserir(p);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/*
	 * public void alterar(Perfil p){
	 * 
	 * try{ PerfilDAO perfildao=new PerfilDAO(); ExperienciaBO experienciaBO=new
	 * ExperienciaBO(); CursoEgressoBO cursoEgressoBO=new CursoEgressoBO();
	 * 
	 * experienciaBO.alterar(p.getExperiencias());
	 * cursoEgressoBO.alterar(p.getCursoEgresso()); perfildao.alterar(p);
	 * 
	 * } catch (SQLException excp){ System.out.println("Erro ao alterar Perfil "
	 * + excp.getMessage()); excp.printStackTrace(); } }
	 * 
	 * public void excluir(int id){
	 * 
	 * try{ PerfilDAO dao=new PerfilDAO(); ExperienciaBO experienciaBO=new
	 * ExperienciaBO(); CursoEgressoBO cursoEgressoBO=new CursoEgressoBO();
	 * 
	 * experienciaBO.excluir(getPerfilById(id).getExperiencias().getId());
	 * cursoEgressoBO.excluir(getPerfilById(id).getCursoEgresso().getId());
	 * dao.excluir(id);
	 * 
	 * } catch (SQLException excp){ System.out.println("Erro ao excluir Perfil "
	 * + excp.getMessage()); excp.printStackTrace(); } }
	 */

	public void excluir(Integer id) throws SQLException {
		PerfilDAO dao = new PerfilDAO();
		dao.excluir(id);
	}

	public Perfil getById(Integer id) throws SQLException {
		PerfilDAO dao = new PerfilDAO();

		return dao.getById(id);
	}

}
