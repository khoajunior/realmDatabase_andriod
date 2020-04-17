package com.bignose.realmdatabase.model;

import io.realm.RealmObject;

public class Students extends RealmObject {
    String name;
    int marks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public Students(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    public Students() {
    }

    @Override
    public String toString() {
        return "Students{" +
                "name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }
}
