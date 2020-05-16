/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.ctrl;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import joe.entwa.bus.TestDataService;

/**
 * Controller for the start view
 * @author Joe
 */
@Named(value = "startCtrl")
@RequestScoped
public class StartCtrl {
    @EJB
    private TestDataService ds;
    
    /**
     * Creates a new instance of StartCtrl
     */
    public StartCtrl() {
    }
    
    /**
     * Method invoked by clicking the test data button in the start view. Populates the database with test data.
     * @return empty string to reload the start page.
     */
    public String insertTestData() {
        ds.insertTestData();
        return "";
    }
    
}
