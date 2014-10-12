package conference;

import conference.command.AlocacaoCommand;
import conference.command.AtribuicaoCommand;
import conference.command.Command;
import conference.command.SelecaoCommand;
import conference.services.ConferenceIOService;

import java.util.HashMap;
import java.util.Map;

public class ConferenceService {

    private ConferenceIOService conferenceIOService;
    private Map<String, Command> commands;

    public ConferenceService(ConferenceIOService conferenceIOService) {
        this.conferenceIOService = conferenceIOService;

        initializeCommands();
    }

    private void initializeCommands() {
        commands = new HashMap<String, Command>()
        {{
                put("1", new AlocacaoCommand(conferenceIOService));
                put("2", new AtribuicaoCommand(conferenceIOService));
                put("3", new SelecaoCommand(conferenceIOService));
        }};
    }

    public void run() {
        conferenceIOService.output("menu");
        String opcao = conferenceIOService.readInput();

        if (opcao.equalsIgnoreCase("0"))
            return;

        commands.get(opcao).execute();
    }
}
