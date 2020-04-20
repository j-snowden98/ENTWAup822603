/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.ctrl;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import joe.entwa.bus.ContactService;
import joe.entwa.ent.Account;

/**
 *
 * @author Joe
 */
@Named(value = "addContact")
@Dependent
public class AddContact {

    /**
     * Creates a new instance of AddContact
     */
    
    private Account user;
    
    @EJB
    private ContactService cs;
    
    public AddContact() {
    }
    
    public String addToContacts() {
        return "";
    }
}
