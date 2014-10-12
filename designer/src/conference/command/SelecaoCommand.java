package conference.command;

import conference.services.ConferenceIOService;

public class SelecaoCommand extends AbstractCommand {

    public SelecaoCommand(ConferenceIOService conferenceIOService) {
        super(conferenceIOService);
    }

    @Override
    public void execute() {
        conferenceIOService.output("selecaoCommand");
    }
}
