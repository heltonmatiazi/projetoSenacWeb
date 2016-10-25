package br.com.senac.excecao;

public class ProblemaTecnicoError extends Error {
	private static final long serialVersionUID = 1L;
	private String help;

	public ProblemaTecnicoError() {
	}

	public ProblemaTecnicoError(String string, String help) {
		super(string);
		this.help = help;
	}

	public ProblemaTecnicoError(String string) {
		super(string);
	}

	public ProblemaTecnicoError(String string, Throwable thrwbl) {
		super(string, thrwbl);
	}

	public ProblemaTecnicoError(Throwable thrwbl) {
		super(thrwbl);
	}

	public ProblemaTecnicoError(String string, Throwable thrwbl, boolean bln, boolean bln1) {
		super(string, thrwbl, bln, bln1);
	}

	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	@Override
	public String toString() {
		if (getHelp() != null || !getHelp().equals("")) {
			return String.format("%s (%s)", getMessage(), getHelp());
		} else {
			return getMessage();
		}
	}
}