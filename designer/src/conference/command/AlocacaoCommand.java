package conference.command;

import conference.services.ConferenceIOService;
import conference.services.ReviewService;

public class AlocacaoCommand extends AbstractCommand {

    private ReviewService reviewService = new ReviewService() {
        @Override
        public void assignReview(Integer idArtigo, Integer idReviewer, double nota) {
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

        reviewService.assignReview(idArtigo, idRevisor, nota);

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
