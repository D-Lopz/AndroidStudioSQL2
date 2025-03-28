package com.example.appgestionnotas.model;

public class Estudiante {
    private int id;
    private String name;
    private String idCode;

    public Estudiante(int id, String name, String idCode) {
        this.id = id;
        this.name = name;
        this.idCode = idCode;
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

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }
}
