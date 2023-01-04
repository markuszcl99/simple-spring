package com.markus.spring.domain;

/**
 * @author: markus
 * @date: 2023/1/4 10:43 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class Teacher extends User {
    private Student student;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getDescription() {
        return "Teacher";
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "student=" + student.getDescription() +
                "} " + super.toString();
    }
}
