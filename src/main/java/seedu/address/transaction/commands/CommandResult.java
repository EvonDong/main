package seedu.address.transaction.commands;

import seedu.address.util.OverallCommandResult;

public class CommandResult extends OverallCommandResult {

    private String feedbackToUser;

    public CommandResult(String feedbackToUser) {
        super(feedbackToUser);
    }
}
