package br.com.senac.servlets;

import br.com.senac.tratamento.ImportaArquivo;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name="/UploadFile", urlPatterns="/UploadFile")
public class UploadFile extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String UPLOAD_DIRECTORY = "C:\\SNAC\\";
    private ImportaArquivo importador;

    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
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
            catch (Exception e) 
            {
                request.setAttribute("message", "Falha ao enviar o arquivo: " + e);
            }
        
        } else 
        {
        request.setAttribute("message", "Somente uploads permitidos");
        }
        request.getRequestDispatcher("/html/resultado.jsp").forward(request, response);
   }
}