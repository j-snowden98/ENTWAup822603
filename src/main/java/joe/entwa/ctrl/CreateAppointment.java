/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.ctrl;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import joe.entwa.ent.Appointment;

/**
 *
 * @author Joe
 */
@Named(value = "createAppointment")
@RequestScoped
public class CreateAppointment {
    private Appointment newApp;
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
    
}
