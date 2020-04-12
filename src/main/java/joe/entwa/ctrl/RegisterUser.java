/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.ctrl;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import joe.entwa.bus.AccountService;
import joe.entwa.ent.Account;

/**
 *
 * @author Joe
 */
@Named(value = "registerUser")
@RequestScoped
public class RegisterUser {
    /**
     * Creates a new instance of RegisterUser
     */
    
    private Account newUser = new Account();
    @EJB
    private AccountService as;
    
    public RegisterUser() {
    }

    public Account getNewUser() {
        return newUser;
    }

    public void setNewUser(Account newUser) {
        this.newUser = newUser;
    }
    
    public String register() {
        as.createAccount(newUser);
        return "";
    }
}