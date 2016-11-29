package br.com.senac.action;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.senac.bo.EgressosBO;
import br.com.senac.excecao.ActionException;
import br.com.senac.excecao.FalhaBancoException;
import br.com.senac.model.Contato;
import br.com.senac.model.Curso;
import br.com.senac.model.CursoEgresso;
import br.com.senac.model.Endereco;
import br.com.senac.model.Estado;
import br.com.senac.model.Experiencia;
import br.com.senac.model.Perfil;
import br.com.senac.model.Sexo;
import br.com.senac.model.TipoTrabalho;
import br.com.senac.model.Titulo;

public class CadastroAction implements Action {
    
    private Perfil perfil;
    private CursoEgresso cursoEgresso;
    private Experiencia exp;
    private Endereco endereco;
    private Contato contato;
    private Curso curso;
    private List<CursoEgresso> listaCurso;
    private List<Experiencia> listaExp;
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        carregaPerfil(request);
        try {
            EgressosBO egressos = new EgressosBO();
            egressos.gravarEgresso(perfil);
            request.setAttribute("message", "Usuário cadastrado com sucesso");
            return "main";
        } catch (FalhaBancoException | SQLException e) {
            throw new ActionException(e.getMessage(), e);
        }
    }
    
    private void carregaPerfil(HttpServletRequest request) {
        perfil = new Perfil();
        cursoEgresso = new CursoEgresso();
        endereco = new Endereco();
        contato = new Contato();
        exp = new Experiencia();
        listaCurso = new ArrayList<>();
        listaExp = new ArrayList<>();
        curso = new Curso();

        /*
		 * Informações Básicas
         */
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        perfil.setNome(request.getParameter("nome"));
        perfil.setSobrenome(request.getParameter("sobrenome"));
        perfil.setCpf(request.getParameter("cpf"));
        
        if (request.getParameter("id") != null && !request.getParameter("id").trim().equals("")) {
            perfil.setIdPerfil(Integer.parseInt(request.getParameter("id")));
        }
        
        try {
            perfil.setDataNascimento(df.parse(request.getParameter("dt-nascimento")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        switch (request.getParameter("sexo")) {
            case "Masculino":
                perfil.setSexo(Sexo.MASCULINO);
                break;
            case "Feminino":
                perfil.setSexo(Sexo.FEMININO);
                break;
        }

        /*
		 * Formação Básica
         */
        perfil.setFormacaoBasica(request.getParameter("formacao-basica"));
        perfil.setFormacaoEnsinoMedio(request.getParameter("form-media"));

        /*
		 * Dados do Curso
         */
        if (request.getParameter("idCurso") != null && !request.getParameter("idCurso").trim().equals("")) {
            cursoEgresso.setIdCursoEgresso(Integer.parseInt(request.getParameter("idCurso")));
        }
        curso.setIdCurso(Integer.parseInt(request.getParameter("cursos")));
        cursoEgresso.setCurso(curso);
        switch (request.getParameter("titulo")) {
            case "tecnologo":
                cursoEgresso.setTitulo(Titulo.TECNOLOGO);
                break;
            case "licenciatura":
                cursoEgresso.setTitulo(Titulo.LICENCIATURA);
                break;
            case "bacharelado":
                cursoEgresso.setTitulo(Titulo.BACHARELADO);
                break;
            case "mestrado":
                cursoEgresso.setTitulo(Titulo.MESTRADO);
                break;
            case "doutorado":
                cursoEgresso.setTitulo(Titulo.DOUTORADO);
                break;
            case "pos-doutorado":
                cursoEgresso.setTitulo(Titulo.POS_DOUTORADO);
                break;
        }
        cursoEgresso.setUnidadeSenac(request.getParameter("unidade-senac"));
        cursoEgresso.setAnoIngresso(Integer.parseInt(request.getParameter("ano-ingresso")));
        cursoEgresso.setSemestreIngresso(Integer.parseInt(request.getParameter("semestre-ingresso")));
        cursoEgresso.setAnoConclusao(Integer.parseInt(request.getParameter("ano-conclusao")));
        cursoEgresso.setSemestreConclusao(Integer.parseInt(request.getParameter("semestre-conclusao")));

        /*
		 * Endereço
         */
        if (request.getParameter("idEndereco") != null && !request.getParameter("idEndereco").trim().equals("")) {
            endereco.setId(Integer.parseInt(request.getParameter("idEndereco")));
        }
        
        endereco.setRua(request.getParameter("rua"));
        endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
        endereco.setComplemento(request.getParameter("Complemento"));
        endereco.setCidade(request.getParameter("cidade"));
        endereco.setCep(request.getParameter("cep"));
        Estado estado = new Estado();
        estado.setId(Integer.parseInt(request.getParameter("estado")));
        endereco.setEstado(estado);

        /*
		 * Contato
         */
        if (request.getParameter("idContato") != null && !request.getParameter("idContato").trim().equals("")) {
            contato.setIdContato(Integer.parseInt(request.getParameter("idContato")));
        }
        contato.setEmail(request.getParameter("email"));
        contato.setTelefone(request.getParameter("telefone"));

        /*
		 * Experiências Profissionais
         */
        if (request.getParameter("idExp") != null && !request.getParameter("idExp").trim().equals("")) {
            exp.setIdExperiencia(Integer.parseInt(request.getParameter("idExp")));
        }
        exp.setEmpresa(request.getParameter("empresa-1"));
        exp.setCargo(request.getParameter("cargo-1"));
        switch (request.getParameter("tipo-trabalho")) {
            case "estagio":
                exp.setTipoTrabalho(TipoTrabalho.ESTAGIO);
                break;
            case "contratacao":
                exp.setTipoTrabalho(TipoTrabalho.CONTRATACAO);
                break;
            case "CLT":
                exp.setTipoTrabalho(TipoTrabalho.CLT);
                break;
            case "concurso":
                exp.setTipoTrabalho(TipoTrabalho.CONCURSO);
                break;
        }
        
        try {
            exp.setDataIngresso(df.parse(request.getParameter("dataEntrada")));
            exp.setDataSaida(df.parse(request.getParameter("dataSaida")));
        } catch (ParseException e) {
            // mensagem na tela
            e.printStackTrace();
        }
        
        listaCurso.add(cursoEgresso);
        listaExp.add(exp);
        
        perfil.setCurso(listaCurso);
        perfil.setExperiencias(listaExp);
        
        perfil.setEndereco(endereco);
        perfil.setContato(contato);
        
    }
    
}
