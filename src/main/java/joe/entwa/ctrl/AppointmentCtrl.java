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
 *
 * @author Joe
 */
@Named(value = "appointmentCtrl")
@RequestScoped
public class AppointmentCtrl {
    private Appointment newApp = new Appointment();
    
    @Inject
    private LoginSession loginSession;
    
    @EJB
    private AppointmentService aps;
    
    /**
     * Creates a new instance of CreateAppointment
     */
    public AppointmentCtrl() {
    }

    public Appointment getNewApp() {
        if(loginSession.getCurrentApp() != null) {
            newApp = loginSession.getCurrentApp();
        }
        return newApp;
    }

    public void setNewApp(Appointment newApp) {
        this.newApp = newApp;
        this.loginSession.setCurrentApp(newApp);
    }
    
    public String removeParticipant(Account p) {
        newApp.getParticipants().remove(p);
        return "";
    }
    
    public String addParticipants() {
        System.out.println(newApp.getDescription());
        loginSession.setCurrentApp(newApp);
        return "addParticipants";
    }
    
    public String save() {
        Appointment a = aps.createAppointment(newApp, loginSession.getUser());
        return "";
    }
}
