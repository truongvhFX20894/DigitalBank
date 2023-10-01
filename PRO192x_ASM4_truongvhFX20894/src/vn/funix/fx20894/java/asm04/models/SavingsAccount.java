package vn.funix.fx20894.java.asm04.models;

import vn.funix.fx20894.java.asm04.Interface.ITransfer;
import vn.funix.fx20894.java.asm04.Interface.ReportService;
import vn.funix.fx20894.java.asm04.Interface.Withdraw;
import vn.funix.fx20894.java.asm04.Utils;
import vn.funix.fx20894.java.asm04.models.models_asm2.Account;

import java.io.Serializable;

public class SavingsAccount extends Account implements ReportService, Withdraw, ITransfer {
    private static final double SAVINGS_ACCOUNT_MAX_WITHDRAW = 5000000;
    public SavingsAccount(String accountNumber, double balance, String customerID) {
        super(accountNumber, balance, customerID);
    }

    @Override
    public String toString() {
        return super.getAccountNumber()+" |      SAVINGS  |  "+String.format("%,.0f", super.getBalance())+"đ";
    }

    @Override
    public void log(double amount) {
        System.out.println(Utils.getDivider());
        System.out.printf("%30s%n", Utils.getTitle()+" SAVING");
        System.out.printf("NGAY G/D: %28s%n", Utils.getDateTime());
        System.out.printf("ATM ID: %30s%n","DIGITAL-BANK-ATM 2023");
        System.out.printf("SO TK: %31s%n",getAccountNumber());
        System.out.printf("SO TIEN: %29s%n", Utils.formatBalance(amount));
        System.out.printf("SO DU: %31s%n", Utils.formatBalance(getBalance()));
        System.out.printf("PHI + VAT: %27s%n", Utils.formatBalance(0.0));
        System.out.println(Utils.getDivider());
    }

    @Override
    public boolean withdraw(double amount) {
        if (isAccepted(amount)) {
            setBalance(getBalance()-amount);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean isAccepted(double amount) {
        double minAmount = 50000;
        if (amount>=minAmount && amount%10000==0 && getBalance()-amount>=50000) {
            if (!isPremium()) {
                if (amount<=SAVINGS_ACCOUNT_MAX_WITHDRAW) {
                    return true;
                }
                else {
                    System.out.println("Tài khoản của bạn không được rút quá 5,000,000đ trong một lần rút");
                    return false;
                }
            }
            else {
                return true;
            }
        }
        else {
            System.out.println("Số tiền rút không hợp lệ." +
                    "\n1. Số tiền rút phải là bội số của 10,000." +
                    "\n2. Số tiền rút tối thiểu phải là 50,000đ." +
                    "\n3. Số dư trong tài khoản sau khi rút tối thiểu phải là 50,000đ");
            return false;
        }
    }

    @Override
    public boolean transfer(Account receiveAccount, double amount) {
        if (isAccepted(amount)) {
            setBalance(getBalance()-amount);
            receiveAccount.setBalance(receiveAccount.getBalance()+amount);
            return true;
        } else {
            return false;
        }
    }
}
