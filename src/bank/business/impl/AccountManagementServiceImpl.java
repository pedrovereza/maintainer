/*
 * Created on 5 Jan 2014 00:51:19 
 */
package bank.business.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import bank.business.AccountManagementService;
import bank.business.BusinessException;
import bank.business.domain.Branch;
import bank.business.domain.Client;
import bank.business.domain.CurrentAccount;
import bank.business.domain.Employee;
import bank.business.domain.OperationLocation;
import bank.business.domain.Status;
import bank.business.domain.Transfer;
import bank.data.Database;
import bank.util.RandomString;

/**
 * @author Ingrid Nunes
 * 
 */
public class AccountManagementServiceImpl implements AccountManagementService {

	private final Database database;
	private RandomString random;

	public AccountManagementServiceImpl(Database database) {
		this.database = database;
		this.random = new RandomString(8);
	}

	@Override
	public CurrentAccount createCurrentAccount(long branch, String name,
			String lastName, int cpf, Date birthday, double balance)
			throws BusinessException {
		OperationLocation operationLocation = database
				.getOperationLocation(branch);
		if (operationLocation == null || !(operationLocation instanceof Branch)) {
			throw new BusinessException("exception.invalid.branch");
		}

		Client client = new Client(name, lastName, cpf, random.nextString(),
				birthday);
		CurrentAccount currentAccount = new CurrentAccount(
				(Branch) operationLocation,
				database.getNextCurrentAccountNumber(), client, balance);

		database.save(currentAccount);

		return currentAccount;
	}

	@Override
	public Employee login(String username, String password)
			throws BusinessException {
		Employee employee = database.getEmployee(username);

		if (employee == null) {
			throw new BusinessException("exception.inexistent.employee");
		}
		if (!employee.getPassword().equals(password)) {
			throw new BusinessException("exception.invalid.password");
		}

		return employee;
	}

	@Override	
	public List<Transfer> viewAllPendingTransfers() {
		List<Transfer> pendingTransfers = new LinkedList<Transfer>();
		for (CurrentAccount account : database.getAllCurrentAccounts()) {
			pendingTransfers.addAll(account.getAllTransfersPending());			
		}
		return pendingTransfers;
		
	}

	public Transfer authorize(Transfer transfer) throws BusinessException {
		transfer.getAccount().finalizeTransfer(transfer);
		transfer.setStatus(Status.FINISHED);
		return transfer;
	}

	public Transfer cancel(Transfer transfer) throws BusinessException {
		transfer.getAccount().cancelTransfer(transfer);
		transfer.setStatus(Status.CANCELED);
		return transfer;
	}

}
