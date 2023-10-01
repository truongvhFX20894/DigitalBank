package vn.funix.fx20894.java.asm04.Test;

import org.junit.Before;
import org.junit.Test;
import vn.funix.fx20894.java.asm04.models.models_asm2.Account;
import vn.funix.fx20894.java.asm04.models.models_asm2.Customer;

import static org.junit.Assert.assertEquals;

public class CustomerTest {
    private Customer customer;
    @Before
    public void setup() {
        Account account = new Account("000000", 10000000, "024098000125");
        Account account1 = new Account("111111", 5000000, "024098000125");
        customer = new Customer("Truong","024098000125");
        customer.addAccount(account);
        customer.addAccount(account1);
    }

    @Test
    public void getAccountByAccountNumber() {
        assertEquals(customer.getAccounts().get(0), customer.getAccountByAccountNumber(customer.getAccounts(), "000000"));

        assertEquals(customer.getAccounts().get(1), customer.getAccountByAccountNumber(customer.getAccounts(),"111111"));
    }

    @Test
    public void isPremium() {
        assertEquals("Premium", customer.isPremium());
    }

    @Test
    public void checkAccountExisted() {
        assertEquals(true, customer.checkAccountExisted("000000"));
        assertEquals(true, customer.checkAccountExisted("111111"));
        assertEquals(false, customer.checkAccountExisted("123456"));
    }
}