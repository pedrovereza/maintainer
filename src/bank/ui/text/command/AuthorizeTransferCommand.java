package bank.ui.text.command;

import static java.lang.String.format;
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

        List<Transfer> transfers = accountManagementService.getAllPendingTransfers();

        if (transfers.isEmpty()) {
            System.out.println("message.nothing.pending");
            return;
        }

        showSelectableTransfers(transfers);

        int transferSelected = ui.readInteger(null);

        if (transferSelected == 0)
            return;

        Transfer transferToAuthorize = transfers.get(transferSelected - 1);
        System.out.println("message.option.authorize");

        Transfer result;

        if (ui.readString(null).equalsIgnoreCase("s")) {
            result = accountManagementService.authorize(transferToAuthorize);
        }
        else {
            result = accountManagementService.cancel(transferToAuthorize);
        }

        System.out.println(result);
    }

    private void showSelectableTransfers(List<Transfer> transfers) {
        for (int i = 0; i < transfers.size(); ++i) {
            System.out.println(format("%d - %s", i + 1, transfers.get(i)));
        }

        System.out.println("message.select.transfer");
    }
}
