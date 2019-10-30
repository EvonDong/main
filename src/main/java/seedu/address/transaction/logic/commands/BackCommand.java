package seedu.address.transaction.logic.commands;

import seedu.address.transaction.model.Model;

/**
 * Backs out from a find command interface to the complete transaction list.
 */
public class BackCommand extends Command {
    public static final String COMMAND_WORD = "back";

    @Override
    public CommandResult execute(Model model, seedu.address.person.model.GetPersonByNameOnlyModel personModel) {
        model.resetPredicate();
        return new CommandResult("");
    }

    @Override
    public String toString() {
        return COMMAND_WORD;
    }
}
