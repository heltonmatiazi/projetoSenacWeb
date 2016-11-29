package br.com.senac.excecao;

public class ActionException extends Exception {
	private static final long serialVersionUID = 1L;

	public ActionException(String string) {
		super(string);
	}

	public ActionException(String string, Throwable thrwbl) {
		super(string, thrwbl);
	}

	public ActionException(Throwable thrwbl) {
		super(thrwbl);
	}

	public ActionException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
		super(string, thrwbl, bln, bln1);
	}
}
