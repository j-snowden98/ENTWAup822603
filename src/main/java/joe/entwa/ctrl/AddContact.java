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
 * Controller for the add contact view.
 * @author Joe
 */
@Named(value = "addContact")
@RequestScoped
public class AddContact {
    @Inject
    private LoginSession loginSession;
    
    private List<Account> accounts;
    
    @EJB
    private ContactService cs;
    
    /**
     * Creates a new instance of AddContact
     */
    public AddContact() {
        
    }
    
    /**
     * Gets a list of account entities in the system, which are not the user and not already in the user's contacts list.
     * @return a list of account entities which can be added to the user's contacts list
     */
    public List<Account> getAccounts() {
        accounts = cs.getOtherAccounts(loginSession.getUser());
        return accounts;
    }
    
    /**
     * Adds the selected account entity to the user account's list of contacts.
     * @param a is the selected account entity
     * @return empty string, to reload the page and therefore update the list of accounts.
     */
    public String addToContacts(Account a) {
        Account updatedUser = cs.addContact(loginSession.getUser(), a);
        loginSession.setUser(updatedUser);
        return "";
    }
}
