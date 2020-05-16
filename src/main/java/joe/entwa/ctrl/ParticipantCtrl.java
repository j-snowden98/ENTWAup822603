/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.ctrl;

import javax.inject.Named;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import joe.entwa.bus.AppointmentService;
import joe.entwa.ent.Account;
import joe.entwa.login.LoginSession;

/**
 * Controller for the add participants view.
 * @author Joe
 */
@Named(value = "participantCtrl")
@RequestScoped
public class ParticipantCtrl {
    @Inject
    private LoginSession user;
    
    @EJB
    private AppointmentService as;
    
    private List<Account> accounts;
    
    /**
     * Creates a new instance of ParticipantCtrl
     */
    public ParticipantCtrl() {
    }
    
    /**
     * Method to retrieve a list of accounts which are not the user and not already participating in the current appointment.
     * @return a list of account entities.
     */
    public List<Account> getAccounts() {
        if(user.getCurrentApp() != null) {
            accounts = as.getPotentialParticipants(user.getUser(), user.getCurrentApp().getParticipants());
        }
        else {
            accounts = as.getPotentialParticipants(user.getUser(), null);
        }
        return accounts;
    }
     /**
      * Method invoked when clicking the add button on an account in the add participants view.
      * @param a is the selected account entity
      * @return an empty string to reload the page and update the list of available accounts.
      */
    public String addToParticipants(Account a) {
        if(user.getCurrentApp() != null) {
            user.getCurrentApp().getParticipants().add(a);
        }
        return "";
    }
}
