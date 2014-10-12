import static org.junit.Assert.assertEquals;
import conference.ConferenceService;
import conference.FakeIOService;
import org.junit.Test;

public class ConferenceServiceTest {

    @Test
    public void shows_a_menu() throws Exception {
        FakeIOService fakeIOService = new FakeIOService();
        fakeIOService.enqueueCommand("0");

        ConferenceService conferenceService = new ConferenceService(fakeIOService);
        conferenceService.run();

        assertEquals("menu", fakeIOService.printedContent());
    }

    @Test
    public void option_1_is_for_AlocacaoCommand() throws Exception {
        FakeIOService fakeIOService = new FakeIOService();
        fakeIOService.enqueueCommand("1");

        ConferenceService conferenceService = new ConferenceService(fakeIOService);
        conferenceService.run();

        assertEquals("alocacaoCommand", fakeIOService.printedContent());
    }

    @Test
    public void option_2_is_for_AtribuicaoCommand() throws Exception {
        FakeIOService fakeIOService = new FakeIOService();
        fakeIOService.enqueueCommand("2");

        ConferenceService conferenceService = new ConferenceService(fakeIOService);
        conferenceService.run();

        assertEquals("atribuicaoCommand", fakeIOService.printedContent());
    }

    @Test
    public void option_3_is_for_SelecaoCommand() throws Exception {
        FakeIOService fakeIOService = new FakeIOService();
        fakeIOService.enqueueCommand("3");

        ConferenceService conferenceService = new ConferenceService(fakeIOService);
        conferenceService.run();

        assertEquals("selecaoCommand", fakeIOService.printedContent());
    }
}
