package bank.business.domain;

public enum Status {
	FINISHED("Finalizada"), PENDING("Pendente"), CANCELED("Cancelada");

    private String message;

    private Status(String message) {

        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
