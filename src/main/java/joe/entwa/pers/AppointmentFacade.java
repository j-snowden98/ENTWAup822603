/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.pers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    
    public String checkTimeClash(Appointment a, Boolean addOwner) {
        if(addOwner) {
            //check owner
        }
        
        return null;
    }
}
