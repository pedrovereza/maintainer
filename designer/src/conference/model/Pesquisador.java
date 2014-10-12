package conference.model;

import java.util.List;

public class Pesquisador {

    private Integer id;
    private String nome;
    private Universidade afiliacao;
    private List<TopicosPesquisa> topicosInteresse;

    public Pesquisador(Integer id, String nome, Universidade afiliacao, List<TopicosPesquisa> topicosInteresse) {
        this.id = id;
        this.nome = nome;
        this.afiliacao = afiliacao;
        this.topicosInteresse = topicosInteresse;
    }
}
