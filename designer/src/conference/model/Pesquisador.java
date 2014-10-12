package conference.model;

import java.util.List;

public class Pesquisador {

    private Integer id;
    private String nome;
    private Universidade afiliacao;
    private List<TopicosPesquisa> topicosInteresse;
    private List<Integer> idsArtigosComoRevisor;

    public Pesquisador(Integer id, String nome, Universidade afiliacao, List<TopicosPesquisa> topicosInteresse) {
        this.id = id;
        this.nome = nome;
        this.afiliacao = afiliacao;
        this.topicosInteresse = topicosInteresse;
    }

    public Integer getId() {
        return id;
    }

    public Universidade getAfiliacao() {
        return afiliacao;
    }

    public boolean temInteresseEm(TopicosPesquisa topicosPesquisa) {
        return topicosInteresse.contains(topicosPesquisa);
    }

    public boolean temArtigoParaRevisar(Integer idArtigo) {
        return idsArtigosComoRevisor.contains(idArtigo);
    }

    public void addIdArtigoParaRevisar(Integer idArtigo) {

    }

    public Integer numeroDeArtigosParaRevisar() {
        return idsArtigosComoRevisor.size();
    }
}
