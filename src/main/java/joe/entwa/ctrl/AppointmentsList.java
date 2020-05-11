/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.ctrl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    private LoginSession loginSession;
    private List<Appointment> appointments = new ArrayList<>();
    /**
     * Creates a new instance of AppointmentsList
     */
    public AppointmentsList() {
    }

    public List<Appointment> getAppointments() {
        Set<Appointment> setOwn = new HashSet<>();
        Set<Appointment> setAtt = new HashSet<>();
        
        setOwn.addAll(loginSession.getUser().getOwnedAppointments());
        setAtt.addAll(loginSession.getUser().getAttendAppointments());
        setOwn.addAll(setAtt);
        appointments.addAll(setOwn);
        return appointments;
    }
}
