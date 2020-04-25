/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.ctrl;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import joe.entwa.bus.AccountService;
import joe.entwa.ent.Account;
import joe.entwa.login.LoginSession;

/**
 *
 * @author Joe
 */
@Named(value = "loginCtrl")
@RequestScoped
public class LoginCtrl {

    /**
     * Creates a new instance of LoginCtrl
     */
    private String username;
    private String password;
    
    @EJB
    private AccountService as;
    
    @Inject
    private LoginSession loginSession;
    
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
    
    public String attemptLogin() {
        Account currentUser = as.loginAttempt(username, password);
        if(currentUser != null) {
            loginSession.setUser(currentUser);
            return "myContacts";
        }
        else {
            return "";
        }
        
    }
}
