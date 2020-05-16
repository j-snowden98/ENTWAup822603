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
 * Controller for the login view
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
    
    /**
     * Creates an instance of LoginCtrl
     */
    public LoginCtrl() {
    }
    
    /**
     * @return the username entered in the field in the login form. 
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Method to set the username based on the string entered in the login view.
     * @param username is the username entered in the login view.
     */
    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * @return a string containing the password entered by a user in the login view.
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Method to set the password based on the string entered in the login view
     * @param password is the password entered in the login view 
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * Method invoked when the login button is clicked. Attempts to log the user in by invoking the business logic method. Sets the user entity of the session scoped bean upon successful login.
     * @return a string to redirect to the contacts list view upon successful login, otherwise reloads the page and notifies the user that their entered credentials are invalid
     */
    public String attemptLogin() {
        Account currentUser = as.loginAttempt(username, password);
        if(currentUser != null) {
            loginSession.setUser(currentUser);
            return "myContacts";
        }
        else {
            FacesMessages.error("@property(loginCtrl.password)", "The username and/or passoword combination is incorrect.", "");
            return "";
        }
    }
}
