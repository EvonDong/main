package seedu.address.cashier.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.cashier.commands.AddCashierCommand;
import seedu.address.cashier.commands.CheckoutCommand;
import seedu.address.cashier.commands.Command;
import seedu.address.cashier.commands.DeleteCommand;
import seedu.address.cashier.logic.exception.ParseException;
import seedu.address.cashier.model.ModelManager;
import seedu.address.cashier.ui.CashierUi;
import seedu.address.person.logic.commands.AddCommand;
import seedu.address.person.model.Model;

public class CashierTabParser {
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public Command parseCommand(String userInput, ModelManager modelManager,
                                Model personModel) throws Exception {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(CashierUi.MESSAGE_INVALID_ADDCOMMAND_FORMAT);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments, modelManager);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case AddCashierCommand.COMMAND_WORD:
            return new AddCashierCommandParser().parse(arguments, modelManager, personModel);

        case CheckoutCommand.COMMAND_WORD:
            return new CheckoutCommandParser().parse(arguments, modelManager);

        default:
            throw new ParseException(CashierUi.NO_SUCH_COMMAND);

        }
    }
}