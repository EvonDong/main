package seedu.address.cashier.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import seedu.address.cashier.model.exception.NoSuchIndexException;
import seedu.address.cashier.util.InventoryList;
import seedu.address.inventory.model.Item;
import seedu.address.person.model.person.Person;
import seedu.address.transaction.model.Transaction;
import seedu.address.transaction.util.TransactionList;

public class StorageManager {

    private String filepathToInventory;
    private String filepathToTransaction;
    private final seedu.address.person.model.Model personModel;

    public StorageManager(String filepathToInventory, String filepathToTransaction,
                          seedu.address.person.model.Model personModel) {
        this.filepathToInventory = filepathToInventory;
        this.filepathToTransaction = filepathToTransaction;
        this.personModel = personModel;
    }

    public InventoryList getInventoryList() throws Exception {
        ArrayList<Item> itemArrayList = new ArrayList<>();
        File f = new File(filepathToInventory);
        f.getParentFile().mkdirs();
        f.createNewFile();
        BufferedReader bfr = new BufferedReader(new FileReader(f));
        String line = null;
        while ((line = bfr.readLine()) != null) {
            Item i = this.readInInventoryFileLine(line);
            itemArrayList.add(i);
        }
        return new InventoryList(itemArrayList);
    }

    public static Item readInInventoryFileLine(String line) {
        String[] stringArr = line.split(" [|] ", 0);
        Item i = null;
        if (stringArr.length == 4) {
            i = new Item(stringArr[0], stringArr[1], Integer.parseInt(stringArr[2]),
                    Double.parseDouble(stringArr[3]));
        } else if (stringArr.length == 5) {
            i = new Item(stringArr[0], stringArr[1], Integer.parseInt(stringArr[2]),
                    Double.parseDouble(stringArr[3]), Double.parseDouble(stringArr[4]),
                    Integer.parseInt(stringArr[5]));
        }
        return i;
    }

    public void writeFileToInventory(InventoryList inventoryList) throws IOException, NoSuchIndexException {
        FileWriter fw = new FileWriter(this.filepathToInventory);
        String textFileMsg = "";
        for (int i = 0; i < inventoryList.size(); i++) {
            if (i == 0) {
                textFileMsg = textFileMsg + (i + 1) + ". " + inventoryList.getItemByIndex(i).toWriteIntoFile();
            } else {
                textFileMsg = textFileMsg + System.lineSeparator() + (i + 1) + ". " +
                        inventoryList.getItemByIndex(i).toWriteIntoFile();
            }
        }
        fw.write(textFileMsg);
        fw.close();
    }

    public TransactionList getTransactionList() throws Exception {
        ArrayList<Transaction> transactionArrayList = new ArrayList<>();
        File f = new File(filepathToTransaction);
        f.getParentFile().mkdirs();
        f.createNewFile();
        BufferedReader bfr = new BufferedReader(new FileReader(f));
        String line = null;
        while ((line = bfr.readLine()) != null) {
            Transaction t = this.readInTransactionFileLine(line, personModel);
            transactionArrayList.add(t);
        }
        return new TransactionList(transactionArrayList);
    }

    public static Transaction readInTransactionFileLine(String line, seedu.address.person.model.Model personModel) {
        String[] stringArr = line.split(" [|] ", 0);
        String[] dateTimeArr = stringArr[0].split(" ");
        Person person = personModel.getPersonByName(stringArr[4]);
        Transaction t = new Transaction(dateTimeArr[1], stringArr[1],
                stringArr[2], Double.parseDouble(stringArr[3]), person,
                Integer.parseInt(dateTimeArr[0].split("[.]")[0]), isReimbursed(stringArr[5]));
        return t;
    }

    private static boolean isReimbursed(String num) {
        return num.equals("1")? true: false;
    }

    public void appendToTransaction(Transaction transaction) throws Exception {
        FileWriter fw = new FileWriter(this.filepathToTransaction, true);
        TransactionList transactionList = getTransactionList();
        String textFileMsg = "";
        if (transactionList.size() == 0) {
            textFileMsg = (transactionList.size() + 1) + ". " + transaction.toWriteIntoFile();
        } else {
            textFileMsg = System.lineSeparator() + (transactionList.size() + 1) + ". " +
                    transaction.toWriteIntoFile();
        }
        fw.write(textFileMsg);
        fw.close();
    }

}