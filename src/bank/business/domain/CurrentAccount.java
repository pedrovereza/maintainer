package bank.business.domain;

import static java.util.Collections.unmodifiableList;
import bank.business.BusinessException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Ingrid Nunes
 * 
 */
public class CurrentAccount implements Credentials {

	private static final int MAX_ALLOWED_AMOUNT = 5000;
	private double balance;
	private Client client;
	private List<Deposit> deposits;
	private CurrentAccountId id;
	private List<Transfer> transfers;
	private List<Withdrawal> withdrawals;

	public CurrentAccount(Branch branch, long number, Client client) {
		this.id = new CurrentAccountId(branch, number);
		branch.addAccount(this);
		this.client = client;
		client.setAccount(this);
		this.deposits = new ArrayList<>();
		this.transfers = new ArrayList<>();
		this.withdrawals = new ArrayList<>();
	}

	public CurrentAccount(Branch branch, long number, Client client,
			double initialBalance) {
		this(branch, number, client);
		this.balance = initialBalance;
	}

	public Deposit deposit(OperationLocation location, long envelope,
			double amount) throws BusinessException {
		depositAmount(amount);

		Deposit deposit = new Deposit(location, this, envelope, amount);
		this.deposits.add(deposit);

		return deposit;
	}

	private void depositAmount(double amount) throws BusinessException {
		if (!isValidAmount(amount)) {
			throw new BusinessException("exception.invalid.amount");
		}

		this.balance += amount;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @return the deposits
	 */
	public List<Deposit> getDeposits() {
		return deposits;
	}

	/**
	 * @return the id
	 */
	public CurrentAccountId getId() {
		return id;
	}

	public List<Transaction> getTransactions() {
		List<Transaction> transactions = new ArrayList<>(deposits.size()
				+ withdrawals.size() + transfers.size());
		transactions.addAll(deposits);
		transactions.addAll(withdrawals);
		transactions.addAll(transfers);
		return transactions;
	}

	/**
	 * @return the transfers
	 */
	public List<Transfer> getTransfers() {
		return transfers;
	}

	/**
	 * @return the withdrawals
	 */
	public List<Withdrawal> getWithdrawals() {
		return withdrawals;
	}

	private boolean hasEnoughBalance(double amount) {
		return amount <= balance;
	}

	private boolean isValidAmount(double amount) {
		return amount > 0;
	}

	public Transfer transfer(OperationLocation location,
			CurrentAccount destinationAccount, double amount)
			throws BusinessException {

		withdrawalAmount(amount);

		Transfer transfer = new Transfer(location, this, destinationAccount,
				amount);
		
		if (isATM(location) && amount >= MAX_ALLOWED_AMOUNT) {
			transfer.setStatus(Status.PENDING);
		} else {
			destinationAccount.depositAmount(amount);
		}
		
		this.transfers.add(transfer);
		destinationAccount.transfers.add(transfer);

		return transfer;
	}

    private boolean isATM(OperationLocation location) {
        return location instanceof ATM;
    }

    public List<Transfer> getAllTransfersPending() {
        List<Transfer> pendingTransfer = new LinkedList<>();

        for (Transfer transfer : transfers) {
            if (transfer.isPending() && transfer.getAccount().equals(this)) {
                pendingTransfer.add(transfer);
            }
        }

        return unmodifiableList(pendingTransfer);
    }

    public Withdrawal withdrawal(OperationLocation location, double amount)
			throws BusinessException {
		withdrawalAmount(amount);

		Withdrawal withdrawal = new Withdrawal(location, this, amount);
		this.withdrawals.add(withdrawal);

		return withdrawal;
	}

	private void withdrawalAmount(double amount) throws BusinessException {
		if (!isValidAmount(amount)) {
			throw new BusinessException("exception.invalid.amount");
		}

		if (!hasEnoughBalance(amount)) {
			throw new BusinessException("exception.insufficient.balance");
		}

		this.balance -= amount;
	}

	public void finalizeTransfer(Transfer transfer) throws BusinessException {
		transfer.getDestinationAccount().depositAmount(transfer.getAmount());
	}

	public void cancelTransfer(Transfer transfer) throws BusinessException {
		this.depositAmount(transfer.getAmount());
	}

}
