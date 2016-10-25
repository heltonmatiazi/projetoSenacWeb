
package br.com.senac.dao;

import br.com.senac.model.Contato;
import br.com.senac.model.Endereco;
import br.com.senac.model.Pessoa;
import br.com.senac.model.Sexo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FiltrosDAO {
    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;
    
    
    /*
    buscar	pesquisa por nome

    filtrar 	pesquisa por curso
    		pesquisa por unidade do senac que cursou
    		pesquisa por ano de conclusão
    		pesquisa por semestre de conclusão
    		pesquisa por estado
    		pesquisa por sexo 
    */
}
