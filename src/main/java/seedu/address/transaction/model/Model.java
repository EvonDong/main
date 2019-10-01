package seedu.address.transaction.model;

import seedu.address.transaction.model.exception.NoSuchIndexException;
import seedu.address.transaction.util.TransactionList;

public interface Model {

    void addTransaction(Transaction trans);

    Transaction findTransactionByIndex(int index) throws NoSuchIndexException;

    void deleteTransaction(int index);

    void writeInTransactionFile() throws Exception;

    TransactionList getTransactionList();
}
