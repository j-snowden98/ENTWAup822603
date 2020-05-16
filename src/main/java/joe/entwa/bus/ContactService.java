/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.bus;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import joe.entwa.ent.Account;
import joe.entwa.pers.AccountFacade;

/**
 * Business logic for the management of contacts lists.
 * @author Joe
 */
@Stateless
public class ContactService {
    
    @EJB
    private AccountFacade af;
    
    /**
     * Method to add an account to an account's contacts list.
     * @param user this is the currently logged in account entity.
     * @param newContact this is the account entity selected by the user to add to their contacts list
     * @return the updated account entity which is currently logged in.
     */
    public Account addContact(Account user, Account newContact) {
        user.getContacts().add(newContact);
        af.edit(user);
        return user;
    }
    
    /**
     * Method to retrieve all account entities from the persistence store apart from the current user.
     * @param user the account entity currently logged in.
     * @return a list of all accounts which are not the current user and not already in the user's contacts list.
     */
    public List<Account> getOtherAccounts(Account user) {
        List<Account> otherAccounts = af.findAll();
        otherAccounts.remove(user);
        otherAccounts.removeAll(user.getContacts());
        return otherAccounts;
    }
}
