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
import joe.entwa.ent.Appointment;
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
    
    public Boolean createAppointment(Appointment a, Account owner, Boolean addOwner) {
        a.setOwner(owner);
        owner.getOwnedAppointments().add(a);
        a.getParticipants().forEach((p) -> {
            p.getAttendAppointments().add(a);
            acc.edit(p);
        });
        
        if(addOwner) {
            a.getParticipants().add(owner);
            owner.getAttendAppointments().add(a);
        }
        apmt.create(a);
        acc.edit(owner);
        return true;
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
