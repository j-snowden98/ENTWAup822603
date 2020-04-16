/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.bus;

import java.util.ArrayList;
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
    private AccountFacade acnt;
    
    @EJB
    private AppointmentFacade apmt;
    
    public Appointment createAppointment(Appointment a, Account owner, ArrayList<Account> participants) {
        a.setOwner(owner);
        a.getParticipants().addAll(participants);
        
        apmt.create(a);
        return a;
    }
    
    public void deleteAppointment(Appointment a) {
        apmt.remove(a);
    }
}
