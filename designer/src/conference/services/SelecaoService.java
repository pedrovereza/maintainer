package conference.services;

import conference.data.Database;
import conference.model.Artigo;
import conference.model.SelecaoArtigos;
import conference.services.comparators.ArtigoNotaAscendingComparator;
import conference.services.comparators.ArtigoNotaDescendingComparator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SelecaoService {

    Database database;

    public SelecaoArtigos selecionaArtigosParaConferencia(String siglaConferencia) {
        List<Artigo> artigos = database.getArtigosParaConferencia(siglaConferencia);

         if (!todosArtigosRevisados(artigos)) {
             throw new ConferenciaException();
         }

        return selecionaArtigos(artigos);
    }

    private boolean todosArtigosRevisados(List<Artigo> artigos) {
        return false;
    }

    private SelecaoArtigos selecionaArtigos(List<Artigo> artigos) {
        Double media = artigos.get(0).getMediaNotas();

        List<Artigo> artigosAceitos = new LinkedList<>(artigos);
        List<Artigo> artigosRejeitados = new LinkedList<>(artigos);

        Collections.sort(artigosAceitos, new ArtigoNotaAscendingComparator());
        Collections.sort(artigosRejeitados, new ArtigoNotaDescendingComparator());

        return new SelecaoArtigos(artigosAceitos, artigosRejeitados);
    }

}
