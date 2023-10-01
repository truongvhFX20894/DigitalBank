package vn.funix.fx20894.java.asm04.models.models_asm2;

import vn.funix.fx20894.java.asm04.DAO.TransactionDao;
import vn.funix.fx20894.java.asm04.Utils;
import vn.funix.fx20894.java.asm04.models.Transaction;
import vn.funix.fx20894.java.asm04.models.TransactionType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Account implements Serializable {
    private String accountNumber;
    private double balance;
    private static final long serialVersionUID = 1L;
    private final String customerID;

    private List<Transaction> transactions = TransactionDao.getTransactionsList();

    public Account(String accountNumber, double balance, String customerID) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerID = customerID;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isPremium() {
        return this.balance >= 10000000;
    }
    public String toString() {

        return accountNumber+" |      "+String.format("%,.0f", balance)+"đ";
    }

    public List<Transaction> getTransactions() {
        List<Transaction> transactionList = new ArrayList<>();
        for (Transaction transaction: transactions) {
            if (Objects.equals(transaction.getAccountNumber(), this.accountNumber)) {
                transactionList.add(transaction);
            }
        }
        return transactionList;
    }

    public void displayTransactionsList() {
        for (Transaction transaction: getTransactions() ) {
            transaction.GD();
        }
    }

    public void createTransaction(double amount, String time, boolean status, TransactionType type) {
        Transaction transaction = new Transaction(accountNumber, time, amount, status, type);
        transactions.add(transaction);
        TransactionDao.save(transactions);
    }

    public void input(Scanner sc) {

    }


}
