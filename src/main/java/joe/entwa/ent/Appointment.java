/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.ent;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * Entity class for representing Appointments in the online diary facility
 * @author Joe
 */
@Entity
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
     * This field represents the start date of an appointment.
     */
    private LocalDate startDate;
    
    /**
     * This field represents the time at which the appointment starts.
     */
    private LocalTime startTime;
    
    /**
     * This field represents the date when the appointment ends. This allows complete flexibility to add events to a diary
     * which last multiple days.
     */
    private LocalDate endDate;
    
    /**
     * This field represents time at which the appointment ends.
     */
    private LocalTime endTime;
    
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
     * @param startDate sets the start date of the appointment
     * @param startTime sets the start time of the appointment
     * @param endDate sets the end date of the appointment
     * @param endTime sets the end time of the appointment
     * @param owner sets the owner of the appointment
     */
    public Appointment(String description, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, Account owner) {
        this.description = description;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.owner = owner;
    }
    
    /**
     * Empty constructor, to conform to specification of entity classes
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
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
