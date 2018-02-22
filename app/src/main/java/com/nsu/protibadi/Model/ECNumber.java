package com.nsu.protibadi.Model;

/**
 * Created by Majedur Rahman on 2/22/2018.
 */

public class ECNumber {
    private String contactName;
    private String ContactNumber;

    public ECNumber() {
    }

    public ECNumber(String contactName, String ContactNumber) {
        this.contactName = contactName;
        this.ContactNumber = ContactNumber;
    }

    public String getName() {
        return contactName;
    }

    public void setName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return ContactNumber;
    }

    public void setPhoneNumber(String ContactNumber) {
        this.ContactNumber = ContactNumber;
    }
}
