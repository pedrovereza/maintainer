package conference.services;

public interface ConferenceIOService {

    String readInput();
    Double readDouble();
    Integer readInteger();
    void output(Object obj);
    void output(String format, Object... obj);
}
