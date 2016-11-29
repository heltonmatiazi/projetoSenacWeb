package br.com.senac.servlets;

import br.com.senac.action.Action;
import br.com.senac.action.ActionFactory;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.senac.bo.EstadoBO;
import br.com.senac.excecao.ActionException;
import br.com.senac.model.Estado;
import com.google.gson.Gson;

@WebServlet("/egressos/*")
public class ServletControle extends HttpServlet implements Serializable {

    private static final long serialVersionUID = 1L;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher rd;
    private List<Estado> listaEstados;

    public ServletControle() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;

        processaRequisicao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.request = request;
        this.response = response;
        processaRequisicao();
    }

    // request parse o final do url depois do servlet e.g. /validar
    private void processaRequisicao() throws ServletException, IOException {
        EstadoBO bo = new EstadoBO();
        Gson gson = new Gson();
        listaEstados = bo.listaEstados();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(listaEstados));

//        if (listaEstados == null) {
//            EstadoBO bo = new EstadoBO();
//            listaEstados = bo.listaEstados();
//            request.setAttribute("listaEstados", listaEstados);
//        }
//        if (cursosCadastrados == null) {
//            CursoBO cursoBO = new CursoBO();
//            cursosCadastrados = cursoBO.getCursos();
//            request.setAttribute("cursosCadastrados", cursosCadastrados);
//        }
        Action action = ActionFactory.getAction(request);
        if (action != null) {
            try {
                String retorno = action.execute(request, response);
                rd = request.getRequestDispatcher("/html/" + retorno + ".jsp");
                rd.forward(request, response);
            } catch (ActionException e) {
                request.setAttribute("message", e.getMessage());
                rd = request.getRequestDispatcher("/html/500.jsp");
                rd.forward(request, response);
            }
        }
    }

    // private void processaRequisicao() throws ServletException, IOException {
    // String comando = request.getParameter("cmd");
    // switch (comando) {
    // case "upload":
    // upload();
    // break;
    // case "cadastrar":
    // cadastrar();
    // break;
    // case "pesquisar":
    // pesquisar();
    // break;
    // case "carregar":
    // carregarTela();
    // break;
    // case "excluir":
    // excluir();
    // break;
    // default:
    // break;
    // }
    // }
    // private void upload() {
    // try {
    // carregaXls();
    // } catch (ServletException | IOException e) {
    // request.setAttribute("message", e);
    // rd = request.getRequestDispatcher("/html/500.jsp");
    // e.printStackTrace();
    // }
    //
    // }
    // private void pesquisar() throws ServletException, IOException {
    // FiltrosBO filtro = new FiltrosBO();
    // List<Perfil> lista = new ArrayList<>();
    // String tipo = request.getParameter("param");
    //
    // try {
    // switch (tipo) {
    // case "1":
    // lista = filtro.getPerfilPorNome(request.getParameter("text"));
    // break;
    // case "2":
    // lista = filtro.getPerfilPorCurso(request.getParameter("curso"));
    // break;
    // case "3":
    // lista =
    // filtro.getPerfilPorUnidade(request.getParameter("unidade-senac"));
    // break;
    // case "4":
    // lista = filtro.getPerfilPorAno(request.getParameter("ano-conclusao"));
    // break;
    // case "5":
    // lista =
    // filtro.getPerfilPorSemestre(request.getParameter("semestre-conclusao"));
    // break;
    // case "6": // pesquisaporestado
    // break;
    // }
    // request.setAttribute("lista", lista);
    // rd = request.getRequestDispatcher("/html/busca.jsp");
    // rd.forward(request, response);
    //
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    //
    // }
    // private void excluir() throws ServletException, IOException {
    //
    // }
    // private void carregaXls() throws ServletException, IOException {
    // boolean isMultipart = ServletFileUpload.isMultipartContent(request);
    // importador = new ImportaArquivo();
    //
    // if (isMultipart) {
    // FileItemFactory factory = new DiskFileItemFactory();
    // ServletFileUpload upload = new ServletFileUpload(factory);
    // try {
    // List<FileItem> multiparts = upload.parseRequest(request);
    // for (FileItem item : multiparts) {
    // if (!item.isFormField()) {
    // String name = item.getName();
    // File uploadedFile = new File(UPLOAD_DIRECTORY + File.separator + name);
    // importador.importar(uploadedFile);
    // }
    // }
    // request.setAttribute("message", "Seu arquivo foi enviado!");
    // } catch (Exception e) {
    // request.setAttribute("message", "Falha ao enviar o arquivo: " + e);
    // }
    // } else {
    // request.setAttribute("message", "Somente uploads permitidos");
    // }
    // rd = request.getRequestDispatcher("/html/resultado.jsp");
    // rd.forward(request, response);
    //
    // }
    // private void cadastrar() throws ServletException, IOException {
    // carregaPerfil();
    // try {
    // EgressosBO egressos = new EgressosBO();
    // egressos.gravarEgresso(perfil);
    // request.setAttribute("message", "Aluno gravado com sucesso.");
    // rd = request.getRequestDispatcher("/html/resultado.jsp");
    // rd.forward(request, response);
    // } catch (FalhaBancoException | SQLException e) {
    // request.setAttribute("message", e);
    // rd = request.getRequestDispatcher("/html/500.jsp");
    // rd.forward(request, response);
    // }
    // }
}
