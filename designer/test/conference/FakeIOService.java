package conference;

import conference.services.ConferenceIOService;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class FakeIOService implements ConferenceIOService {

    Queue<String> queue = new LinkedBlockingDeque<>();
    String printedContent = null;

    @Override
    public String readInput() {
        return queue.poll();
    }

    @Override
    public void output(Object obj) {
        printedContent = obj.toString();
    }

    public void enqueueCommand(String s) {
        queue.add(s);
    }

    public String printedContent() {
        return printedContent;
    }
}
