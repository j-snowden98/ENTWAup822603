/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.ctrl;

import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.inject.Inject;
import joe.entwa.ent.Account;
import joe.entwa.login.LoginSession;

/**
 * Controller for the contacts list view.
 * @author Joe
 */
@Named(value = "contactsList")
@RequestScoped
public class ContactsList {
    
    @Inject
    LoginSession loginSession;
    
    /**
     * Creates a new instance of ContactsList
     */
    public ContactsList() {
    }
    
    /**
     * Display the user's contacts
     * @return a list of account entities which the user has added to their contacts list.
     */
    public List<Account> getContacts() {
        return loginSession.getUser().getContacts();
    }
}
