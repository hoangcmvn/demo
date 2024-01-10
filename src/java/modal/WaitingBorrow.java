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
public class WaitingBorrow {
    private Student Student; 
    private Book book; 
    private Date dob;
    private boolean isBorrow;

    public WaitingBorrow(Student Student, Book book, Date dob, boolean isBorrow) {
        this.Student = Student;
        this.book = book;
        this.dob = dob;
        this.isBorrow = isBorrow;
    }

    public WaitingBorrow() {
    }

    public Student getStudent() {
        return Student;
    }

    public void setStudent(Student Student) {
        this.Student = Student;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }





    public boolean isIsBorrow() {
        return isBorrow;
    }

    public void setIsBorrow(boolean isBorrow) {
        this.isBorrow = isBorrow;
    }
    
}
