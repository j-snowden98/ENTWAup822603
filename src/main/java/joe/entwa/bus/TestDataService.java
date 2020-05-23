/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.bus;

import java.time.LocalDateTime;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import joe.entwa.ent.Account;
import joe.entwa.ent.Appointment;
import joe.entwa.pers.AccountFacade;
import joe.entwa.pers.AppointmentFacade;

/**
 * Business logic for adding the test data to demonstrate the functionality of this web application
 * @author Joe
 */
@Stateless
public class TestDataService {
    
    @EJB
    AccountFacade acc;
    
    @EJB
    AppointmentFacade app;
    
    /**
     * Method to populate the persistence store with test data.
     */
    public void insertTestData() {
        Account user1 = new Account("Becky", "Draper", "user1", "password1", "1 London Road", "North end", "Portsmouth", "Hampshire", "PO2 8PJ", "+44(71)23456789", "user1@google.com");
        acc.create(user1);
        
        Account user2 = new Account("Kloe", "Duarte", "user2", "password2", "2 London Road", "North end", "Portsmouth", "Hampshire", "PO2 8PJ", "(071)33456789", "user2@google.com");
        acc.create(user2);
        
        Account user3 = new Account("Bryson", "Sharma", "user3", "password3", "3 London Road", "North end", "Portsmouth", "Hampshire", "PO2 8PJ", "(071)23776789", "user3@google.com");
        acc.create(user3);
        
        Account user4 = new Account("Drake", "Hutton", "user4", "password4", "4 London Road", "North end", "Portsmouth", "Hampshire", "PO2 8PJ", "+44(71)22656789", "user4@google.com");
        acc.create(user4);
        
        Account user5 = new Account("Mackenzie", "Manning", "user5", "password5", "5 London Road", "North end", "Portsmouth", "Hampshire", "PO2 8PJ", "(071)23456679", "user5@google.com");
        acc.create(user5);
        
        Account user6 = new Account("Colette", "Kramer", "user6", "password6", "6 London Road", "North end", "Portsmouth", "Hampshire", "PO2 8PJ", "+44(71)23461789", "user6@google.com");
        acc.create(user6);
        
        Account user7 = new Account("Safia", "Kinney", "user7", "password7", "7 London Road", "North end", "Portsmouth", "Hampshire", "PO2 8PJ", "+44 (71)24984789", "user7@google.com");
        acc.create(user7);
        
        Account user8 = new Account("Alberto", "Doyle", "user8", "password8", "8 London Road", "North end", "Portsmouth", "Hampshire", "PO2 8PJ", "(071) 23462829", "user8@google.com");
        acc.create(user8);
        
        Account user9 = new Account("Abbie", "Faufman", "user9", "password9", "9 London Road", "North end", "Portsmouth", "Hampshire", "PO2 8PJ", "(071)23474789", "user9@google.com");
        acc.create(user9);
        
        Account user10 = new Account("Jesse", "Howarth", "user10", "password10", "10 London Road", "North end", "Portsmouth", "Hampshire", "PO2 8PJ", "(071)23967789", "user10@google.com");
        acc.create(user10);
        
        user1.getContacts().add(user2);
        user2.getContacts().add(user1);
        
        user1.getContacts().add(user3);
        user3.getContacts().add(user1);
        
        user1.getContacts().add(user10);
        user10.getContacts().add(user1);
        
        Appointment appoint1 = new Appointment("Meeting 1", LocalDateTime.parse("2020-01-08T09:00"), LocalDateTime.parse("2020-01-08T11:00"), user1);
        app.create(appoint1);
        appoint1.getParticipants().add(user1);
        appoint1.getParticipants().add(user2);
        appoint1.getParticipants().add(user3);
        appoint1.getParticipants().add(user10);
        
        user1.getAttendAppointments().add(appoint1);
        user2.getAttendAppointments().add(appoint1);
        user3.getAttendAppointments().add(appoint1);
        user10.getAttendAppointments().add(appoint1);
        
        app.edit(appoint1);
        acc.edit(user1);
        acc.edit(user2);
        acc.edit(user3);
        acc.edit(user10);
        
        Appointment appoint2 = new Appointment("Meeting 2", LocalDateTime.parse("2020-01-08T13:00"), LocalDateTime.parse("2020-01-08T14:30"), user7);
        app.create(appoint1);
        appoint2.getParticipants().add(user1);
        appoint2.getParticipants().add(user2);
        appoint2.getParticipants().add(user3);
        appoint2.getParticipants().add(user4);
        appoint2.getParticipants().add(user5);
        appoint2.getParticipants().add(user6);
        appoint2.getParticipants().add(user7);
        appoint2.getParticipants().add(user10);
        
        user1.getAttendAppointments().add(appoint2);
        user2.getAttendAppointments().add(appoint2);
        user3.getAttendAppointments().add(appoint2);
        user4.getAttendAppointments().add(appoint2);
        user5.getAttendAppointments().add(appoint2);
        user6.getAttendAppointments().add(appoint2);
        user7.getAttendAppointments().add(appoint2);
        user10.getAttendAppointments().add(appoint2);
        
        app.edit(appoint1);
        acc.edit(user1);
        acc.edit(user2);
        acc.edit(user3);
        acc.edit(user4);
        acc.edit(user5);
        acc.edit(user6);
        acc.edit(user7);
        acc.edit(user10);
    }
}
