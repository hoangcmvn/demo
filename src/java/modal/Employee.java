/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;

import java.sql.Date;

/**
 *
 * @author pv
 */
public class Employee {
    private int eid; 
    private String ename; 
    private String email;
    private boolean gender; 
    private Date dob;
    private Account account; 

    public Employee() {
    }

    public Employee(int eid, String ename, String email, boolean gender, Date dob, Account account) {
        this.eid = eid;
        this.ename = ename;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.account = account;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
   
    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
}
