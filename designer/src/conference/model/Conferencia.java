package conference.model;

import java.util.List;

public class Conferencia {

    private String sigla;
    private List<Artigo> artigos;
    private List<Pesquisador> membrosComite;

    public Conferencia(String sigla, List<Artigo> artigos, List<Pesquisador> membrosComite) {
        this.sigla = sigla;
        this.artigos = artigos;
        this.membrosComite = membrosComite;
    }

    public List<Pesquisador> getMembrosComite() {
        return membrosComite;
    }
}
