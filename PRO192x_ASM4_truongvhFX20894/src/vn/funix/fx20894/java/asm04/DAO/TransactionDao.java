package vn.funix.fx20894.java.asm04.DAO;

import vn.funix.fx20894.java.asm04.models.Transaction;
import vn.funix.fx20894.java.asm04.services.BinaryFileService;

import java.util.List;

public class TransactionDao {
    private final static String FILE_PATH = "store/transactions.dat";

    public static void save(List<Transaction> transactionList) {
        BinaryFileService.writeFile(FILE_PATH, transactionList);
    }
    public static List<Transaction> getTransactionsList() {
        return BinaryFileService.readFile(FILE_PATH);
    }
}
