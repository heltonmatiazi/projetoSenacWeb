package br.com.senac.servlets;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import br.com.senac.bo.EgressosBO;
import br.com.senac.bo.FiltrosBO;
import br.com.senac.bo.PerfilBO;
import br.com.senac.excecao.FalhaBancoException;
import br.com.senac.model.Contato;
import br.com.senac.model.CursoEgresso;
import br.com.senac.model.Endereco;
import br.com.senac.model.Estado;
import br.com.senac.model.Experiencia;
import br.com.senac.model.Perfil;
import br.com.senac.model.Sexo;
import br.com.senac.model.TipoTrabalho;
import br.com.senac.model.Titulo;
import br.com.senac.tratamento.ImportaArquivo;

@WebServlet(name = "/ServletControle", urlPatterns = "/ServletControle")
public class ServletControle extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private RequestDispatcher rd;
	private final String UPLOAD_DIRECTORY = "C:\\SNAC\\";
	private ImportaArquivo importador;
	private Perfil perfil;
	private CursoEgresso curso;
	private Experiencia exp;
	private Endereco endereco;
	private Contato contato;
	private List<CursoEgresso> listaCurso;
	private List<Experiencia> listaExp;

	public ServletControle() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.request = request;
		this.response = response;
		processaRequisicao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.request = request;
		this.response = response;
		processaRequisicao();
	}

	private void processaRequisicao() throws ServletException, IOException {
		String comando = request.getParameter("cmd");
		switch (comando) {
		case "upload":
			upload();
			break;
		case "cadastrar":
			cadastrar();
			break;
		case "pesquisar":
			pesquisar();
			break;
		case "carregar":
			carregarTela();
			break;
		case "excluir":
			excluir();
			break;
		default:
			break;
		}
	}

	private void carregarTela() throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		PerfilBO perfilBO = new PerfilBO();
		Perfil p = new Perfil();
		try {
			p = perfilBO.getById(id);
		} catch (SQLException e) {
			System.out.println("Erro ao preparar alteração: " + e.getMessage());
			e.printStackTrace();
		}

		request.setAttribute("perfil", p);
		rd = request.getRequestDispatcher("/html/index.jsp");
		rd.forward(request, response);

	}

	private void upload() {
		try {
			carregaXls();
		} catch (ServletException | IOException e) {
			request.setAttribute("message", e);
			rd = request.getRequestDispatcher("/html/500.jsp");
			e.printStackTrace();
		}

	}

	private void pesquisar() throws ServletException, IOException {
		FiltrosBO filtro = new FiltrosBO();
		List<Perfil> lista = new ArrayList<>();
		String tipo = request.getParameter("param");

		try {
			switch (tipo) {
			case "1":
				lista = filtro.getPerfilPorNome(request.getParameter("text"));
				break;
			case "2":
				lista = filtro.getPerfilPorCurso(request.getParameter("curso"));
				break;
			case "3":
				lista = filtro.getPerfilPorUnidade(request.getParameter("unidade-senac"));
				break;
			case "4":
				lista = filtro.getPerfilPorAno(request.getParameter("ano-conclusao"));
				break;
			case "5":
				lista = filtro.getPerfilPorSemestre(request.getParameter("semestre-conclusao"));
				break;
			case "6": // pesquisaporestado
				break;
			}
			request.setAttribute("lista", lista);
			rd = request.getRequestDispatcher("/html/busca.jsp");
			rd.forward(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void carregaPerfil() {
		perfil = new Perfil();
		curso = new CursoEgresso();
		endereco = new Endereco();
		contato = new Contato();
		exp = new Experiencia();
		listaCurso = new ArrayList<>();
		listaExp = new ArrayList<>();

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
			curso.setIdCursoEgresso(Integer.parseInt(request.getParameter("idCurso")));
		}
		curso.setNomeCurso(request.getParameter("curso"));
		switch (request.getParameter("titulo")) {
		case "tecnologo":
			curso.setTitulo(Titulo.TECNOLOGO);
			break;
		case "licenciatura":
			curso.setTitulo(Titulo.LICENCIATURA);
			break;
		case "bacharelado":
			curso.setTitulo(Titulo.BACHARELADO);
			break;
		case "mestrado":
			curso.setTitulo(Titulo.MESTRADO);
			break;
		case "doutorado":
			curso.setTitulo(Titulo.DOUTORADO);
			break;
		case "pos-doutorado":
			curso.setTitulo(Titulo.POS_DOUTORADO);
			break;
		}
		curso.setUnidadeSenac(request.getParameter("unidade-senac"));
		curso.setAnoIngresso(Integer.parseInt(request.getParameter("ano-ingresso")));
		curso.setSemestreIngresso(Integer.parseInt(request.getParameter("semestre-ingresso")));
		curso.setAnoConclusao(Integer.parseInt(request.getParameter("ano-conclusao")));
		curso.setSemestreConclusao(Integer.parseInt(request.getParameter("semestre-conclusao")));

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

		listaCurso.add(curso);
		listaExp.add(exp);

		perfil.setCurso(listaCurso);
		perfil.setExperiencias(listaExp);

		perfil.setEndereco(endereco);
		perfil.setContato(contato);

	}

	private void excluir() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));
		PerfilBO perfilBO = new PerfilBO();
		try {
			perfilBO.excluir(userId);
			request.setAttribute("message", "Aluno excluído com sucesso.");
			rd = request.getRequestDispatcher("/html/resultado.jsp");
			rd.forward(request, response);
		} catch (FalhaBancoException | SQLException e) {
			request.setAttribute("message", e);
			rd = request.getRequestDispatcher("/html/500.jsp");
			rd.forward(request, response);
		}
	}

	private void carregaXls() throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		importador = new ImportaArquivo();

		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				List<FileItem> multiparts = upload.parseRequest(request);
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String name = item.getName();
						File uploadedFile = new File(UPLOAD_DIRECTORY + File.separator + name);
						importador.importar(uploadedFile);
					}
				}
				request.setAttribute("message", "Seu arquivo foi enviado!");
			} catch (Exception e) {
				request.setAttribute("message", "Falha ao enviar o arquivo: " + e);
			}
		} else {
			request.setAttribute("message", "Somente uploads permitidos");
		}
		rd = request.getRequestDispatcher("/html/resultado.jsp");
		rd.forward(request, response);

	}

	private void cadastrar() throws ServletException, IOException {
		carregaPerfil();
		try {
			EgressosBO egressos = new EgressosBO();
			egressos.gravarEgresso(perfil);
			request.setAttribute("message", "Aluno gravado com sucesso.");
			rd = request.getRequestDispatcher("/html/resultado.jsp");
			rd.forward(request, response);
		} catch (FalhaBancoException | SQLException e) {
			request.setAttribute("message", e);
			rd = request.getRequestDispatcher("/html/500.jsp");
			rd.forward(request, response);
		}
	}
}
