package br.com.senac.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import br.com.senac.excecao.ActionException;
import br.com.senac.tratamento.ImportaArquivo;

public class UploadAction implements Action {
	private final String UPLOAD_DIRECTORY = "D:\\TS\\";
	private ImportaArquivo importador;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
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
				request.setAttribute("uploadmsg", "Seu arquivo foi enviado!");

			} catch (Exception e) {
				request.setAttribute("uploadmsg", "Falha ao enviar o arquivo: " + e);

			}
		} else {
			request.setAttribute("uploadmsg", "Somente uploads permitidos");
		}

		return "main";
	}

}
