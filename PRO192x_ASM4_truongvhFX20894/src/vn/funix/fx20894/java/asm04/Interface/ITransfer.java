package vn.funix.fx20894.java.asm04.Interface;

import vn.funix.fx20894.java.asm04.models.models_asm2.Account;

public interface ITransfer {
    boolean transfer(Account receiveAccount, double amount);
}
