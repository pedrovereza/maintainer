package bank.ui.text.command;

import static java.lang.String.format;
import bank.business.AccountManagementService;
import bank.business.BusinessException;
import bank.business.domain.Transfer;
import bank.ui.text.BankTextInterface;
import bank.ui.text.UIUtils;

import java.util.List;

public class AuthorizeTransferCommand extends Command {

    public static final String BACK_OPTION = "X";
    public static final String AUTHORIZE_OPTION = "s";
    public static final String CANCEL_OPTION = "n";
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
            System.out.println(getTextManager().getText("message.nothing.pending"));
            return;
        }

        Transfer transferToAuthorize = selectTransfer(ui, transfers);

        if (transferToAuthorize == null) {
            return;
        }

        String option = readValidAuthorizeOption(ui);

        if (option.equalsIgnoreCase(BACK_OPTION)) {
            return;
        }

        Transfer result = applyOptionToTransfer(option, transferToAuthorize);

        System.out.println(result);
    }

    private Transfer applyOptionToTransfer(String option, Transfer transferToAuthorize) throws BusinessException {
        Transfer result;

        if (option.equalsIgnoreCase(AUTHORIZE_OPTION)) {
            result = accountManagementService.authorize(transferToAuthorize);
        } else {
            result = accountManagementService.cancel(transferToAuthorize);
        }
        return result;
    }

    private Transfer selectTransfer(UIUtils ui, List<Transfer> transfers) {
        showSelectableTransfers(transfers);

        int selectedIndex = ui.readInteger(null, 0, transfers.size());

        if (selectedIndex == 0) {
            return null;
        }

        return transfers.get(selectedIndex - 1);
    }

    private void showSelectableTransfers(List<Transfer> transfers) {
        for (int i = 0; i < transfers.size(); ++i) {
            System.out.println(format("%d - %s", i + 1, transfers.get(i)));
        }

        System.out.println(getTextManager().getText("message.select.transfer"));
    }

    private String readValidAuthorizeOption(UIUtils ui) {
        String option;

        do {
            System.out.println(getTextManager().getText("message.option.authorize"));
            option = ui.readString(null);

        } while (!isValidOption(option));

        return option;
    }

    private boolean isValidOption(String option) {
        return option.equalsIgnoreCase(AUTHORIZE_OPTION) ||
                option.equalsIgnoreCase(CANCEL_OPTION) ||
                option.equalsIgnoreCase(BACK_OPTION);
    }
}
