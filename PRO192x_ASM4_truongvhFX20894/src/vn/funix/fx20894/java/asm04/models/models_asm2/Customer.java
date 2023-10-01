package vn.funix.fx20894.java.asm04.models.models_asm2;

import vn.funix.fx20894.java.asm04.DAO.AccountDao;
import vn.funix.fx20894.java.asm04.Utils;
import vn.funix.fx20894.java.asm04.models.SavingsAccount;
import vn.funix.fx20894.java.asm04.models.TransactionType;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Customer extends User implements Serializable {
    private static List<Account> accounts = AccountDao.list();
    private static final long serialVersionUID = 0L;

    public Customer(String name, String customerID) {
        super(name, customerID);
    }

    public Customer(List<String> values) {
        this(values.get(1), values.get(0));
    }

    public List<Account> getAccounts() {
        return accounts.stream()
                .filter(account -> account.getCustomerID().equals(getCustomerID()))
                .collect(Collectors.toList());
    }

    public Account getAccountByAccountNumber(List<Account> accounts, String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public String isPremium() {
        for (Account account : getAccounts()) {
            if (account.isPremium()) {
                return "Premium";
            }
        }
        return "Normal";
    }

    public boolean checkAccountExisted(String accountNumber) {
        boolean check = false;
        for (Account account: accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                check = true;
                break;
            }
        }
        return check;
    }

    public void addAccount(Account newAccount) {
        accounts.add(newAccount);
        AccountDao.save(accounts);
    }

    public double getBalance() {
        double totalBalance = 0;
        for (Account account : getAccounts()) {
            totalBalance = totalBalance + account.getBalance();
        }
        return totalBalance;
    }

    public void displayInformation() {
        System.out.println( getCustomerID() + "|      "  +getName()+" | "+isPremium()+" | "+String.format("%,.0f", getBalance())+"đ");
        for (int i=0;i<getAccounts().size();i++) {
            System.out.print((i+1)+"    ");
            System.out.println(getAccounts().get(i).toString());
        }
    }

    public void withdraw(Scanner sc) {
        List<Account> accounts = getAccounts();
        if (!accounts.isEmpty()) {
            Account account;
            double amount;
            String accountNumber;

            do {
                System.out.println("Nhập số tài khoản: ");
                accountNumber = sc.nextLine();
                account = getAccountByAccountNumber(accounts, accountNumber);
            } while (account==null);

            do {
                System.out.println("Nhập số tiền rút: ");
                amount = Double.parseDouble(sc.nextLine());
            } while (amount<=0);

            if (account instanceof SavingsAccount) {
                SavingsAccount savingsAccount = (SavingsAccount) account;
                if (savingsAccount.withdraw(amount)) {
                    savingsAccount.log(amount);
                    savingsAccount.createTransaction(-amount, Utils.getDateTime(), true, TransactionType.WITHDRAW);
                }
                else {
                    savingsAccount.createTransaction(-amount, Utils.getDateTime(), false, TransactionType.WITHDRAW);
                }
            }
        } else {
            System.out.println("Khách hàng không có tài khoản nào, thao tác không thành công.");
        }
    }

    public void transfer(Scanner sc) {
        List<Account> accounts = getAccounts();
        if (!accounts.isEmpty()) {
            String accountNumber1;
            String accountNumber2;
            Account accountGiven;
            Account accountTaken;
            double amount;

            do {
                System.out.print("Nhập số tài khoản chuyển: ");
                accountNumber1 = sc.nextLine();
                accountGiven = getAccountByAccountNumber(accounts, accountNumber1);
            } while (accountGiven==null);

            do {
                System.out.print("Nhập số tài khoản nhận: ");
                accountNumber2 = sc.nextLine();
                accountTaken = getAccountByAccountNumber(accounts, accountNumber2);
            } while (accountTaken==null);

            do {
                System.out.println("Nhập số tiền chuyển: ");
                amount = Double.parseDouble(sc.nextLine());
            } while (amount<=0);

            if (accountGiven instanceof SavingsAccount) {
                SavingsAccount savingsAccount = (SavingsAccount) accountGiven;
                if (savingsAccount.transfer(accountTaken, amount)) {
                    savingsAccount.log(amount);
                    savingsAccount.createTransaction(-amount, Utils.getDateTime(), true, TransactionType.TRANSFER);
                    accountTaken.createTransaction(amount, Utils.getDateTime(), true, TransactionType.TRANSFER);
                }
                else {
                    savingsAccount.createTransaction(-amount, Utils.getDateTime(), false, TransactionType.TRANSFER);
                    accountTaken.createTransaction(amount, Utils.getDateTime(), false, TransactionType.TRANSFER);
                }
            }

        } else {
            System.out.println("Khách hàng không có tài khoản nào, thao tác không thành công.");
        }
    }
}
