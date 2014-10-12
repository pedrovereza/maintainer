package conference.model;

import java.util.LinkedList;
import java.util.List;

public class SelecaoArtigos {
    List<Artigo> artigosAceitos;
    List<Artigo> artigosRejeitados;

    public SelecaoArtigos(List<Artigo> artigosAceitos, List<Artigo> artigosRejeitados) {
        this.artigosAceitos = new LinkedList<>(artigosAceitos);
        this.artigosRejeitados = new LinkedList<>(artigosRejeitados);
    }

    public List<Artigo> getArtigosAceitos() {
        return artigosAceitos;
    }

    public List<Artigo> getArtigosRejeitados() {
        return artigosRejeitados;
    }
}
