package conference.command;

import conference.services.ConferenceIOService;

public class AlocacaoCommand extends AbstractCommand {


    public AlocacaoCommand(ConferenceIOService conferenceIOService) {
        super(conferenceIOService);
    }

    @Override
    public void execute() {


        conferenceIOService.output("alocacaoCommand");
    }


}
