package conference.command;

import conference.services.ConferenceIOService;

public class AtribuicaoCommand extends AbstractCommand {

    public AtribuicaoCommand(ConferenceIOService conferenceIOService) {
        super(conferenceIOService);
    }

    @Override
    public void execute() {
        conferenceIOService.output("atribuicaoCommand");
    }
}
