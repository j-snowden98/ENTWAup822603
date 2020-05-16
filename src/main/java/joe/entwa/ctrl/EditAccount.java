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
 * Controller for editing the details of a user account.
 * @author Joe
 */
@Named(value = "editAccount")
@RequestScoped
public class EditAccount {
    
    private String verifyPassword;
    private Account currentUser = new Account();
    
    @EJB
    private AccountService as;
    
    @Inject
    private LoginSession loginSession;
    
    /**
     * Create a new instance of EditAccount.
     */
    public EditAccount() {
    }
    
    /**
     * Retrieve the currently logged in account entity
     * @return an account entity.
     */
    public Account getCurrentUser() {
        currentUser = loginSession.getUser();
        return currentUser;
    }
    
    /**
     * Update the currently edited account entity
     * @param currentUser the modified account entity
     */
    public void setCurrentUser(Account currentUser) {
        this.currentUser = currentUser;
    }
    
    /**
     * @return the value of the verify password field in the edit account view. 
     */
    public String getVerifyPassword() {
        return verifyPassword;
    }
    
    /**
     * Sets the value of the verify password field
     * @param verifyPassword a string containing the value of the verify password field in the edit account view.
     */
    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }
    
    /**
     * Method to test whether the password and verify password fields are matching.
     * @return true if the password fields are matching, false if not.
     */
    public Boolean comparePassword() {
        return (currentUser.getPassword() == null ? verifyPassword == null : currentUser.getPassword().equals(verifyPassword));
    }
    
    /**
     * Method invoked by clicking the save button in the edit account view. This attempts to save the modified details of the user.
     * @return a string to redirect to the contacts list view if the details have been successfully updated. Otherwise returns an empty string to reload the page, along with an appropriate error message.
     */
    public String save() {
        if(comparePassword()) {
            Account updatedAc = as.editAccount(currentUser, loginSession.getCurrentUsername());
            if(updatedAc != null) {
                loginSession.setUser(updatedAc);
                return "myContacts";
            }
            else {
                FacesMessages.error("@property(editAccount.currentUser.username)", "This username is already taken. Please choose another one", "");
                return "";
            }
        }
        else {
            FacesMessages.error("@property(editAccount.verifyPassword)", "The passwords do not match.", "");
            return "";
        }
    }
}