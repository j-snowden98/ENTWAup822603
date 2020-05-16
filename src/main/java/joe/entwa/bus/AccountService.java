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
 * Business logic for the management of user accounts in the system.
 * @author Joe
 */
@Stateless
public class AccountService {

    @EJB
    private AccountFacade af;
    
    /**
     * Method for persisting a newly created account. Will first get the DAO to check that the entered username is unique
     * @param a is the account to be persisted
     * @return the managed account entity if successful, otherwise if the username already exists, return null
     */
    public Account createAccount(Account a) {
        Boolean usernameUnique = af.checkUnique(a.getUsername());
        if(usernameUnique) {
            Account newAc = af.create(a);
            return newAc;
        }
        else
            return null;
    }
    
    /**
     * Method for merging an account entity whose fields have been changed. Like createAccount, it checks that the entered username doesn't already exist.
     * However, if the username is the same as before, it will allow the changes to be saved. checkUnique would return false in this case so it is necessary to test that the
     * entered username is the same as the original username of the account.
     * @param a is the account whose changes are to be merged with the persistence store.
     * @param oldUsername is the original username of the account, to test whether it has been changed.
     * @return the updated account entity if the username is unique or same as the original. Otherwise return null, which will cause the controller to display an error message to
     *  notify the user.
     */
    public Account editAccount(Account a, String oldUsername) {
        Boolean usernameUnique = af.checkUnique(a.getUsername());
        if(usernameUnique || (oldUsername.equals(a.getUsername()))) {
            Account editedAc = af.edit(a);
            return editedAc;
        }
        else
            return null;
    }
    
    /**
     * Method to log in a user account. Invokes the DAO to check whether the username and password are correct
     * @param username the username entered by the user
     * @param password the password entered by the user
     * @return returns the account entity with matching credentials. If not found, will return null which has been returned by the DAO
     */
    public Account loginAttempt(String username, String password) {
        Account a = af.authenticate(username, password);
        return a;
    }
    
    /**
     * Method to retrieve all account entities in the persistence store, by invoking the DAO to find all accounts.
     * @return a list of all account entities
     */
    public List<Account> loadAccounts() {
        List accounts = af.findAll();
        return accounts;
    }
}
