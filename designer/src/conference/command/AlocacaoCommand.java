package conference.command;

import conference.model.Artigo;
import conference.services.AlocacaoService;
import conference.services.ConferenceIOService;

import java.util.List;

public class AlocacaoCommand extends AbstractCommand {

    AlocacaoService alocacaoService;

    public AlocacaoCommand(ConferenceIOService conferenceIOService) {
        super(conferenceIOService);
    }

    @Override
    public void execute() {

        conferenceIOService.output("conferencia");
        String siglaConferencia = conferenceIOService.readInput();

        conferenceIOService.output("revisores");
        Integer numeroRevisores = conferenceIOService.readInteger();

        List<Artigo> artigosAlocados = alocacaoService.alocaArtigos(siglaConferencia, numeroRevisores);

        showResultadoAlocacao(artigosAlocados);

        conferenceIOService.output("alocacaoCommand");
    }

    private void showResultadoAlocacao(List<Artigo> artigosAlocados) {
        conferenceIOService.output("artigos e revisores");

    }


}
