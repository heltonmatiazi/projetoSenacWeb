package br.com.senac.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    private static Map<String, Action> actions = new HashMap<>();

    static {
        actions.put("POST/upload", new UploadAction());
        actions.put("POST/cadastrar", new CadastroAction());
        actions.put("POST/login", new LoginAction());
        actions.put("GET/excluir", new DeleteAction());
        actions.put("POST/pesquisar", new PesquisaAction());
        actions.put("GET/logout", new LogoutAction());
        actions.put("GET/carregar", new CarregaTelaAction());
    }

    public static Action getAction(HttpServletRequest request) {
        return actions.get(request.getMethod() + request.getPathInfo());
    }

}
