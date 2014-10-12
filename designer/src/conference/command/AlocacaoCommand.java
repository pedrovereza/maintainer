package conference.command;

import conference.services.ConferenceIOService;
import conference.services.RevisaoService;

public class AlocacaoCommand extends AbstractCommand {

    private RevisaoService revisaoService = new RevisaoService() {
        @Override
        public void assignRevisao(Integer idArtigo, Integer idReviewer, double nota) {
        }
    };

    public AlocacaoCommand(ConferenceIOService conferenceIOService) {
        super(conferenceIOService);
    }

    @Override
    public void execute() {
        showArtigos(); //1. Usuário solicita informar uma nota a um artigo, e sistema exibe a lista de artigos.

        Integer idArtigo = conferenceIOService.readInteger(); //2. Usuário seleciona um artigo,
        // e sistema exibe a lista de revisores
        
        showRevisoresDoArtigo(idArtigo);

        Integer idRevisor = conferenceIOService.readInteger(); //3. Usuário seleciona um revisor.

        showMensagemNota();
        Double nota = conferenceIOService.readDouble();

        revisaoService.assignRevisao(idArtigo, idRevisor, nota);

        conferenceIOService.output("alocacaoCommand");
    }

    private void showMensagemNota() {
        conferenceIOService.output("mensagemNota");
    }

    private void showRevisoresDoArtigo(Integer idArtigo) {

    }

    private void showArtigos() {

    }
}
