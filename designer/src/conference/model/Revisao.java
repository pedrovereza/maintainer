package conference.model;

public class Revisao {

    private Double nota = null;
    private Integer idAutor;

    public Revisao(Integer idAutor, double nota) {
        this.nota = nota;
        this.idAutor = idAutor;
    }

    public Revisao(Integer idAutor) {
        this.idAutor = idAutor;
    }

    public void setNota(Double nota) {

    }

    public Double getNota() {
        return nota;
    }

    public Integer getIdAutor() {
        return idAutor;
    }
}
