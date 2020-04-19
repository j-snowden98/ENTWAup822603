/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.bus;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import joe.entwa.ent.Account;
import joe.entwa.pers.AccountFacade;

/**
 *
 * @author Joe
 */
@Stateless
public class AccountService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB
    private AccountFacade af;
    
    public Account createAccount(Account a) {
        af.create(a);
        return a;
    }
    
    /*public Account loginAttempt(String username, String password) {
        Account a = af.;
        return a;
    }*/
    
    public List<Account> loadAccounts() {
        List accounts = af.findAll();
        return accounts;
    }
}
