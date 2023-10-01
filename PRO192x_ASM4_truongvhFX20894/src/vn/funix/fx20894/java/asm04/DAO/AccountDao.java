package vn.funix.fx20894.java.asm04.DAO;

import vn.funix.fx20894.java.asm04.models.models_asm2.Account;
import vn.funix.fx20894.java.asm04.services.BinaryFileService;

import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private final static String FILE_PATH = "store\\accounts.dat";
    public static void save(List<Account> accountsList) {
        BinaryFileService.writeFile(FILE_PATH, accountsList);
    }
    public static List<Account> list() {
        return BinaryFileService.readFile(FILE_PATH);
    }

    public static void  update(Account editAccount){
        List<Account> accounts = list();
        boolean hasExist = accounts.stream().anyMatch(account -> account.getAccountNumber().equals(editAccount.getAccountNumber()));
        List<Account> updatedAccounts;
        if(!hasExist){
            updatedAccounts = new ArrayList<>(accounts);
            updatedAccounts.add(editAccount);
        } else {
            updatedAccounts = new ArrayList<>();
            for (Account account: accounts){
                if(account.getAccountNumber().equals(editAccount.getAccountNumber())){
                    updatedAccounts.add(editAccount);
                }else {
                    updatedAccounts.add(account);
                }
            }
        }
        save(updatedAccounts);
    }
}
