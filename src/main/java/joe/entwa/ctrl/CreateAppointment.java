/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.ctrl;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import joe.entwa.bus.AppointmentService;
import joe.entwa.ent.Account;
import joe.entwa.ent.Appointment;
import joe.entwa.login.LoginSession;

/**
 *
 * @author Joe
 */
@Named(value = "createAppointment")
@RequestScoped
public class CreateAppointment {
    private Appointment newApp;
    
    @Inject
    private LoginSession loginSession;
    
    @EJB
    private AppointmentService aps;
    
    private ArrayList<Account> participants;
    /**
     * Creates a new instance of CreateAppointment
     */
    public CreateAppointment() {
    }

    public Appointment getNewApp() {
        return newApp;
    }

    public void setNewApp(Appointment newApp) {
        this.newApp = newApp;
    }

    public ArrayList<Account> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Account> participants) {
        this.participants = participants;
    }
    
    public String save() {
        Appointment a = aps.createAppointment(newApp, loginSession.getUser(), participants);
        return "";
    }
    
}
