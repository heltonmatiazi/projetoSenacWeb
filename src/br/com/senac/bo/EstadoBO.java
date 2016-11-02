package br.com.senac.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.senac.dao.EstadoDAO;
import br.com.senac.model.Estado;

public class EstadoBO {

	public List<Estado> listaEstados() {
		EstadoDAO dao = new EstadoDAO();
		List<Estado> lista = null;
		try {
			lista = dao.listaEstados();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}

	//
	// public void alterar(Estado e){
	//
	// try{
	// EstadoDAO dao=new EstadoDAO();
	// dao.alterar(e);
	//
	// } catch (SQLException excp){
	// System.out.println("Erro ao alterar estado " + excp.getMessage());
	// excp.printStackTrace();
	// }
	// }
	// public void excluir(int id){
	//
	// try{
	// EstadoDAO dao=new EstadoDAO();
	// dao.excluir(id);
	//
	// } catch (SQLException excp){
	// System.out.println("Erro ao excluir estado " + excp.getMessage());
	// excp.printStackTrace();
	// }
	// }
	//
	// public Estado getEnderecoById(int id){
	//
	// Estado estado=null;
	//
	// try{
	// EstadoDAO dao=new EstadoDAO();
	// estado=dao.getEstadoById();
	//
	//
	// }catch(SQLException excp){
	// System.out.println("Erro ao listar estado. " + excp.getMessage());
	// excp.printStackTrace();
	// }
	//
	// return estado;
	// }
}
