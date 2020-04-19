/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.bus;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import joe.entwa.ent.Account;
import joe.entwa.pers.AccountFacade;
import joe.entwa.pers.AppointmentFacade;

/**
 *
 * @author Joe
 */
@Stateless
public class TestDataService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @EJB
    AccountFacade acc;
    
    @EJB
    AppointmentFacade app;
    
    public void insertTestData() {
        Account user1 = new Account("Becky", "Draper", "user1", "password1", "1 London Road", "North end", "Portsmouth", "Hampshire", "PO2 1AA", "07123456789", "user1@google.com");
        acc.create(user1);
        
        Account user2 = new Account("Kloe", "Duarte", "user2", "password2", "2 London Road", "North end", "Portsmouth", "Hampshire", "PO2 1AB", "07133456789", "user2@google.com");
        acc.create(user2);
        
        Account user3 = new Account("Bryson", "Sharma", "user3", "password3", "3 London Road", "North end", "Portsmouth", "Hampshire", "PO2 1AC", "07123776789", "user3@google.com");
        acc.create(user3);
        
        Account user4 = new Account("Drake", "Hutton", "user4", "password4", "4 London Road", "North end", "Portsmouth", "Hampshire", "PO2 1AD", "07122656789", "user4@google.com");
        acc.create(user4);
        
        Account user5 = new Account("Mackenzie", "Manning", "user5", "password5", "5 London Road", "North end", "Portsmouth", "Hampshire", "PO2 1AE", "07123456679", "user5@google.com");
        acc.create(user5);
        
        Account user6 = new Account("Colette", "Kramer", "user6", "password6", "6 London Road", "North end", "Portsmouth", "Hampshire", "PO2 1AF", "07123461789", "user6@google.com");
        acc.create(user6);
        
        Account user7 = new Account("Safia", "Kinney", "user7", "password7", "7 London Road", "North end", "Portsmouth", "Hampshire", "PO2 1AG", "07124984789", "user7@google.com");
        acc.create(user7);
        
        Account user8 = new Account("Alberto", "Doyle", "user8", "password8", "8 London Road", "North end", "Portsmouth", "Hampshire", "PO2 1AH", "07123462829", "user8@google.com");
        acc.create(user8);
        
        Account user9 = new Account("Abbie", "Faufman", "user9", "password9", "9 London Road", "North end", "Portsmouth", "Hampshire", "PO2 1AI", "07123474789", "user9@google.com");
        acc.create(user9);
        
        Account user10 = new Account("Jesse", "Howarth", "user10", "password10", "10 London Road", "North end", "Portsmouth", "Hampshire", "PO2 1AJ", "07123967789", "user10@google.com");
        acc.create(user10);
        
        user1.getContacts().add(user2);
        user1.getContacts().add(user3);
        user1.getContacts().add(user10);
        acc.edit(user1);
        
    }
}
