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
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountManagementServiceTest {

    public static final long BRANCH_ID = 1;
    public static final long DESTINATION_ACCOUNT = 2;
    public static final int ATM_ID = 123;

    private Branch branch;
    private CurrentAccount destinationAccount;
    private CurrentAccount sourceAccount;
    private AccountManagementServiceImpl accountService;
    private ATM atm;

    @Before
    public void setUp() throws BusinessException {
        Database database = new Database(false);
        atm = new ATM(ATM_ID);

        branch = new Branch(BRANCH_ID, "Campus Vale");
        sourceAccount = Mockito.mock(CurrentAccount.class);
        destinationAccount = anAccount(DESTINATION_ACCOUNT);

        Mockito.doCallRealMethod().when(sourceAccount).finalizeTransfer(Mockito.any(Transfer.class));
        Mockito.doCallRealMethod().when(sourceAccount).cancelTransfer(Mockito.any(Transfer.class));

        database.save(atm);
        database.save(branch);
        database.save(sourceAccount);
        database.save(destinationAccount);
        
        List<Transfer> transfers = new ArrayList<Transfer>();
        transfers.add((new Transfer(atm, sourceAccount, destinationAccount, 1000, Status.FINISHED)));
        transfers.add((new Transfer(atm, sourceAccount, destinationAccount, 5500, Status.PENDING)));
        transfers.add((new Transfer(atm, sourceAccount, destinationAccount, 7000, Status.PENDING)));
        transfers.add((new Transfer(atm, sourceAccount, destinationAccount, 6000, Status.PENDING)));

        accountService = new AccountManagementServiceImpl(database);
    }
    
    @Test
    public void list_all_pending_transfers(){
        accountService.getAllPendingTransfers();
        Mockito.verify(sourceAccount).getAllTransfersPending();
    }
    
    @Test
    public void authrorize_pending_transfers() throws BusinessException{
    	Transfer transfer = new Transfer(atm, sourceAccount, destinationAccount, 5500, Status.PENDING);
    	Transfer authorizedTransfer = accountService.authorize(transfer);
    	assertEquals(Status.FINISHED, authorizedTransfer.getStatus());
    	assertEquals(5500, destinationAccount.getBalance(), 0.0);
    }
    
    @Test
    public void cancel_pending_transfers() throws BusinessException{
    	Transfer transfer = new Transfer(atm, sourceAccount, destinationAccount, 5500, Status.PENDING);
    	Transfer authorizedTransfer = accountService.cancel(transfer);
    	assertEquals(Status.CANCELED, authorizedTransfer.getStatus());
    	assertEquals(0, destinationAccount.getBalance(), 0.0);
    }
    
    private CurrentAccount anAccount(long accountId) {
        return new CurrentAccount(branch, accountId, new Client(" ", " ", 12345, "abc",
                new Date()));
    }
}
