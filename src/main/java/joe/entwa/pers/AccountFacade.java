/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.pers;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import joe.entwa.ent.Account;

/**
 * DAO for persistence operations on the Account entity
 * @author Joe
 */
@Stateless
public class AccountFacade extends AbstractFacade<Account> {

    @PersistenceContext(unitName = "entwaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountFacade() {
        super(Account.class);
    }
    
    /**
     * Method to check that an entered username is unique.
     * @param newUsername is the entered username.
     * @return true or false for whether the username is unique.
     */
    public Boolean checkUnique(String newUsername) {
        TypedQuery<Account> findUser = em.createQuery("SELECT a FROM Account a WHERE a.username = :username", Account.class);
        List<Account> results = findUser.setParameter("username", newUsername).getResultList();
        return results.isEmpty();
    }
    
    /**
     * Method to log a user in. Checks that the credentials are valid.
     * @param username the entered username.
     * @param password the entered password.
     * @return an Account entity if the username matches an account and the password is correct, otherwise returns null.
     */
    public Account authenticate(String username, String password) {
        TypedQuery<Account> findUser = em.createQuery("SELECT a FROM Account a WHERE a.username = :username", Account.class);
        List<Account> results = findUser.setParameter("username", username).getResultList();
        if(results.isEmpty()) {
            return null;
        }
        else {
            if(password == null ? results.get(0).getPassword() == null : password.equals(results.get(0).getPassword())) {
                return results.get(0);
            }
            else {
                return null;
            }
        }
    }
    
}
