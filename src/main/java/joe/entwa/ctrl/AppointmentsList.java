/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.ctrl;

import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import joe.entwa.ent.Appointment;
import joe.entwa.login.LoginSession;

/**
 *
 * @author Joe
 */
@Named(value = "appointmentsList")
@RequestScoped
public class AppointmentsList {
    
    @Inject
    LoginSession loginSession;
    
    
    /**
     * Creates a new instance of AppointmentsList
     */
    public AppointmentsList() {
    }

    public List<Appointment> getAppointments() {
       return loginSession.getUser().getAttendAppointments();
    }
    
}
