package bank.business.impl;

import static org.junit.Assert.assertEquals;
import bank.business.BusinessException;
import bank.business.domain.ATM;
import bank.business.domain.Branch;
import bank.business.domain.Client;
import bank.business.domain.CurrentAccount;
import bank.business.domain.Status;
import bank.business.domain.Transfer;
import bank.data.Database;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class AccountOperationServiceTest {

    public static final long BRANCH_ID = 1;
    public static final long SOURCE_ACCOUNT = 1;
    public static final long DESTINATION_ACCOUNT = 2;
    public static final int ATM_ID = 123;

    private Branch branch;
    private CurrentAccount destinationAccount;
    private CurrentAccount sourceAccount;
    private AccountOperationServiceImpl accountService;


    @Before
    public void setUp() {
        Database database = new Database(false);
        ATM atm = new ATM(ATM_ID);

        branch = new Branch(BRANCH_ID, "Campus Vale");
        sourceAccount = anAccount(SOURCE_ACCOUNT);
        destinationAccount = anAccount(DESTINATION_ACCOUNT);

        database.save(atm);
        database.save(branch);
        database.save(sourceAccount);
        database.save(destinationAccount);

        accountService = new AccountOperationServiceImpl(database);
    }

    @Test
    public void should_add_transaction_as_finished_when_value_is_lower_than_5000() throws BusinessException {
        givenAccountHasBalanceOf(5000.0, SOURCE_ACCOUNT);
        givenAccountHasBalanceOf(0.0, DESTINATION_ACCOUNT);

        Transfer transfer = accountService.transfer(ATM_ID, BRANCH_ID, SOURCE_ACCOUNT, BRANCH_ID,
                DESTINATION_ACCOUNT, 4000.0);

        assertEquals(transfer.getDestinationAccount(), destinationAccount);
        assertEquals(transfer.getAccount(), sourceAccount);
        assertEquals(transfer.getAmount(), 4000.0, 0.0);
        assertEquals(Status.FINISHED, transfer.getStatus());
        assertEquals(4000.0, destinationAccount.getBalance(), 0.0);
    }
    
    @Test
    public void should_add_transaction_as_finished_when_branch() throws BusinessException {
        givenAccountHasBalanceOf(7000.0, SOURCE_ACCOUNT);
        givenAccountHasBalanceOf(0.0, DESTINATION_ACCOUNT);

        Transfer transfer = accountService.transfer(BRANCH_ID, BRANCH_ID, SOURCE_ACCOUNT, BRANCH_ID,
                DESTINATION_ACCOUNT, 6000.0);

        assertEquals(transfer.getDestinationAccount(), destinationAccount);
        assertEquals(transfer.getAccount(), sourceAccount);
        assertEquals(transfer.getAmount(), 6000.0, 0.0);
        assertEquals(Status.FINISHED, transfer.getStatus());
        assertEquals(6000.0, destinationAccount.getBalance(), 0.0);
    }
    
    @Test
    public void should_add_transaction_as_pending_when_value_is_greater_than_5000() throws BusinessException {
        givenAccountHasBalanceOf(5000.0, SOURCE_ACCOUNT);
        givenAccountHasBalanceOf(0.0, DESTINATION_ACCOUNT);

        Transfer transfer = accountService.transfer(ATM_ID, BRANCH_ID, SOURCE_ACCOUNT, BRANCH_ID,
                DESTINATION_ACCOUNT, 5000.0);

        assertEquals(transfer.getDestinationAccount(), destinationAccount);
        assertEquals(transfer.getAccount(), sourceAccount);
        assertEquals(transfer.getAmount(), 5000.0, 0.0);
        assertEquals(Status.PENDING, transfer.getStatus());
        assertEquals(0.0, destinationAccount.getBalance(), 0.0);
        assertEquals(0.0, sourceAccount.getBalance(), 0.0);
    }

    private CurrentAccount anAccount(long accountId) {
        return new CurrentAccount(branch, accountId, new Client(" ", " ", 12345, "abc",
                new Date()));
    }

    private void givenAccountHasBalanceOf(double amount, long accountId) throws BusinessException {
        if (amount == 0)
            return;

        accountService.deposit(ATM_ID, BRANCH_ID, accountId, 123, amount);
    }
}
