/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.ctrl;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Joe
 */
@Named(value = "startCtrl")
@Dependent
public class Start {

    /**
     * Creates a new instance of Start
     */
    private Boolean testDataAdded = false;
    
    public Start() {
    }
    
    public Boolean getTestDataAdded() {
        return testDataAdded;
    }
    
    public String insertTestData() {
        testDataAdded = true;
        return "";
    }
    
}
