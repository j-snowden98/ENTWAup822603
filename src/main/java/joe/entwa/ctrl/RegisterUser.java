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
import net.bootsfaces.utils.FacesMessages;

/**
 *
 * @author Joe
 */
@Named(value = "registerUser")
@RequestScoped
public class RegisterUser {
    
    private String verifyPassword;
    private Account newUser = new Account();
    @EJB
    private AccountService as;
    @Inject
    LoginSession loginSession;
    
    /**
     * Creates a new instance of RegisterUser
     */
    public RegisterUser() {
    }
    
    /**
     * @return the value of the verify password field in the register view. 
     */
    public String getVerifyPassword() {
        return verifyPassword;
    }
    
    /**
     * Sets the value of the verify password field
     * @param verifyPassword a string containing the value of the verify password field in the register view.
     */
    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
    
    /**
     * Retrieve the created account entity
     * @return an account entity.
     */
    public Account getNewUser() {
        return newUser;
    }
    
    /**
     * Update the created account entity
     * @param newUser the modified account entity
     */
    public void setNewUser(Account newUser) {
        this.newUser = newUser;
    }
    
    /**
     * Method to test whether the password and verify password fields are matching.
     * @return true if the password fields are matching, false if not.
     */
    public Boolean comparePassword() {
        return (newUser.getPassword() == null ? verifyPassword == null : newUser.getPassword().equals(verifyPassword));
    }
    
    /**
     * Method invoked by clicking the save button in the register view. This attempts to save the details of the new user.
     * @return a string to redirect to the contacts list view if the account has been successfully saved. Otherwise returns an empty string to reload the page, along with an appropriate error message.
     */
    public String register() {
        if(comparePassword()) {
            Account createdAc = as.createAccount(newUser);
            //createAccount returns null if the username is not unique
            if(createdAc != null) {
                loginSession.setUser(createdAc);
                return "myContacts";
            }
            else {
                FacesMessages.error("@property(registerUser.newUser.username)", "This username is already taken. Please choose another one", "");
                return "";
            }
        }
        else {
            FacesMessages.error("@property(registerUser.verifyPassword)", "The passwords do not match.", "");
            return "";
        }
    }
}