/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.ctrl;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import joe.entwa.bus.TestDataService;

/**
 *
 * @author Joe
 */
@Named(value = "startCtrl")
@Dependent
public class StartCtrl {

    /**
     * Creates a new instance of StartCtrl
     */
    private String testDataAdded = "Test data added: ";
    
    @EJB
    private TestDataService ds;
    
    public StartCtrl() {
    }
    
    public String getTestDataAdded() {
        return testDataAdded;
    }
    
    public String insertTestData() {
        ds.insertTestData();
        testDataAdded += "True";
        return "";
    }
    
}
