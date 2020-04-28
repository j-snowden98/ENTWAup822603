/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.ctrl;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import joe.entwa.bus.ContactService;
import joe.entwa.ent.Account;
import joe.entwa.login.LoginSession;

/**
 *
 * @author Joe
 */
@Named(value = "accountsList")
@RequestScoped
public class AccountsList {
    @Inject
    private LoginSession loginSession;
    
    private List<Account> accounts;
    
    @EJB
    private ContactService cs;
    
    /**
     * Creates a new instance of AccountsList
     */
    public AccountsList() {
        accounts = cs.getOtherAccounts(loginSession.getUser());
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
