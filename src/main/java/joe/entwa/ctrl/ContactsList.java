/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.ctrl;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import joe.entwa.ent.Account;

/**
 *
 * @author Joe
 */
@Named(value = "contactsList")
@Dependent
public class ContactsList {
    private List<Account> contacts;
    /**
     * Creates a new instance of ContactsList
     */
    public ContactsList() {
    }

    public List<Account> getContacts() {
        return contacts;
    }

    public void setContacts(List<Account> contacts) {
        this.contacts = contacts;
    }
}
