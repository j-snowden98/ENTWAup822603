/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.login;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import joe.entwa.ent.Account;
import joe.entwa.ent.Appointment;

/**
 *
 * @author Joe
 */
@Named(value = "loginSession")
@SessionScoped
public class LoginSession implements Serializable {
    private Account user;
    private Appointment currentApp;
    
    /**
     * Creates a new instance of LoginSession
     */
    LoginSession() {
    }

    public Account getUser() {
        return user;
    }

    public void setUser(Account user) {
        this.user = user;
    }

    public Appointment getCurrentApp() {
        return currentApp;
    }

    public void setCurrentApp(Appointment currentApp) {
        this.currentApp = currentApp;
    }
    
    public String logOut() {
        return "index";
    }
}
