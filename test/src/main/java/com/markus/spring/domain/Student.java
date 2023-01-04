package com.markus.spring.domain;

/**
 * @author: markus
 * @date: 2023/1/4 10:43 PM
 * @Description:
 * @Blog: http://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class Student extends User {
    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getDescription() {
        return "Student";
    }

    @Override
    public String toString() {
        return "Student{" +
                "teacher=" + teacher.getDescription() +
                "} " + super.toString();
    }
}
