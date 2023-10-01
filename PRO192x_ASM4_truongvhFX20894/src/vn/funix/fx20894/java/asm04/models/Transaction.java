package vn.funix.fx20894.java.asm04.models;

import vn.funix.fx20894.java.asm04.Utils;

import java.io.Serializable;
import java.util.UUID;

public class Transaction implements Serializable {
    private String ID;
    private String accountNumber;
    private double amount;
    private String time;
    private boolean status;
    private TransactionType type;
    private static final long SerialVersionUID = 0L;

    public Transaction(String accountNumber, String time, double amount, boolean status, TransactionType type) {
        this.ID=String.valueOf(UUID.randomUUID());
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.time= time;
        this.status = status;
        this.type = type;
    }

    public String getID() {
        return ID;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getTime() {
        return time;
    }

    public boolean isStatus() {
        return status;
    }

    public void GD() {
        if (isStatus()) {
            System.out.println("[GD] "+getAccountNumber()+" | "+toString()+" | "+ Utils.formatBalance(getAmount())+" (THÀNH CÔNG)"+" | "+getTime());
        }
        else {
            System.out.println("[GD] "+getAccountNumber()+" | "+toString()+" | "+ Utils.formatBalance(getAmount())+" (THẤT BẠI)"+" | "+getTime());
        }
    }

    public String toString() {
        return type.toString();
    }
}

