/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.bus;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import joe.entwa.ctrl.AppointmentCtrl;
import joe.entwa.ent.Account;
import joe.entwa.ent.Appointment;
import joe.entwa.login.LoginSession;
import joe.entwa.pers.AccountFacade;
import joe.entwa.pers.AppointmentFacade;

/**
 *
 * @author Joe
 */
@Stateless
public class AppointmentService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB
    private AccountFacade acc;
    
    @EJB
    private AppointmentFacade apmt;
    
    private List<Account> potentialParticipants;
    
    public Appointment createAppointment(Appointment a, Account owner) {
        a.setOwner(owner);
        apmt.create(a);
        
        a.getParticipants().forEach((p) -> {
            p.getAttendAppointments().add(a);
            acc.edit(p);
        });
        apmt.edit(a);
        
        return a;
    }
    
    public List<Account> getPotentialParticipants(Account owner, List<Account> participants) {
        List<Account> accounts = acc.findAll();
        accounts.remove(owner);
        if(participants != null) {
            accounts.removeAll(participants);
        }
        return accounts;
    }
    
    public void deleteAppointment(Appointment a) {
        apmt.remove(a);
    }
}
