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
        Boolean usernameUnique = af.checkUnique(a.getUsername());
        if(usernameUnique) {
            Account newAc = af.create(a);
            return newAc;
        }
        else
            return null;
    }
    
    public Account editAccount(Account a, String oldUsername) {
        Boolean usernameUnique = af.checkUnique(a.getUsername());
        System.out.println(a.getUsername());
        System.out.println(oldUsername);
        if(usernameUnique || (oldUsername.equals(a.getUsername()))) {
            Account newAc = af.edit(a);
            return newAc;
        }
        else
            return null;
    }
    
    public Account loginAttempt(String username, String password) {
        Account a = af.authenticate(username, password);
        return a;
    }
    
    public List<Account> loadAccounts() {
        List accounts = af.findAll();
        return accounts;
    }
}
