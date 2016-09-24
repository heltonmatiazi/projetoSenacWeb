package br.com.senac.servlets;
import br.com.senac.tratamento.ImportaArquivo;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

@WebServlet(name="/ServletControle",urlPatterns="/ServletControle")
public class ServletControle extends HttpServlet implements Serializable{
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
    private RequestDispatcher rd;
    private final String UPLOAD_DIRECTORY = "C:\\SNAC\\";
    private ImportaArquivo importador;

    public ServletControle() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		processaRequisicao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		processaRequisicao();
	}

	private void processaRequisicao()  throws ServletException, IOException{
		String comando = request.getParameter("cmd");
		switch(comando){
		case "salvar":
			salvar();
			break;
		case "pesquisar":
			pesquisar();
		case "alterar":
			alterar();
			break;
		case "excluir":
			excluir();
			break;	
		default:
			break;
		}
		rd.forward(request, response);
	}

	private void salvar() {
    	carregaXls();
    	rd = request.getRequestDispatcher("/mostraProduto.jsp");
    }
	private void pesquisar() {
		// TODO Auto-generated method stub
		
	}
	private void alterar(){

    }
    private void excluir(){

    }

	private void carregaXls() {
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
            } 
            catch (Exception e){            
                request.setAttribute("message", "Falha ao enviar o arquivo: " + e);
            }
        } else
        {
        request.setAttribute("message", "Somente uploads permitidos");
        }
        try {
			request.getRequestDispatcher("/html/resultado.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
