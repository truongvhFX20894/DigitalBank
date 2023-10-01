package vn.funix.fx20894.java.asm04.Test;

import org.junit.Before;
import org.junit.Test;
import vn.funix.fx20894.java.asm04.models.SavingsAccount;

import static org.junit.Assert.assertEquals;

public class SavingsAccountTest {
    private SavingsAccount savingsAccount;
    private SavingsAccount takenAccount;
    @Before
    public void setup() {
        savingsAccount = new SavingsAccount("000000", 10000000, "024098000125");
        takenAccount = new SavingsAccount("111111", 10000000, "024098000125");
    }

    @org.junit.Test
    public void withdraw() {
        assertEquals(true, savingsAccount.withdraw(1000000));
    }

    @org.junit.Test
    public void isAccepted() {
        assertEquals(true, savingsAccount.isAccepted(1000000));
    }

    @Test
    public void transfer() {
        assertEquals(true, savingsAccount.transfer(takenAccount, 1000000));
    }
}