package conference.model;

import java.util.Collections;
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

    public boolean foiRevisado() {
        return false;
    }

    public Integer getIdAutor() {
        return idAutor;
    }

    public TopicosPesquisa getTopicosPesquisa() {
        return topicosPesquisa;
    }

    public List<Revisao> getRevisoes() {
        return Collections.unmodifiableList(revisoes);
    }

    public void addRevisao(Integer idRevisor, Double nota) {
        revisoes.get(idRevisor).setNota(nota);
    }

    public void addRevisor(Integer idRevisor) {
        revisoes.add(new Revisao(idRevisor));
    }

    public Double getMediaNotas() {
        return null;
    }

    public Integer getId() {
        return id;
    }
}
