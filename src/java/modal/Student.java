/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;

import java.sql.Date;
import java.util.ArrayList;
import modal.Class;
/**
 *
 * @author pv
 */
public class Student {
    private int id; 
    private String name;
    private Date dob; 
    private String email; 
    private String phone;
    private Class c;
    private boolean gender;
    private Account account; 
    private ArrayList<WaitingBorrow> waitingBorrows = new ArrayList<>();
    public Student() {
    }

    public Class getC() {
        return c;
    }

    public void setC(Class c) {
        this.c = c;
    }

    public ArrayList<WaitingBorrow> getWaitingBorrows() {
        return waitingBorrows;
    }

    public void setWaitingBorrows(ArrayList<WaitingBorrow> waitingBorrows) {
        this.waitingBorrows = waitingBorrows;
    }   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Student(int id, String name, Date dob, String email, String phone, Class c, boolean gender, Account account) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.phone = phone;
        this.c = c;
        this.gender = gender;
        this.account = account;
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
