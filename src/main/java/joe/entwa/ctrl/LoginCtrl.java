/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.ctrl;

import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import joe.entwa.bus.AccountService;
import joe.entwa.ent.Account;

/**
 *
 * @author Joe
 */
@Named(value = "loginCtrl")
@Dependent
public class LoginCtrl {

    /**
     * Creates a new instance of LoginCtrl
     */
    private String username;
    private String password;
    private List<Account> allAccounts = null;
    
    @EJB
    private AccountService as;
    
    public LoginCtrl() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Account> getAllAccounts() {
        if (allAccounts == null) {
            allAccounts = as.loadAccounts();
        }
        return allAccounts;
    }
    
    public String attemptLogin() {
        return "";
    }
}
