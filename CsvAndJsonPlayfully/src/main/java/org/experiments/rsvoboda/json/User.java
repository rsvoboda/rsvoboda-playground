package org.experiments.rsvoboda.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * Created by rsvoboda on 5/2/17.
 */
public class User {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private List<String> emails;
    private List<String> phoneNumbers = new ArrayList<>();

    public User() {  }

    public User(String firstName,String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override public String toString() {
        return "User{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", dateOfBirth=" + dateOfBirth
                + ", emails=" + emails + ", phoneNumbers=" + phoneNumbers + '}';
    }
}
