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
@Named(value = "editAccount")
@RequestScoped
public class EditAccount {
    /**
     * Creates a new instance of RegisterUser
     */
    
    private String verifyPassword;
    private Account currentUser = new Account();
    
    @EJB
    private AccountService as;
    
    @Inject
    private LoginSession loginSession;
    
    public EditAccount() {
    }

    public Account getCurrentUser() {
        currentUser = loginSession.getUser();
        return currentUser;
    }

    public void setCurrentUser(Account currentUser) {
        this.currentUser = currentUser;
    }
    
    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
    
    public Boolean comparePassword() {
        return (currentUser.getPassword() == null ? verifyPassword == null : currentUser.getPassword().equals(verifyPassword));
    }
    
    public String save() {
        if(comparePassword()) {
            Account updatedAc = as.editAccount(currentUser, loginSession.getCurrentUsername());
            if(updatedAc != null) {
                loginSession.setUser(updatedAc);
                return "myContacts";
            }
            else {
                //error msg for duplicate username
                return "";
            }
        }
        else {
            return "";
        }
    }
}