
package br.com.senac.model;

public enum TipoTrabalho {
    ESTAGIO(1), CONTRATACAO(2), CLT(3), CONCURSO(4);
    
    private int codigoTipo;
    
    private TipoTrabalho(int codigoTipo) {
        this.codigoTipo = codigoTipo;
    }

    public int getCodigoTipo() {
        return codigoTipo;
    }

    public void setCodigoTipo(int codigoTipo) {
        this.codigoTipo = codigoTipo;
    }
        
}
