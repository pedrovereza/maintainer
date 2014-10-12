package conference.command;

import conference.services.ConferenceIOService;

public abstract class AbstractCommand implements Command {

    protected ConferenceIOService conferenceIOService;

    protected AbstractCommand(ConferenceIOService conferenceIOService) {
        this.conferenceIOService = conferenceIOService;
    }
}
