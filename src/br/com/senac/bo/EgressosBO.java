package br.com.senac.bo;

import java.sql.SQLException;

import br.com.senac.dao.PerfilDAO;
import br.com.senac.excecao.FalhaBancoException;
import br.com.senac.model.Perfil;

public class EgressosBO {

    public void gravarEgresso(Perfil perfil) throws SQLException, FalhaBancoException {
        PerfilDAO perfilDao = new PerfilDAO();
        if (perfil.getIdPerfil() == null) {
            perfilDao.inserir(perfil);
            UsuarioBO bo = new UsuarioBO();
            bo.inserir(bo.geraUsuario(perfil, 1));
        } else {
            perfilDao.alterar(perfil);
        }
    }

}
