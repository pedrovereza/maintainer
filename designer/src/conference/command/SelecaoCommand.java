package conference.command;

import conference.model.SelecaoArtigos;
import conference.services.ConferenceIOService;
import conference.services.SelecaoService;

public class SelecaoCommand extends AbstractCommand {

    SelecaoService selecaoService;

    public SelecaoCommand(ConferenceIOService conferenceIOService) {
        super(conferenceIOService);
    }

    @Override
    public void execute() {
        conferenceIOService.output("conferencia");
        String conferencia = conferenceIOService.readInput();

        SelecaoArtigos selecaoArtigos = selecaoService.selecionaArtigosParaConferencia(conferencia);

        conferenceIOService.output(selecaoArtigos);

        conferenceIOService.output("selecaoCommand");
    }
}
