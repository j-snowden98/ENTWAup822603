/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.ctrl;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import joe.entwa.bus.AppointmentService;
import joe.entwa.ent.Account;
import joe.entwa.ent.Appointment;
import joe.entwa.login.LoginSession;
import net.bootsfaces.utils.FacesMessages;

/**
 * Controller for the appointments list view
 * @author Joe
 */
@Named(value = "appointmentsList")
@RequestScoped
public class AppointmentsList {
    
    @Inject
    private LoginSession loginSession;
    private List<Appointment> appointments = new ArrayList<>();
    private List<Appointment> allAppointments = new ArrayList<>();
    private DateTimeFormatter ukDate = DateTimeFormatter.ofPattern("dd/MM/uuuu");
    @EJB
    private AppointmentService as;
    
    /**
     * Creates a new instance of AppointmentsList
     */
    public AppointmentsList() {
    }
    
    /**
     * Method to retrieve the current user's appointments. Combines the lists of the user's owned and attending appointments and removes the duplicates.
     * @return a list of appointments which the current user owns and/or is attending.
     */
    public List<Appointment> getAppointments() {
        //Creates sets for owned and attending appointments, to ensure there are no duplicates
        Set<Appointment> setOwn = new HashSet<>();
        Set<Appointment> setAtt = new HashSet<>();
        setOwn.addAll(loginSession.getUser().getOwnedAppointments());
        setAtt.addAll(loginSession.getUser().getAttendAppointments());
        
        //Combines the two sets to get the union, without any elements repeated.
        setOwn.addAll(setAtt);
        
        //Returns the union of the two sets as the expected list type.
        appointments.clear();
        appointments.addAll(setOwn);
        
        return appointments;
    }
    
    /**
     * Method to retrieve all appointments in the system
     * @return a list of all appointment entities to be displayed in the table
     */
    public List<Appointment> getAllAppointments() {
        allAppointments.clear();
        allAppointments.addAll(as.getAllAppointments());
        return allAppointments;
    }
    
    public DateTimeFormatter getUkDate() {
        return ukDate;
    }
    
    /**
     * Method to cancel an appointment when clicked. Checks that the current account is the owner of the appointment.
     * If the account is the owner, cancels the appointment. Otherwise notifies the user that they cannot cancel an appointment they do not own.
     * @param a the clicked appointment entity
     * @return a string to reload the list of appointments.
     */
    public String cancelAppointment(Appointment a) {
        if (Objects.equals(a.getOwner().getId(), loginSession.getUser().getId())) {
            Account user = as.cancelAppointment(a, loginSession.getUser());
            loginSession.setUser(user);
            return "";
        }
        else {
            FacesMessages.error("", "Only the owner can cancel an appointment.", "");
            return "";
        }
    }
}
