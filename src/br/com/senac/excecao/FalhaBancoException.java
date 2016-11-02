package br.com.senac.excecao;

public class FalhaBancoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String help;

	public FalhaBancoException() {
	}

	public FalhaBancoException(String string) {
		super(string);
	}

	public FalhaBancoException(String string, String help) {
		super(string);
		this.help = help;
	}

	public FalhaBancoException(String string, Throwable thrwbl) {
		super(string, thrwbl);
	}

	public FalhaBancoException(Throwable thrwbl) {
		super(thrwbl);
	}

	public FalhaBancoException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
		super(string, thrwbl, bln, bln1);
	}

	public void setHelp(String help) {
		this.help = help;
	}

	public String getHelp() {
		return help;
	}

	@Override
	public String toString() {
		if (getHelp() != null || !getHelp().equals("")) {
			return String.format("%s (%s)", getHelp(), getMessage());
		} else {
			return getMessage();
		}
	}
}
