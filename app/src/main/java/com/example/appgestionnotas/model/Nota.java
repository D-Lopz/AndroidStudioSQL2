package com.example.appgestionnotas.model;

public class Nota {
    private int id;
    private int student;
    private double value;

    public Nota(int id, int student, double value) {
        this.id = id;
        this.student = student;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent() {
        return student;
    }

    public void setStudent(int student) {
        this.student = student;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
