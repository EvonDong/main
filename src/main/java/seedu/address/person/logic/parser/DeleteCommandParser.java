package seedu.address.person.logic.parser;

import static seedu.address.person.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.person.commons.core.index.Index;
import seedu.address.person.logic.commands.DeleteCommand;
import seedu.address.person.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteIndexCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteIndexCommand
     * and returns a DeleteIndexCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
