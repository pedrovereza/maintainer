package bank.ui.text.command;

import bank.business.AccountManagementService;
import bank.business.domain.Transfer;
import bank.ui.text.BankTextInterface;
import bank.ui.text.UIUtils;

import java.util.List;

public class AuthorizeTransferCommand extends Command {

    private final AccountManagementService accountManagementService;

    public AuthorizeTransferCommand(BankTextInterface bankInterface, AccountManagementService accountManagementService) {
        super(bankInterface);
        this.accountManagementService = accountManagementService;
    }

    @Override
    public void execute() throws Exception {
        UIUtils ui = UIUtils.INSTANCE;

        List<Transfer> transfers = accountManagementService.viewAllPendingTransfers();

        if (transfers.isEmpty()) {
            System.out.println("Não há transferências para autorizar");
            return;
        }

        for (int i = 0; i < transfers.size(); ++i) {
            System.out.println(String.format("%d - %s", i, transfers.get(i)));
        }
        System.out.println("Selecione uma das transferências listadas: ");


        int transferSelected = ui.readInteger(null);

        Transfer transferToAuthorize = transfers.get(transferSelected);
        System.out.println("Deseja autorizar? (S/N)");

        Transfer result;

        if (ui.readString(null).equalsIgnoreCase("s")) {
            result = accountManagementService.authorize(transferToAuthorize);
        }
        else {
            result = accountManagementService.cancel(transferToAuthorize);
        }

        System.out.println(result);
    }
}
