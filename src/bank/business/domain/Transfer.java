package bank.business.domain;

/**
 * @author Ingrid Nunes
 * 
 */
public class Transfer extends Transaction {

	private CurrentAccount destinationAccount;
	private Status status = Status.FINISHED;


	public Transfer(OperationLocation location, CurrentAccount account,
			CurrentAccount destinationAccount, double amount) {
		super(location, account, amount);
		this.destinationAccount = destinationAccount;
	}
	
	public Transfer(OperationLocation location, CurrentAccount account,
			CurrentAccount destinationAccount, double amount, Status status) {
		this(location, account, destinationAccount, amount);
		this.status = status;
	}


	/**
	 * @return the destinationAccount
	 */
	public CurrentAccount getDestinationAccount() {
		return destinationAccount;
	}

	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}

    public boolean isPending() {
        return status.equals(Status.PENDING);
    }
}
