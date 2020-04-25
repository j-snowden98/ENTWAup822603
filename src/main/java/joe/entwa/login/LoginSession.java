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

/**
 *
 * @author Joe
 */
@Named(value = "loginSession")
@SessionScoped
public class LoginSession implements Serializable {
    private Account user;
    
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
}
