package com.walter.cojal.easylearning.data.Entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Asesor {

    private int id;
    private String name;
    @SerializedName("last_name")
    private String lastName;
    private int age;
    private String genre;
    private String document;
    private String academic;
    private Double rating;
    private ArrayList<String> assignatures;

    public Asesor(int id, String name, String lastName, int age) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getAcademic() {
        return academic;
    }

    public void setAcademic(String academic) {
        this.academic = academic;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public ArrayList<String> getAssignatures() {
        return assignatures;
    }

    public void setAssignatures(ArrayList<String> assignatures) {
        this.assignatures = assignatures;
    }
}
