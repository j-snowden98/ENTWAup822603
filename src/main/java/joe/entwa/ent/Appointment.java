/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.ent;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 * Entity class for representing Appointments in the online diary facility
 * @author Joe
 */
@Entity
@NamedQuery(name = "Appointment.timeClash", query = "SELECT a FROM Appointment a INNER JOIN a.participants u WHERE (u.username = ?1) AND ((a.appStart BETWEEN ?2 AND ?3) OR (a.appEnd BETWEEN ?2 AND ?3) OR (a.appStart <= ?2 AND a.appEnd >= ?2))")
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /**
     * This field represents the description of an appointment.
     */
    private String description;
    
    /**
     * This field represents the start date and time of an appointment.
     */
    private LocalDateTime appStart;
    
    
    /**
     * This field represents the date and time when the appointment ends. This allows complete flexibility to add events to a diary which last multiple days.
     */
    private LocalDateTime appEnd;
    
    /**
     * This field represents the account which created the appointment
     */
    @ManyToOne
    private Account owner;
    
    /**
     * This field represents the collection of accounts which are attending this meeting.
     * This is a many to many relationship, and is bi-directional, as it may be beneficial to access an account's appointments and an appointment's accounts
     */
    @ManyToMany
    private List<Account> participants = new ArrayList<>();
    
    /**
     * Constructor with parameters, allowing appointments to be created in the test data.
     * @param description sets the description of the appointment
     * @param appStart sets the start date and time of the appointment
     * @param appEnd sets the end date and time of the appointment
     * @param owner sets the owner of the appointment
     */
    public Appointment(String description, LocalDateTime appStart, LocalDateTime appEnd, Account owner) {
        this.description = description;
        this.appStart = appStart;
        this.appEnd = appEnd;
        this.owner = owner;
    }

    /**
     * Creates an instance of appointment.
     */
    public Appointment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getAppStart() {
        return appStart;
    }

    public void setAppStart(LocalDateTime appStart) {
        this.appStart = appStart;
    }

    public LocalDateTime getAppEnd() {
        return appEnd;
    }

    public void setAppEnd(LocalDateTime appEnd) {
        this.appEnd = appEnd;
    }
    
    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public List<Account> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Account> participants) {
        this.participants = participants;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "up822603.entwadraft.ent.Appointment[ id=" + id + " ]";
    }
    
}
