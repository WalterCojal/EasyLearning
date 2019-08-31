package com.walter.cojal.easylearning.data.entities;

import com.google.gson.annotations.SerializedName;

public class User {

    private int id;
    private String name;
    @SerializedName("last_name")
    private String lastName;
    private String email;
    private String phone;
    @SerializedName("birth_date")
    private String birthDate;
    private int age;
    private String password;
    private String token;
    private String image;

    public User(String name, String lastName, String email, String phone, String password) {
        this.id = 0;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.birthDate = "";
        this.age = 0;
        this.password = password;
        this.token = "";
        this.image = "";
    }

    public User(int id, String name, String lastName, String email, String phone, String birthDate, int age, String password, String token, String image) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.age = age;
        this.password = password;
        this.token = token;
        this.image = image;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
