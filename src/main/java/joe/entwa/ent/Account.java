/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joe.entwa.ent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Entity class for representing user accounts in the system.
 * @author Joe
 */
@Entity
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String forename;
    private String surname;
    
    /**
     * This field represents an account's username, which must be unique. It is used to log in, along with their password.
     */
    private String username;
    
    /**
     * This field represents an account's password, which they will use along with their username, to log in. 
     */
    @Size(min=5, message = "Password must be at least 5 characters in length")
    private String password;
    private String address1;
    private String address2;
    private String town;
    private String county;
    
    /**
     * This field represents the postcode of an account. It is validated based on a regular expression
     * designed to recognise only valid UK postcodes
     */
    @Pattern(regexp="^$|^(([gG][iI][rR] {0,}0[aA]{2})|((([a-pr-uwyzA-PR-UWYZ][a-hk-yA-HK-Y]?[0-9][0-9]?)|(([a-pr-uwyzA-PR-UWYZ][0-9][a-hjkstuwA-HJKSTUW])|([a-pr-uwyzA-PR-UWYZ][a-hk-yA-HK-Y][0-9][abehmnprv-yABEHMNPRV-Y]))) {0,}[0-9][abd-hjlnp-uw-zABD-HJLNP-UW-Z]{2}))$", message = "Postcode is invalid")
    private String postcode;
    
    /**
     * This field represents the telephone number for an account. This user may choose not to enter their phone number.
     * It is validated using a regular expression to ensure it follows one of the two valid formats from the requirements.
     */
    @Pattern(regexp="\\+44\\s*\\(\\d{2}\\)\\s*\\d{8}|\\(\\d{3}\\)\\s*\\d{8}|^$", message = "Telephone number is invalid")
    private String telephone;
    
    /**
     * This field represents the email address of an account, which is mandatory. It is validated by a regular expression
     */
    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])", message = "Email format is invalid.")
    private String email;
    
    /**
     * This field maintains a list of this account's contacts; a list of other accounts which they have
     * added to their contacts list.
     */
    @ManyToMany 
    private List<Account> contacts = new ArrayList<>();
    
    /**
     * This field represents a collection of appointments which this account owns, meaning that they created the appointment
     */
    @OneToMany(mappedBy = "owner")
    private List<Appointment> ownedAppointments = new ArrayList<>();
    
    /**
     * This field represents a collection of appointments which this account is attending; therefore, this account
     * is also in the list of participants in each of the appointments in this field
     */
    @ManyToMany(mappedBy="participants")
    private List<Appointment> attendAppointments = new ArrayList<>();
    
    
    /**
     * Empty constructor, to conform to specification of entity classes
     */
    public Account() {
    }
    
    /**
     * Constructor with fields, used to create account entities in the test data
     * @param forename sets the account's forename
     * @param surname sets the account's surname
     * @param username sets the account's username
     * @param password sets the account's password
     * @param address1 sets the first line of the account's address
     * @param address2 sets the second line of the account's address
     * @param town sets the account's town or city
     * @param county sets the account's county
     * @param postcode sets the account's postcode
     * @param telephone sets the account's telephone number
     * @param email sets the account's email address
     * 
    */
    public Account(String forename, String surname, String username, String password, String address1, String address2, String town, String county, String postcode, String telephone, String email) {
        this.forename = forename;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.address1 = address1;
        this.address2 = address2;
        this.town = town;
        this.county = county;
        this.postcode = postcode;
        this.telephone = telephone;
        this.email = email;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Account> getContacts() {
        return contacts;
    }

    public void setContacts(List<Account> contacts) {
        this.contacts = contacts;
    }
    
    public List<Appointment> getOwnedAppointments() {
        return ownedAppointments;
    }

    public void setOwnedAppointments(List<Appointment> ownedAppointments) {
        this.ownedAppointments = ownedAppointments;
    }

    public List<Appointment> getAttendAppointments() {
        return attendAppointments;
    }

    public void setAttendAppointments(List<Appointment> attendAppointments) {
        this.attendAppointments = attendAppointments;
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
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "up822603.entwadraft.ent.user[ id=" + id + " ]";
    }
    
}
