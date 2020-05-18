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
 * This class contains the business logic methods required for managing appointments.
 * @author Joe
 */
@Stateless
public class AppointmentService {
    
    @EJB
    private AccountFacade acc;
    
    @EJB
    private AppointmentFacade apmt;
    
    /**
     * Method to persist a newly created appointment entity. First checks for any time clashes for any participating accounts.
     * @param a the appointment entity to be persisted
     * @param owner the account entity who created the appointment
     * @param addOwner whether the owner of the appointment will be attending.
     * @return null if the appointment is created successfully, otherwise return a string containing a message to notify the owner of a time clash; which participant and the times of their appointment, allowing the owner to adjust the timings or the list of participants.
     */
    public String createAppointment(Appointment a, Account owner, Boolean addOwner) {
        a.setOwner(owner);
        String timeClashMsg = apmt.checkTimeClash(a, addOwner);
        if(timeClashMsg == null) {    
            owner.getOwnedAppointments().add(a);
            if(addOwner) 
                a.getParticipants().add(owner);
            apmt.create(a);

            a.getParticipants().forEach((p) -> {
                p.getAttendAppointments().add(a);
                acc.edit(p);
            });
            return null;
        }
        else {
            return timeClashMsg;
        }
    }
    
    /**
     * Method to retrieve a list of account entities, which are not the owner and are not already in the list of participants for the currently edited appointment
     * @param owner account entity which is creating the appointment.
     * @param participants the current list of participants in the appointment.
     * @return a list of account entities from the system which are not already in the list of participants and are not the owner.
     */
    public List<Account> getPotentialParticipants(Account owner, List<Account> participants) {
        List<Account> accounts = acc.findAll();
        accounts.remove(owner);
        if(participants != null) {
            accounts.removeAll(participants);
        }
        return accounts;
    }
    
    /**
     * Method to cancel an appointment.
     * @param a the appointment entity to be cancelled.
     */
    public void cancelAppointment(Appointment a) {
        apmt.remove(a);
    }
}
