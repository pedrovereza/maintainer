/*
 * Created on 16 Dec 2013 16:17:23
 */
package bank.business;

import bank.business.domain.CurrentAccount;
import bank.business.domain.Employee;
import bank.business.domain.Transfer;

import java.util.Date;
import java.util.List;

/**
 * @author Ingrid Nunes
 * 
 */
public interface AccountManagementService {

	public CurrentAccount createCurrentAccount(long branch, String name,
			String lastName, int cpf, Date birthday, double balance)
			throws BusinessException;

	public Employee login(String username, String password)
			throws BusinessException;
	
	public List<Transfer> viewAllPendingTransfers();

    public Transfer authorize(Transfer transfer) throws BusinessException;

    public Transfer cancel(Transfer transfer) throws BusinessException;
}
