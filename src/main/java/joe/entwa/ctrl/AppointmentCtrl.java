/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.ctrl;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.inject.Inject;
import joe.entwa.bus.AppointmentService;
import joe.entwa.ent.Account;
import joe.entwa.ent.Appointment;
import joe.entwa.login.LoginSession;

/**
 * Controller for the create appointment view.
 * @author Joe
 */
@Named(value = "appointmentCtrl")
@RequestScoped
public class AppointmentCtrl {
    private Appointment newApp = new Appointment();
    private Boolean ownerParticipating = true;
    @Inject
    private LoginSession loginSession;
    @EJB
    private AppointmentService aps;
    
    /**
     * Creates a new instance of AppointmentCtrl
     */
    public AppointmentCtrl() {
    }
    
    /**
     * Returns the currently created appointment.
     * Start and end are split into LocalDates and LocalTimes.
     * @return either a new appointment entity or the currently edited appointment entity in the session scoped bean if there is one.
     */
    public Appointment getNewApp() {
        if(loginSession.getCurrentApp() != null) {
            newApp = loginSession.getCurrentApp();
        }
        return newApp;
    }
    
    /**
     * Updates the edited appointment entity with the fields entered from the form in the create appointment view.
     * LocalDate and LocalTimes are combined into LocalDateTimes to modify those in the appointment entity
     * @param newApp the updated account entity.
     */
    public void setNewApp(Appointment newApp) {
        this.newApp = newApp;
        this.loginSession.setCurrentApp(newApp);
    }
    
    /**
     * @return true or false for whether the owner of the current appointment is attending
     */
    public Boolean getOwnerParticipating() {
        return ownerParticipating;
    }
    
    /**
     * Sets whether the owner is attending the appointment based on the checkbox in the create appointment view.
     * @param ownerParticipating 
     */
    public void setOwnerParticipating(Boolean ownerParticipating) {
        this.ownerParticipating = ownerParticipating;
    }
    
    /**
     * Removes the selected account entity from the list of participating accounts in the appointment.
     * @param p is the selected account entity to be removed.
     * @return an empty string to reload the create appointment view, which will update the list of participants.
     */
    public String removeParticipant(Account p) {
        newApp.getParticipants().remove(p);
        return "";
    }
    
    /**
     * Save the current state of the created appointment to the session scoped CDI bean, then navigate to the add participants view.
     * @return a string to redirect to the add participants facelet.
     */
    public String openAddParticipants() {
        loginSession.setCurrentApp(newApp);
        return "addParticipants";
    }
    
    /**
     * Attempt to save the appointment after clicking the save button on the create appointment view.
     * @return either a string to redirect to the list of appointments facelet if the appointment has saved successfully, otherwise return an empty string to reload the page and display an appropriate error message.
     */
    public String saveAppointment() {
        aps.createAppointment(newApp, loginSession.getUser(), ownerParticipating);
        loginSession.setCurrentApp(null);
        return "myAppointments";
    }
}
