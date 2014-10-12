package conference.services;

import conference.data.Database;
import conference.model.Artigo;
import conference.model.Conferencia;
import conference.model.Pesquisador;
import conference.services.comparators.RevisorComparator;

import java.util.Collections;
import java.util.List;

public class AlocacaoService {

    Database database;
    ConferenceIOService conferenceIOService;

    public AlocacaoService(ConferenceIOService conferenceIOService) {
        this.conferenceIOService = conferenceIOService;
    }

    public List<Artigo> alocaArtigos(String siglaConferencia, Integer numeroRevisores) {

        conferenceIOService.output("Inicio do processo");

        Conferencia conferencia = database.getConferencia(siglaConferencia);
        List<Pesquisador> comite = conferencia.getMembrosComite();

        List<Artigo> artigosDaConferencia = database.getArtigosParaConferencia(siglaConferencia);

        for (int i = 0; i < numeroRevisores; i++) {

            for (Artigo artigo : artigosDaConferencia) {
                List<Pesquisador> candidatosArevisao = filtraCandidatos(comite, artigo);

                Collections.sort(candidatosArevisao, new RevisorComparator());

                Pesquisador pesquisador = candidatosArevisao.get(0);

                alocaArtigoParaPesquisador(artigo, pesquisador);

                conferenceIOService.output("Artigo %d para revisor %d", artigo.getId(), pesquisador.getId());
            }
        }

        conferenceIOService.output("Fim alocação");

        return artigosDaConferencia;
    }

    private void alocaArtigoParaPesquisador(Artigo artigo, Pesquisador pesquisador) {
        pesquisador.addIdArtigoParaRevisar(artigo.getId());
        artigo.addRevisor(pesquisador.getId());
    }

    private List<Pesquisador> filtraCandidatos(List<Pesquisador> comite, Artigo artigo) {

        Pesquisador pesquisador = comite.get(0);

        if (artigo.getIdAutor().equals(pesquisador.getId()))
            return null;

        if (database.getPesquisador(artigo.getIdAutor()).getAfiliacao().equals(pesquisador.getAfiliacao())){
            return null;
        }

        if (!pesquisador.temInteresseEm(artigo.getTopicosPesquisa()))
            return null;

        if (pesquisador.temArtigoParaRevisar(artigo.getId()))
            return null;


        return null;
        //i. membro que é o autor do artigo ou que pertence à mesma universidade que o autor do
        //    artigo é excluído
        //ii. membro cujos tópicos de pesquisa de interesse não inclui o tópico de pesquisa relacionado
        //ao artigo é excluído
        //iii. membro que já foi alocado para revisar o artigo é excluído
    }
}
