/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.pers;

import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import joe.entwa.ent.Account;
import joe.entwa.ent.Appointment;

/**
 * DAO for persistence operations on the Appointment entity
 * @author Joe
 */
@Stateless
public class AppointmentFacade extends AbstractFacade<Appointment> {

    @PersistenceContext(unitName = "entwaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppointmentFacade() {
        super(Appointment.class);
    }
    
    /**
     * Method to check whether any participants in an appointment have other appointments which clash with this one.
     * @param a The appointment being created.
     * @param addOwner Whether the owner of the appointment is also attending. If they are, this method will
     * first check if the owner has clashing appointments and notify them.
     * @return a message containing the name of the participant and the start and end times 
     * of their appointment which clashes with a. Returns null if no clashes are found.
     */
    public String checkTimeClash(Appointment a, Boolean addOwner) {
        List<Appointment> clashingAppointments;
        Appointment c;
        Query timeClash = em.createNamedQuery("Appointment.timeClash")
                .setParameter(2, a.getAppStart())
                .setParameter(3, a.getAppEnd());
        
        if(addOwner) {
            timeClash.setParameter(1, a.getOwner().getUsername());
            clashingAppointments = timeClash.getResultList();
            if(!clashingAppointments.isEmpty()) {
                c = clashingAppointments.get(0);
                String start = c.getAppStart().format(DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm"));
                String end = c.getAppEnd().format(DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm"));
                return "You have a clashing appointment starting at " + start + " and finishing at " + end;
            }
        }
        
        for (Account person : a.getParticipants()) {
            timeClash.setParameter(1, person.getUsername());
            clashingAppointments = timeClash.getResultList();
            if (!clashingAppointments.isEmpty()) {
                c = clashingAppointments.get(0);
                String start = c.getAppStart().format(DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm"));
                String end = c.getAppEnd().format(DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm"));
                return person.getForename() + " " + person.getSurname() + " has a clashing appointment starting at " + start + " and finishing at " + end;
            }
        }
        return null;
    }
}
