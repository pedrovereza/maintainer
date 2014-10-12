package conference.command;

import conference.data.Database;
import conference.model.Artigo;
import conference.model.Pesquisador;
import conference.services.ConferenceIOService;
import conference.services.RevisaoService;

import java.util.List;

public class AtribuicaoCommand extends AbstractCommand {

    private Database database;

    private RevisaoService revisaoService = new RevisaoService() {
        @Override
        public void assignRevisao(Artigo artigo, Integer idReviewer, double nota) {
        }
    };

    public AtribuicaoCommand(ConferenceIOService conferenceIOService) {
        super(conferenceIOService);
    }

    @Override
    public void execute() {

        List<Artigo> artigos = database.getArtigos();
        showArtigos(artigos);  //1. Usuário solicita informar uma nota a um artigo,
        // e sistema exibe a lista de artigos.

        Integer idArtigo = conferenceIOService.readInteger(); //2. Usuário seleciona um artigo,
        // e sistema exibe a lista de revisores

        Artigo artigoSelecionado = artigoComId(idArtigo, artigos);
        List<Pesquisador> revisores = revisoresDoArtigo(artigoSelecionado);

        showRevisores(revisores);

        Integer idRevisor = conferenceIOService.readInteger(); //3. Usuário seleciona um revisor.

        showMensagemNota();
        Double nota = conferenceIOService.readDouble();

        revisaoService.assignRevisao(artigoSelecionado, idRevisor, nota);

        conferenceIOService.output("atribuicaoCommand");
    }

    private void showRevisores(List<Pesquisador> revisores) {
        conferenceIOService.output("revisores");
    }

    private Artigo artigoComId(Integer idArtigo, List<Artigo> artigos) {
        return null;
    }

    private void showMensagemNota() {
        conferenceIOService.output("mensagemNota");
    }

    private List<Pesquisador> revisoresDoArtigo(Artigo artigo) {

        artigo.getRevisoes();
        database.getPesquisador(0);

        return null;

    }

    private void showArtigos(List<Artigo> artigos) {
        conferenceIOService.output(artigos);

    }
}
