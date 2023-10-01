package vn.funix.fx20894.java.asm04.Test;

import org.junit.Before;
import org.junit.Test;
import vn.funix.fx20894.java.asm04.models.models_asm2.Account;

import static org.junit.Assert.assertEquals;

public class AccountTest {
    private Account account;
    @Before
    public void setup() {
        account = new Account("123456", 10000000, "105002926085");
    }

    @Test
    public void getAccountNumber() {
        assertEquals("123456", account.getAccountNumber());
    }

    @Test
    public void getBalance() {
        assertEquals(10000000, account.getBalance(), 0);
    }

    @Test
    public void isPremium() {
        account.setBalance(10000000);
        assertEquals(true, account.isPremium());

        account.setBalance(5000000);
        assertEquals(false, account.isPremium());
    }
}