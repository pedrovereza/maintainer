package conference.model;

import java.util.List;

public class Artigo {

    private Integer id;
    private String titulo;
    private Integer idAutor;
    private String siglaConferencia;
    private TopicosPesquisa topicosPesquisa;
    private List<Revisao> revisoes;

    public Artigo(Integer id, String titulo, Integer idAutor, String siglaConferencia, TopicosPesquisa topicosPesquisa) {
        this.id = id;
        this.titulo = titulo;
        this.idAutor = idAutor;
        this.siglaConferencia = siglaConferencia;
        this.topicosPesquisa = topicosPesquisa;
    }

    public void addRevisao(Revisao revisao) {

    }
}
